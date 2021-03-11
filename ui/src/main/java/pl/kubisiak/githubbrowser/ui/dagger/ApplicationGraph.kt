package pl.kubisiak.githubbrowser.ui.dagger

import pl.kubisiak.githubbrowser.domain.usecase.SearchUseCase

interface ApplicationGraph {

	fun provideSearchUC(): SearchUseCase

	companion object {
		lateinit var instance: ApplicationGraph
	}
}