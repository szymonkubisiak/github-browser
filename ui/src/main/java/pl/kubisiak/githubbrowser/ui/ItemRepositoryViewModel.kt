package pl.kubisiak.githubbrowser.ui

import pl.kubisiak.githubbrowser.domain.models.Repository
import pl.kubisiak.githubbrowser.ui.base.BaseViewModel

class ItemRepositoryViewModel (val model: Repository): BaseViewModel() {

	fun goToHomepage() {
		Navigator.instance.openUrl(model.url)
	}
}