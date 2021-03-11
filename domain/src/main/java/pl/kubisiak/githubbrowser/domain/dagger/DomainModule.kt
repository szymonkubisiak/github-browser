package pl.kubisiak.githubbrowser.domain.dagger

import dagger.Binds
import dagger.Module
import pl.kubisiak.githubbrowser.domain.usecase.SearchUseCase
import pl.kubisiak.githubbrowser.domain.usecase.impl.SearchUseCaseImpl

@Module
interface DomainModule {

	@Binds
	fun bindSearchUseCase(impl: SearchUseCaseImpl) : SearchUseCase
}