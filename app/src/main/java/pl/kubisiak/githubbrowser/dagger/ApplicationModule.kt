package pl.kubisiak.githubbrowser.dagger

import android.content.Context
import dagger.Module
import dagger.Provides
import pl.kubisiak.githubbrowser.GithubBrowserApplication

@Module
class ApplicationModule {
	@Provides
	fun provideContext(): Context {
		return GithubBrowserApplication.instance.applicationContext
	}
}