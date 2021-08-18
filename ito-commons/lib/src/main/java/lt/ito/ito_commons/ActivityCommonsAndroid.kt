package lt.ito.ito_commons

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager

class ActivityCommonsAndroid(
    private val activityContext: Activity
) : ActivityCommons {

    override fun hideKeyboard() {
        val imm = activityContext.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        val view = activityContext.currentFocus
        if (view != null) {
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun hideKeyboardFromView(inputView: View) {
        val imm = activityContext.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(inputView.windowToken, 0)
    }
}