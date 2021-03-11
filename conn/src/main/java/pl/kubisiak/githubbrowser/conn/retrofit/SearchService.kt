package pl.kubisiak.githubbrowser.conn.retrofit

import io.reactivex.Single
import pl.kubisiak.githubbrowser.conn.dto.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
	//IDEA: create RxJava2CallAdapter that handles headers without need to depend on Retrofit specifics like Response<>
	@GET("/search/code")
	fun getCode(@Query("q") q: String): Single<Response<SearchForFilesResponseDto>>

	@GET("/search/repositories")
	fun getRepositories(@Query("q") q: String): Single<Response<SearchForRepositoryResponseDto>>
}