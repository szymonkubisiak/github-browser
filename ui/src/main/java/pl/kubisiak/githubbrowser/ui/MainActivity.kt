package pl.kubisiak.githubbrowser.ui

import android.os.Bundle
import pl.kubisiak.githubbrowser.ui.base.BaseActivity
import pl.kubisiak.githubbrowser.ui.R

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            replaceFragment(MainFragment.newInstance(), false)
        }
    }
}
