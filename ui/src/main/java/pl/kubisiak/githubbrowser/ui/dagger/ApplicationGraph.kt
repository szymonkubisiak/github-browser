package pl.kubisiak.githubbrowser.ui.dagger

import pl.kubisiak.githubbrowser.domain.repos.SearchRepo

interface ApplicationGraph {

	fun provideSearchRepo(): SearchRepo

	companion object {
		lateinit var instance: ApplicationGraph
	}
}