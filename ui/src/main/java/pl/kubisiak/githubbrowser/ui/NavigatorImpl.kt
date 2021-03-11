package pl.kubisiak.githubbrowser.ui

import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat
import pl.kubisiak.githubbrowser.ui.base.BaseActivity

class NavigatorImpl(val activity: BaseActivity) : Navigator {
	override fun openUrl(url: String) {

		val uri = try {
			 Uri.parse(url)
		} catch (e: Exception) {
			return
		}

		val browserIntent = Intent(Intent.ACTION_VIEW, uri)
		ContextCompat.startActivity(activity, browserIntent, null)
	}
}