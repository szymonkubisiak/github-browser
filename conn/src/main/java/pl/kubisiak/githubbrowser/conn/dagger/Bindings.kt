package pl.kubisiak.githubbrowser.conn.dagger

import dagger.Binds
import dagger.Module
import pl.kubisiak.githubbrowser.conn.repos.SearchRepoImpl
import pl.kubisiak.githubbrowser.domain.repos.SearchRepo

@Module
abstract class Bindings {
	@Binds
	abstract fun bindSearchRepo(impl: SearchRepoImpl): SearchRepo
}