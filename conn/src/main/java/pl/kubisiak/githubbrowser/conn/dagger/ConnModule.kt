package pl.kubisiak.githubbrowser.conn.dagger

import dagger.Module

@Module(includes = [HttpModule::class, Bindings::class])
interface ConnModule

