package pl.kubisiak.githubbrowser.domain.repos

import io.reactivex.Single
import pl.kubisiak.githubbrowser.domain.models.Repository

interface SearchRepo {
	fun search(query: String): Single<List<Repository>>
}