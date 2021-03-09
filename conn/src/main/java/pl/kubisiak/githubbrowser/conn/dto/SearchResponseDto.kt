package pl.kubisiak.githubbrowser.conn.dto

class SearchResponseDto {
	var total_count: Int? = null
	var incomplete_results: Boolean? = null
	var items: List<Unit>? = null
}

//{"total_count":0,"incomplete_results":false,"items":[]}