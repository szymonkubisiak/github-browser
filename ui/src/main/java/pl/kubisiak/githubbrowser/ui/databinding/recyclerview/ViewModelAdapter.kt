package pl.kubisiak.githubbrowser.ui.databinding.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableList
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import pl.kubisiak.githubbrowser.ui.base.BaseViewModel
import pl.kubisiak.githubbrowser.ui.databinding.DataTemplateSelector

open class ViewModelAdapter(protected open val items: List<BaseViewModel>, private val dts: DataTemplateSelector) : RecyclerView.Adapter<ViewModelViewHolder>() {

	override fun getItemCount(): Int =
		items.size

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewModelViewHolder {
		val inflater = LayoutInflater.from(parent.context)
		val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, viewType, parent, false)
		//TODO: set appropriate lifecycle owner
		val lifecycleOwner = parent.context as? LifecycleOwner
		lifecycleOwner?.also {
			binding.lifecycleOwner = it
		}
		return ViewModelViewHolder(binding)
	}

	override fun onBindViewHolder(holder: ViewModelViewHolder, position: Int) {
		holder.bind(items[position])
	}

	override fun getItemViewType(position: Int): Int {
		val item = items[position]
		val retval = dts.viewModelToLayout(item)
		return retval
	}
}



class ViewModelObserverAdapter(override val items: ObservableList<BaseViewModel>, dts: DataTemplateSelector) : ViewModelAdapter(items, dts) {
	private val eventTranslator = ListChangedEventTranslator<BaseViewModel>(this)

	fun isListSame(newItems: ObservableList<BaseViewModel>?): Boolean {
		return newItems === items
	}

	init {
		items.addOnListChangedCallback(eventTranslator)
	}

//TODO: research using onAttached instead of init
//    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
//        items.addOnListChangedCallback(eventTranslator)
//    }

	override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
		items.removeOnListChangedCallback(eventTranslator)
	}
}

/**
 * ListChangedEventTranslator
 * This class implements ObservableList.OnListChangedCallback and translates its "content changed" events to corresponding RecyclerView events.
 */
//TODO: Is there a possibility that the translator+adapter could be left referenced by the ObservableList even after RecyclerView is no more? Research if we need weak observing here to contain a leak.
internal class ListChangedEventTranslator<T>(private val adapter: RecyclerView.Adapter<*>) : ObservableList.OnListChangedCallback<ObservableList<T>>() {
	override fun onChanged(sender: ObservableList<T>?) {
		adapter.notifyDataSetChanged()
	}

	override fun onItemRangeChanged(sender: ObservableList<T>?, positionStart: Int, itemCount: Int) {
		adapter.notifyItemRangeChanged(positionStart, itemCount)
	}

	override fun onItemRangeInserted(sender: ObservableList<T>?, positionStart: Int, itemCount: Int) {
		adapter.notifyItemRangeInserted(positionStart, itemCount)
	}

	override fun onItemRangeMoved(sender: ObservableList<T>?, fromPosition: Int, toPosition: Int, itemCount: Int) {
		if (itemCount == 1)
			adapter.notifyItemMoved(fromPosition, toPosition)
		else
			adapter.notifyDataSetChanged()
	}

	override fun onItemRangeRemoved(sender: ObservableList<T>?, positionStart: Int, itemCount: Int) {
		adapter.notifyItemRangeRemoved(positionStart, itemCount)
	}
}