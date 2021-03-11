package pl.kubisiak.githubbrowser.domain.usecase

import io.reactivex.Single
import pl.kubisiak.githubbrowser.domain.models.Repository

interface SearchUseCase {
	fun search(query: String): Single<List<Repository>>
}