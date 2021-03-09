package pl.kubisiak.githubbrowser

import android.app.Application
import pl.kubisiak.githubbrowser.dagger.DaggerApplicationGraphImpl
import pl.kubisiak.githubbrowser.ui.dagger.ApplicationGraph

class GithubBrowserApplication : Application() {

	override fun onCreate() {
		super.onCreate()
		instance = this
		ApplicationGraph.instance = DaggerApplicationGraphImpl.create()
	}

	companion object {
		lateinit var instance: GithubBrowserApplication
	}
}