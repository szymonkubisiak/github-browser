package pl.kubisiak.githubbrowser.domain.usecase.impl

import io.reactivex.Single
import pl.kubisiak.githubbrowser.domain.models.Repository
import pl.kubisiak.githubbrowser.domain.repos.SearchRepo
import pl.kubisiak.githubbrowser.domain.usecase.SearchUseCase
import javax.inject.Inject

class SearchUseCaseImpl @Inject constructor(private val repo: SearchRepo) : SearchUseCase {
	override fun search(query: String): Single<List<Repository>> {
		return repo.search(query)
	}
}