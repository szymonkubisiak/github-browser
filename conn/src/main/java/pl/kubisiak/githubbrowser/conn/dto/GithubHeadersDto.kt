package pl.kubisiak.githubbrowser.conn.dto


//IDEA: use annotation and reflection to tie fields to headers

class GithubHeadersDto {
	var ratelimitLimit: Int? = null //x-ratelimit-limit
	var ratelimitRemaining: Int? = null //x-ratelimit-remaining
	var ratelimitReset: Int? = null //x-ratelimit-reset
	var ratelimitUsed: Int? = null //x-ratelimit-used
}

/*
	var x-ratelimit-limit: Int? = null
	var x-ratelimit-remaining: ? = null
	var x-ratelimit-reset: ? = null
	var x-ratelimit-used: ? = null
*/
