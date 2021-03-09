package pl.kubisiak.githubbrowser.conn.retrofit

import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitProvider @Inject constructor()  {

	fun provide(): Retrofit {
		return retrofitInstance
	}

	private val retrofitInstance = createRetrofit()

	private fun createRetrofit(): Retrofit {

		val retval = Retrofit.Builder()
			.baseUrl("https://api.github.com")
			.addConverterFactory(GsonConverterFactory.create())
			.addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
			.client(createOkHttpClient())
			.build()

		return retval
	}

	private fun createOkHttpClient(): OkHttpClient {
		val httpClient = OkHttpClient.Builder()

		val basicAuth = Base64.getEncoder().encodeToString("".toByteArray())

		httpClient.addInterceptor { chain ->
			val original = chain.request()
			val requestBuilder = original.newBuilder()
				.header("User-Agent", "Szymon-Kubisiak-Github-Browser")
				.header("Accept", "application/vnd.github.v3+json")
				.addHeader("Authorization", "Basic: " + basicAuth)

			val request = requestBuilder.build()
			chain.proceed(request)
		}
			//.addInterceptor(ErrorInterceptor(Gson()))
			//.addInterceptor(MockInterceptor())
			.writeTimeout(30, TimeUnit.SECONDS)
			.readTimeout(30, TimeUnit.SECONDS)

		httpClient.addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))

		return httpClient.build()
	}
}