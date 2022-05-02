package lt.ito.mycooking.di.components

import android.app.Application
import com.google.gson.Gson
import dagger.BindsInstance
import dagger.Component
import lt.ito.AppFileUtils
import lt.ito.components.schedulers.SchedulerProvider
import lt.ito.components.test.IdlingResourceCountable
import lt.ito.my.RecipesFragment
import lt.ito.my.RecommendationsFragment
import lt.ito.mycooking.ITOApplication
import lt.ito.mycooking.UiTestModule
import lt.ito.mycooking.attempts.AttemptsFragment
import lt.ito.mycooking.di.modules.AppModule
import lt.ito.mycooking.di.modules.NetworkModule
import lt.ito.mycooking.di.modules.RepositoryModule
import lt.ito.mycooking.launcher.LauncherActivity
import lt.ito.mycooking.recipe.RecipeFragment
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AppModule::class,
        RepositoryModule::class,
        UiTestModule::class,
        NetworkModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun idlingResourceCounter(): IdlingResourceCountable
    fun schedulerProvider(): SchedulerProvider
    fun provideGson(): Gson
    fun provideAppFileUtils(): AppFileUtils

    fun inject(app: ITOApplication)
    fun inject(recipesController: RecipesFragment)
    fun inject(recommendationsController: RecommendationsFragment)
    fun inject(attemptsController: AttemptsFragment)
    fun inject(recipeController: RecipeFragment)
    fun inject(launcherActivity: LauncherActivity)






}