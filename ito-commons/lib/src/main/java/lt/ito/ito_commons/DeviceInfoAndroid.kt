package lt.ito.ito_commons

import android.content.Context
import android.graphics.Point
import android.view.WindowManager


class DeviceInfoAndroid(
    private val context: Context
) : DeviceInfo {
    override fun screenSize(): DeviceInfo.ScreenSize {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val size = Point()
        display.getSize(size)
        return DeviceInfo.ScreenSize(width = size.x, height = size.y)
    }

    override fun osVersion(): String {
        return android.os.Build.VERSION.RELEASE
    }

    override fun sdkVersion(): Int {
        return android.os.Build.VERSION.SDK_INT
    }

    override fun model(): String {
        return android.os.Build.MODEL
    }

    override fun manufacturer(): String {
        return android.os.Build.MANUFACTURER
    }
}