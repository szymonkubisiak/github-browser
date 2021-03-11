package pl.kubisiak.githubbrowser.conn.dto

class FileDto {

	var name: String? = null // "CHANGES.md",
	var path: String? = null // "CHANGES.md",
	var sha: String? = null // "cf44c1d2246a6f43f9c2415a0e99100c4f024782",
	var url: String? = null // "https://api.github.com/repositories/43908/contents/CHANGES.md?ref=115f77df9755c6a453f3e5d9623ff885d207ea82",
	var git_url: String? = null // "https://api.github.com/repositories/43908/git/blobs/cf44c1d2246a6f43f9c2415a0e99100c4f024782",
	var html_url: String? = null // "https://github.com/defunkt/markdown-mode/blob/115f77df9755c6a453f3e5d9623ff885d207ea82/CHANGES.md",
	var repository: RepositoryDto? = null //
	var score: Double? = null // 1.0

}

class SearchForFilesResponseDto : SearchResponseDto<FileDto>()