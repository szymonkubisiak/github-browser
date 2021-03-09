package pl.kubisiak.githubbrowser.conn.repos

import io.reactivex.Single
import pl.kubisiak.githubbrowser.conn.adapters.SearchAdapter
import pl.kubisiak.githubbrowser.conn.retrofit.SearchService
import pl.kubisiak.githubbrowser.domain.models.SearchResult
import pl.kubisiak.githubbrowser.domain.repos.SearchRepo
import javax.inject.Inject

class SearchRepoImpl @Inject constructor(val http: SearchService) : SearchRepo {

	private val adapter = SearchAdapter()

	override fun search(query: String): Single<List<SearchResult>> {
		return http.getSearch(query)
			.map(adapter::dtoToModel)
	}
}
