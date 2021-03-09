package pl.kubisiak.githubbrowser.domain.repos

import io.reactivex.Single
import pl.kubisiak.githubbrowser.domain.models.SearchResult

interface SearchRepo {
	fun search(query: String): Single<List<SearchResult>>
}