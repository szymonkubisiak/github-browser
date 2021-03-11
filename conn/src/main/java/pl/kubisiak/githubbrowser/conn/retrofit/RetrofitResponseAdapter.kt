package pl.kubisiak.githubbrowser.conn.retrofit

import pl.kubisiak.githubbrowser.conn.dto.GithubHeadersDto
import retrofit2.Response
import java.time.Instant
import java.time.temporal.ChronoUnit


class RetrofitResponseAdapter {
	companion object {

		fun <T> responseToObject(arg: Response<T>): Pair<T?, GithubHeadersDto> {
			val body = arg.body()
			val gth = GithubHeadersDto()

			val headers = arg.headers()

			gth.ratelimitLimit = headers["x-ratelimit-limit"]?.toIntOrNull()
			gth.ratelimitRemaining = headers["x-ratelimit-remaining"]?.toIntOrNull()
			gth.ratelimitUsed = headers["x-ratelimit-used"]?.toIntOrNull()
			gth.ratelimitReset = headers["x-ratelimit-reset"]?.toLongOrNull()?.let {
				val instant = Instant.ofEpochSecond(it)
				val now = Instant.now()
				val remaining = now.until(instant, ChronoUnit.SECONDS)
				remaining.toInt()
			}

			return Pair(body, gth)
		}
	}
}