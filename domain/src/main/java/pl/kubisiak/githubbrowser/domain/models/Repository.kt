package pl.kubisiak.githubbrowser.domain.models

class Repository(
	val name: String,
	val url: String,
) {
	override fun toString(): String {
		return "Repository: $name"
	}
}