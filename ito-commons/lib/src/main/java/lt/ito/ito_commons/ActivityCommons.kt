package lt.ito.ito_commons

import android.view.View

interface ActivityCommons {

    /**
     * Tries to hide keyboard if available
     */
    fun hideKeyboard()

    /**
     * Tries to hide keyboard when focused on a concrete view
     */
    fun hideKeyboardFromView(inputView: View)
}