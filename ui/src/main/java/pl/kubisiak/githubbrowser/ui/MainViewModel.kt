package pl.kubisiak.githubbrowser.ui

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import pl.kubisiak.githubbrowser.ui.base.BaseViewModel
import pl.kubisiak.githubbrowser.ui.dagger.ApplicationGraph

class MainViewModel : BaseViewModel() {

	val query = MutableLiveData("")

	fun doSearch() {
		disposer += ApplicationGraph.instance.provideSearchRepo()
			.search("GitHub user:defunkt")
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe({

			}, {

			})
	}
}