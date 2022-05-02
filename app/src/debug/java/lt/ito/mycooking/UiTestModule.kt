package lt.ito.mycooking

import dagger.Module
import dagger.Provides
import lt.ito.components.test.IdlingResourceCountable
import lt.ito.mycooking.test.IdlingResourceCounting
import javax.inject.Singleton

@Module
class UiTestModule {

    @Provides
    @Singleton
    fun provideIdlingCounter(): IdlingResourceCountable {
        return IdlingResourceCounting()
    }

}