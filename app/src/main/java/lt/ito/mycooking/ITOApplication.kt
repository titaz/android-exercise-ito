package lt.ito.mycooking

import android.app.Application
import android.content.Context
import com.facebook.drawee.backends.pipeline.Fresco
import it.ito.mycooking.BuildConfig
import lt.ito.mycooking.di.components.AppComponent
import lt.ito.mycooking.di.components.DaggerAppComponent
import timber.log.Timber

class ITOApplication : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        Fresco.initialize(this)
        component = DaggerAppComponent.builder()
            .application(this)
            .build()
    }

    companion object {
        fun appComponent(context: Context): AppComponent {
            return (context.applicationContext as ITOApplication)
                .component
        }
    }

}