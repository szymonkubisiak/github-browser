package pl.kubisiak.githubbrowser.dagger

import dagger.Component
import pl.kubisiak.githubbrowser.conn.dagger.ConnModule
import pl.kubisiak.githubbrowser.domain.dagger.DomainModule
import pl.kubisiak.githubbrowser.ui.dagger.ApplicationGraph
import javax.inject.Singleton

@Component(modules = [ConnModule::class, DomainModule::class, ApplicationModule::class])
@Singleton
interface ApplicationGraphImpl : ApplicationGraph