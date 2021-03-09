package pl.kubisiak.githubbrowser.conn.retrofit

import io.reactivex.Single
import pl.kubisiak.githubbrowser.conn.dto.SearchResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
	@GET("/search/code")
	fun getSearch(@Query("q") q: String): Single<SearchResponseDto>
}