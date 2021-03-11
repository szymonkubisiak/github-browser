package pl.kubisiak.githubbrowser.ui

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import pl.kubisiak.githubbrowser.ui.base.BaseViewModel
import pl.kubisiak.githubbrowser.ui.dagger.ApplicationGraph

class MainViewModel : BaseViewModel() {

	val items = ObservableArrayList<BaseViewModel>()

	val query = MutableLiveData("GitHub user:defunkt")

	fun doSearch() {
		query.value?.takeIf { it.isNotBlank() }?.also { validQuery ->
			disposer += ApplicationGraph.instance.provideSearchUC()
				.search(validQuery)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe({ result ->
					items.clear()
					val newList = result.map { ItemRepositoryViewModel(it) }
					items.addAll(newList)
				}, {
					//ignore
				})
		}
	}
}