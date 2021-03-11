package pl.kubisiak.githubbrowser.conn.adapters

import pl.kubisiak.githubbrowser.conn.dto.SearchForFilesResponseDto
import pl.kubisiak.githubbrowser.conn.dto.SearchForRepositoryResponseDto
import pl.kubisiak.githubbrowser.domain.models.Repository

class SearchAdapter {

	fun dtoToModel(dto: SearchForRepositoryResponseDto): List<Repository> {
		//TODO add consistency verification better than '!!'
		val retval = dto.items?.map {
			Repository(it.full_name!!, it.html_url!!)
		}
		return retval ?: listOf()
	}

	fun dtoToModel(dto: SearchForFilesResponseDto): List<Repository> {
		val retval = dto.items?.map {
			Repository(it.repository!!.full_name!!, it.repository!!.html_url!!)
		}
		return retval ?: listOf()
	}
}