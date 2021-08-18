package lt.ito.components.attempts

import lt.ito.components.base.BasePresenter
import lt.ito.components.respositories.DishesRepository
import lt.ito.components.schedulers.SchedulerProvider
import timber.log.Timber
import javax.inject.Inject

class AttemptsPresenter @Inject constructor(
    private val dishesRepository: DishesRepository,
    private val schedulerProvider: SchedulerProvider
) : BasePresenter<AttemptsContract>() {

    fun onAttach() {
        subscriptions.add(
            dishesRepository.getDishes()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({
                    if (it.isEmpty()) {
                        view?.displayNoDishes()
                    } else {
                        view?.displayDishes(it)
                    }
                }, {
                    Timber.e(it, "Error loading dishes")
                })
        )
    }
}