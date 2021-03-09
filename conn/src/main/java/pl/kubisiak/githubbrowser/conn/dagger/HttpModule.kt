package pl.kubisiak.githubbrowser.conn.dagger

import dagger.Module
import dagger.Provides
import pl.kubisiak.githubbrowser.conn.retrofit.RetrofitProvider
import pl.kubisiak.githubbrowser.conn.retrofit.SearchService

@Module
class HttpModule {

	@Provides
	fun createSearchService(retrofitProvider: RetrofitProvider): SearchService {

		val retrofit = retrofitProvider.provide()
		val retval = retrofit.create(SearchService::class.java)
		return retval
	}
}