package pl.kubisiak.githubbrowser.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import pl.kubisiak.githubbrowser.ui.Navigator
import pl.kubisiak.githubbrowser.ui.NavigatorImpl
import pl.kubisiak.githubbrowser.ui.R

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Navigator.instance = NavigatorImpl(this)
    }

    fun replaceFragment(newFragment: BaseFragment, addToBackstack: Boolean = true) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, newFragment, newFragment.generateTag())
            .apply {
                if (addToBackstack) {
                    addToBackStack(null)
                    commitAllowingStateLoss()
                    supportFragmentManager.executePendingTransactions()
                }
                else
                    commitNow()
            }
    }

    fun clearBackStack() {
        val manager = getSupportFragmentManager()
        val backStackEntryCount: Int = manager.getBackStackEntryCount()
        if (backStackEntryCount > 0) {
            val first = manager.getBackStackEntryAt(0)
            val firstId = first.id
            manager.popBackStackImmediate(firstId, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }

    fun goBack(): Boolean {
        val retval = supportFragmentManager.popBackStackImmediate()
        return retval
    }

    override fun onBackPressed() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.container)
        if ((currentFragment as BaseFragment?)?.onBackPressed() == true) {
            return
        }
        super.onBackPressed()
    }
}