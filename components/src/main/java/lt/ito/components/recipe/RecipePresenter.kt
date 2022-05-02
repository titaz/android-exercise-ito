package lt.ito.components.recipe

import lt.ito.components.base.BasePresenter
import lt.ito.components.respositories.DishesRepository
import lt.ito.components.respositories.RecipesRepository
import lt.ito.components.schedulers.SchedulerProvider
import lt.ito.components.test.IdlingResourceCountable
import lt.ito.models.Dish
import lt.ito.models.Recipe
import javax.inject.Inject

class RecipePresenter @Inject constructor(
    private val idlingResourceCountable: IdlingResourceCountable,
    private val recipesRepository: RecipesRepository,
    private val dishesRepository: DishesRepository,
    private val schedulerProvider: SchedulerProvider
) : BasePresenter<RecipeContract>() {

    fun load(recipe: Recipe) {
        subscriptions.add(
            recipesRepository.getRecipe(recipe)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe { view?.showProgress(); idlingResourceCountable.increase() }
                .doFinally { view?.hideProgress(); idlingResourceCountable.decrease() }
                .subscribe(
                    { view?.showRecipe(it) },
                    { view?.showError(it) })
        )
    }

    fun saveAttempt(dish: Dish) {
        subscriptions.add(
            dishesRepository.add(dish)
                .subscribe({}, { view?.showError(it) })
        )
    }
}