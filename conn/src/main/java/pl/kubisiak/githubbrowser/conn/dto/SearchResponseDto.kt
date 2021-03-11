package pl.kubisiak.githubbrowser.conn.dto

open class SearchResponseDto<T> {
	var total_count: Int? = null
	var incomplete_results: Boolean? = null
	var items: List<T>? = null
}
