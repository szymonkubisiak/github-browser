package pl.kubisiak.githubbrowser.ui.databinding.recyclerview

import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import pl.kubisiak.githubbrowser.ui.base.BaseViewModel

class ViewModelViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

	fun bind(viewModel: BaseViewModel) {
		binding.setVariable(BR.vm, viewModel)
		binding.executePendingBindings()
	}
}
