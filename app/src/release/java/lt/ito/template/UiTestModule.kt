package lt.ito.template

import dagger.Module
import dagger.Provides
import lt.ito.template.test.IdlingResourceCountable
import lt.ito.template.test.IdlingResourceEmpty
import javax.inject.Singleton

@Module
class UiTestModule {

    @Provides
    @Singleton
    fun provideIdlingCounter(): IdlingResourceCountable {
        return IdlingResourceEmpty()
    }

}