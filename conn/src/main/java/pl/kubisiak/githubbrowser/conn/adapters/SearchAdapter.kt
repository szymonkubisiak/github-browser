package pl.kubisiak.githubbrowser.conn.adapters

import pl.kubisiak.githubbrowser.conn.dto.SearchResponseDto
import pl.kubisiak.githubbrowser.domain.models.SearchResult

class SearchAdapter {

	fun dtoToModel(dto: SearchResponseDto): List<SearchResult> {
		return listOf()
	}
}