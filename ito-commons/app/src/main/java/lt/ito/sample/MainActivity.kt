package lt.ito.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import lt.ito.ito_commons.ActivityCommons
import lt.ito.ito_commons.ActivityCommonsAndroid
import lt.ito.ito_commons.AppInfoAndroid
import lt.ito.ito_commons.DeviceInfoAndroid
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val activityCommons: ActivityCommons = ActivityCommonsAndroid(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        view_button_hide_keyboard.setOnClickListener {
            activityCommons.hideKeyboard()
        }

        logDeviceInfo()
        logAppInfo()
    }

    private fun logDeviceInfo() {
        val deviceInfo = DeviceInfoAndroid(this)
        Timber.i("Os version: ${deviceInfo.osVersion()}")
        Timber.i("Model : ${deviceInfo.model()}")
        Timber.i("Manufacturer: ${deviceInfo.manufacturer()}")
        Timber.i("SDK version: ${deviceInfo.sdkVersion()}")
        Timber.i("Screen width: ${deviceInfo.screenSize().width}, Screen height: ${deviceInfo.screenSize().height}")
    }

    private fun logAppInfo() {
        val appInfo = AppInfoAndroid()
        Timber.i("App id: ${appInfo.appId()}")
        Timber.i("App version: ${appInfo.version()}")
        Timber.i("Product flavour: ${appInfo.productFlavour()}")
        Timber.i("Build type: ${appInfo.buildType()}")
    }

}
