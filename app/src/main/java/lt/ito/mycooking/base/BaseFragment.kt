package lt.ito.mycooking.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import lt.ito.components.base.BaseScreenContract
import lt.ito.mycooking.component.ProgressDialog
import lt.ito.mycooking.utils.stringResId

abstract class BaseFragment : Fragment(), BaseScreenContract {
    private val progress : ProgressDialog by lazy { ProgressDialog(activity as Context) }
    protected abstract fun inflateView(inflater: LayoutInflater, container: ViewGroup?): View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflateView(inflater, container)
        onViewBound(view)
        return view
    }

    open fun onViewBound(view: View) {

    }

    override fun showError(it: Throwable) {
        Toast.makeText(activity, it.stringResId, Toast.LENGTH_SHORT).show()
    }

    override fun showProgress() {
        progress.show()
    }

    override fun hideProgress() {
        progress.hide()
    }

}