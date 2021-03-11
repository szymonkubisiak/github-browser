package pl.kubisiak.githubbrowser.ui.databinding

import pl.kubisiak.githubbrowser.ui.ItemRepositoryViewModel
import pl.kubisiak.githubbrowser.ui.R
import pl.kubisiak.githubbrowser.ui.base.BaseViewModel

object DefaultDataTemplateSelector : DataTemplateSelector {
	override fun viewModelToLayout(vm: BaseViewModel): Int {
		return when (vm) {
			is ItemRepositoryViewModel -> R.layout.item_repository
			else -> -1
		}
	}
}
