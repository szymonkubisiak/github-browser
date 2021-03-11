package pl.kubisiak.githubbrowser.ui.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.internal.disposables.DisposableContainer

open class BaseViewModel : ViewModel() {

	private val _disposer = CompositeDisposable()
	val disposer: DisposableContainer
		get() = _disposer


	protected fun subscribeDisposer(masterDisposer: DisposableContainer) {
		masterDisposer.add(this._disposer)
	}

	protected var _isLoading = MutableLiveData(false)
	var isLoading: LiveData<Boolean> = _isLoading

	protected fun subscribeLoader(p: Completable?) {
		p?.also {
			_isLoading.value = true
			disposer.add(
				it.subscribe(
					{ _isLoading.value = false },
					{ ex ->
						_isLoading.value = false
						//TODO: check the exception destination, displaying message is only one of them
						//navigator.showMessage(ex.message ?: "unknown", "Error")
					})
			)
		}
	}

	override fun onCleared() {
		super.onCleared()
		Log.d(TAG, "onCleared: " + javaClass.simpleName)
		_disposer.dispose()
	}

	/*
	return true to suppress back button handling
	return false to invoke default handler
	 */
	open fun goBack(): Boolean {
		//navigator.goBack()
		return true
	}

	protected operator fun DisposableContainer.plusAssign(subscription: Disposable) {
		if (!this.add(subscription))
			throw IllegalStateException("Container already disposed")
	}


	companion object {
		private const val TAG = "BaseViewModel"
	}
}