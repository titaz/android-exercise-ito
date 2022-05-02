package lt.ito.components.Recommendations


import io.reactivex.Single
import lt.ito.components.base.BasePresenter
import lt.ito.components.base.ContentItem
import lt.ito.components.base.OnItemClickListener
import lt.ito.components.base.RecyclerViewItem
import lt.ito.components.respositories.DishesRepository
import lt.ito.components.respositories.RecipesRepository
import lt.ito.components.schedulers.SchedulerProvider
import lt.ito.components.test.IdlingResourceCountable
import lt.ito.models.CookingResult
import lt.ito.models.Difficulty
import lt.ito.models.Dish
import lt.ito.models.Recipe
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class RecommendationsPresenter @Inject constructor(
    private val idlingResourceCountable: IdlingResourceCountable,
    private val recipesRepository: RecipesRepository,
    private val dishesRepository: DishesRepository,
    private val schedulerProvider: SchedulerProvider
) : BasePresenter<RecommendationsContract>() {
    private val dishes = mutableListOf<Dish>()


    fun onAttach() {
        loadData()
        getDishes()
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


    fun getDishes() {
        subscriptions.add(
            dishesRepository.getDishes()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({
                        view?.displayDishes(it)
                }, {
                    Timber.e(it, "Error loading dishes")
                })
        )
    }
    fun determineSkillLevel(dishes: List<Dish>?,recipes: List<Recipe>?,onClickListener: OnItemClickListener<Recipe>): ArrayList<RecyclerViewItem> {
        var easy =0;
        var normal = 0;
        var hard = 0;

        for(dish in dishes!!){
            when(dish.result){
                CookingResult.PERFECTION -> hard++
                CookingResult.IT_WAS_EDIBLE -> normal++
                CookingResult.TOTAL_DISASTER -> easy++
                }
        }
        var difficultyLevel:Difficulty
        if (easy >= normal && easy >= hard)
            difficultyLevel = Difficulty.EASY
        else if (normal >= easy && normal >= hard)
            difficultyLevel = Difficulty.NORMAL
        else difficultyLevel = Difficulty.HARD

        return createList(difficultyLevel,recipes,onClickListener)
    }
    fun createList(difficulty: Difficulty, recipes: List<Recipe>?, onClickListener: OnItemClickListener<Recipe>): ArrayList<RecyclerViewItem> {

        val list:ArrayList<RecyclerViewItem> =ArrayList()
        var i = 0

        if (recipes != null) {
            for (recipe in recipes) {
                if (recipe.difficulty.name == difficulty.name) {
                    list.add(ContentItem(recipe, onClickListener))
                    i++
                }
            }
        }
        return list
    }

}
