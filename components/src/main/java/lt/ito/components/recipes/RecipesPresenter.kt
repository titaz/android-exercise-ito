package lt.ito.components.recipes


import lt.ito.components.base.BasePresenter
import lt.ito.components.respositories.RecipesRepository
import lt.ito.components.schedulers.SchedulerProvider
import lt.ito.components.test.IdlingResourceCountable
import javax.inject.Inject


class RecipesPresenter @Inject constructor(
    private val idlingResourceCountable: IdlingResourceCountable,
    private val recipesRepository: RecipesRepository,
    private val schedulerProvider: SchedulerProvider
) : BasePresenter<RecipesContract>() {

    fun onAttach() {
        loadData()
    }

    private fun loadData() {
        subscriptions.add(
            recipesRepository.getRecipes()
                .subscribeOn(schedulerProvider.io())
                .doOnSubscribe { idlingResourceCountable.increase() }
                .doFinally { idlingResourceCountable.decrease() }
                .observeOn(schedulerProvider.ui())
                .subscribe(
                    { view?.updateDataList(it) },
                    { view?.showError(it) })
        )
    }
}
