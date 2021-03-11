package pl.kubisiak.githubbrowser.ui

interface Navigator {

	fun openUrl(url: String)

	companion object {
		lateinit var instance: Navigator
	}
}