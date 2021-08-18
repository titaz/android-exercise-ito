package lt.ito.mycooking.component

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View

open class CustomDialog(
    context: Context,
    themeResId: Int,
    layoutResId: Int,
    private val customDialogListener: CustomDialogListener?
) : Dialog(context, themeResId) {

    var view: View = layoutInflater.inflate(layoutResId, null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(view)
        customDialogListener?.onCreate(view, this)
    }

    fun showDialog(): CustomDialog {
        super.show()
        return this
    }
}

interface CustomDialogListener {
    fun onCreate(view: View, dialog: CustomDialog)

}