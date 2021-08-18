package lt.ito.components.launcher

import io.reactivex.Single
import lt.ito.components.base.BasePresenter
import lt.ito.components.schedulers.SchedulerProvider
import lt.ito.components.test.IdlingResourceCountable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class LauncherPresenter @Inject constructor(
    private var schedulerProvider: SchedulerProvider,
    private var idlingResourceCountable: IdlingResourceCountable
) : BasePresenter<LauncherContract>() {

    fun onAttach() {
        subscriptions.add(
            Single.timer(2, TimeUnit.SECONDS, schedulerProvider.io())
                .subscribeOn(schedulerProvider.io())
                .doOnSubscribe { idlingResourceCountable.increase() }
                .doFinally { idlingResourceCountable.decrease() }
                .observeOn(schedulerProvider.ui())
                .subscribe(
                    { view?.openMainScreen() },
                    { view?.showError(it) })
        )
    }

}