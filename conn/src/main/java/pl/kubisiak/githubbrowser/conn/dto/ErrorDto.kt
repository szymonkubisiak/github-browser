package pl.kubisiak.githubbrowser.conn.dto

class ErrorDto {
	var message: String? = null
	var errors: List<SingleError>? = null
	var documentation_url: String? = null

	class SingleError {
		var message: String? = null
		var resource: String? = null
		var field: String? = null
		var code: String? = null
	}
}

//{"message":"Validation Failed","errors":[{"resource":"Search","field":"q","code":"missing"}],"documentation_url":"https://docs.github.com/v3/search"}