package lt.ito.mycooking.di.modules

import android.app.Application
import dagger.Module
import dagger.Provides
import lt.ito.AppFileUtils
import lt.ito.components.schedulers.SchedulerProvider
import lt.ito.mycooking.ITOApplication
import lt.ito.mycooking.schedulers.AppSchedulerProvider
import lt.ito.mycooking.utils.AppFileUtilsImpl
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideAppContext(
        application: Application
    ): ITOApplication = application as ITOApplication

    @Singleton
    @Provides
    fun providesSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }

    @Singleton
    @Provides
    fun provideAppFileUtils(context: ITOApplication): AppFileUtils {
        return AppFileUtilsImpl(context);
    }
}