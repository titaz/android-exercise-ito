package lt.ito.mycooking.di.components

import androidx.appcompat.app.AppCompatActivity
import dagger.BindsInstance
import dagger.Component
import lt.ito.components.schedulers.SchedulerProvider
import lt.ito.mycooking.MainActivity
import lt.ito.mycooking.di.scopes.ActivityScope

@ActivityScope
@Component(
    dependencies = [
        AppComponent::class
    ]
)
interface ActivityComponent: AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance fun activity(activity: AppCompatActivity): Builder
        fun appComponent(component: AppComponent): Builder
        fun build(): ActivityComponent
    }

    fun inject(mainActivity: MainActivity)
}