package pl.kubisiak.githubbrowser.ui

import androidx.lifecycle.ViewModelProvider
import pl.kubisiak.githubbrowser.ui.base.BaseFragment
import pl.kubisiak.githubbrowser.ui.base.BaseViewModel
import pl.kubisiak.githubbrowser.ui.R

class MainFragment : BaseFragment() {
	override fun createViewModel(): BaseViewModel {
		return ViewModelProvider(this).get(MainViewModel::class.java)
	}

	override fun getLayoutRes(): Int {
		return R.layout.main_fragment
	}

	companion object {
		fun newInstance() = MainFragment()
	}
}
