package pl.kubisiak.githubbrowser.ui.databinding

import androidx.annotation.LayoutRes
import pl.kubisiak.githubbrowser.ui.base.BaseViewModel

interface DataTemplateSelector {
	@LayoutRes
	fun viewModelToLayout(vm: BaseViewModel): Int
}