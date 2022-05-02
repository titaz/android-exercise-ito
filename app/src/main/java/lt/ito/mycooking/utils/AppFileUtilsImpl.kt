package lt.ito.mycooking.utils

import android.content.Context
import lt.ito.AppFileUtils
import javax.inject.Inject

class AppFileUtilsImpl @Inject constructor(private val context: Context) : AppFileUtils {
    override fun getRawText(resId: Int): String? {
        return context.resources.openRawResource(resId).bufferedReader().use { it.readText() }
    }
}