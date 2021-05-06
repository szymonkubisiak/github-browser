# A simple "homework" project.
Still, it demonstrates several key features.

#### Reqs:
1) browse (search) github repositories by a keyword
2) present results as a list
3) open in a browser when clicked


## Key concepts:

#### Horizontal division
This project is divided into 3 modules, representing layers of clean/onion/hexagonal architecture:
1) domain - the innermost, business layer. There is no business logic in app so simple, but if there was, it would go here. It's the most independent module, doesn't even know Android. The only dependencies are RxJava and Dagger. It contains business models, declarations and implementations of UseCases and declarations of repos that UseCases use. Plus a Dagger module.
2) conn - the outermost layers, from data acquisition and storage side. Depends on domain. Contains implementations of repos, HTTP client and transport models, and business<->transport adapters with data consistency checks.
3) ui - the outermost layers, ui side.  Depends on domain. Contains ViewModels, layouts and all the glue logic between them like Activities, Fragments and binding adapters.
4) app - as it turns out, in order to have 3 layers you need a 4th module that does nothing but includes all other modules and presents whole to operating system. Otherwise one of the modules (usually ui) would have to perform this duty and gain access to all other modules - and that's a Bad Thing. Here is `Application` class that builds Dagger graph and the manifest, nothing more. 

Horizontal division is not as popular as vertical one. Vertical is very useful when you're adding and, especially, when removing features. However, I believe that hard horizontal separation is more beneficial in the long term, forcing the future me to keep things where they belong. Of course, one can have both - if willing to handle layers × features number of modules.

#### 2 stage parsing
Data incoming from the internet is parsed in 2 stages: first from JSON to transport models (also known as DTO) and then into domain models. First stage is implemented with Retrofit and Gson automatic JSON-to-POJO parsing and then the second, custom stage converts the 100% anemic POJOs into fully fledged domain models.  
The rules for transport models are: **only simple JSON types, all fields nullable**. Those models are then converted to and from domain models with adapter methods.  
- **"All-nullable"** is the answer to the conceptual difference between untyped JSON and strongly typed Java/Kotlin. Different parsers reconcile it differently, often by making up values that are not there. The extreme example is mandatory (value type) int - if it's missing from JSON, most parsers would insert 0, thus denying ability to find the error and fail early.  
- **"Only simple types"** removes more complicated work (like building `Date` from `String`) from parser to explicit code than can handle it in any way necessary. Even the wildest ideas of API designers can be easily tamed here. Like date that can be ISO format or "today" literal.  

This concept basically rejects all more advanced features of the JSON parser and uses the lowest common denominator functionality. This means that pretty much any parser will do - satisfying the substitution principle and removing dependency.  
Second advantage is the ability to do all kinds of data consistency checks, not only for nulls, but also range checks, and even cross-checking values against each other. Any errors can be reported here, which makes debugging a breeze and instantly arbitrates frontend vs backend blaming.

Architecturally speaking, transport models and Retrofit service definitions form the outermost layer, interfacing directly with external APIs, while Repos form the next layer, which domain consumes.

#### MVVM + databinding
The ui module is composed of 2 main layers: external, facing user is composed of XML layouts and internal is ViewModels that consume domain objects and expose observable properties which binding engine binds to layouts. Binding adapters and Converters facilitate communication which is created solely by the binding engine.


The rules for ViewModels are:  
- **"No dependency on View"** whatsoever. ViewModels can emit only simple types (preferably), domain models (if range of data overlaps enough) or purpose-built ui models (if data need to be pieced from different sources). Sorry, no `VISIBILTY`, only `Boolean`.
- **"Minimize Android dependency"**. Ideally, ViewModels should not be dependent on anything platform-specific. Unfortunately, they have to. First: either `LiveData` or `android.databinding.Observable` - because it's needed to announce changes to the binding engine. Second: `androidx.lifecycle.ViewModel` with that sweet `onCleared` destructor that makes unsubscribing all sources a breeze. In theory one could use Rx to emit and `WeakReference` to observe, but it's not really worth it IMHO.

ViewModels form the inner layer of ui module, interacting directly with domain.

###### Only one RecyclerView:
My take on MVVM allows ViewModels to emit other ViewModels. This allows scoping and reuse of view responsibilities. Prime example are lists: ViewModel  of the outer view (eg. `Framgent`) emits an `ObservableList` of small ("item") ViewModels. `RecyclerView` gets its "source" property bound to the list and from there only one `Adapter` and `ViewHolder` are necessary - all they do is to create a layout and bind it to the `ViewModel`. A `DataTemplateSelector` abstracts the mapping of certain `ViewModels` to appropriate layouts. Voila, that's all that's needed to handle **all `RecyclerViews`**, ever.


#### Devolved Fragments
Fragments (or Activities) pretty much devolve into `getLayoutRes` which defines layout and `createViewModel` which specifies ViewModel. If there is a parameter passed to a screen, it also gets handled here - but that's all. Thank you, databinding, for making it possible!

#### Navigator
Navigator is a very naive abstraction for moving to a screen (Fragment or Activity), but here it's enough. The point is that it can be easily replaced with any other kind of navigation without affecting the rest of the project.

#### Dagger
I'm not very proud of the way I'm using Dagger, it's because I don't have as extensive experience with DI frameworks. The entire graph is simply kept as global variable, so it can be accessed as necessary. Looks ugly, but it merely highlights the global scope of a graph, which is how it exists anyway.  
I'm bit wary of using `@Inject lateinit` members, because it splits initialization into constructor and injection, creating a half-built state.
