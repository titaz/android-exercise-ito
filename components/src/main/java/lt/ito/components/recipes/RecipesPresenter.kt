package lt.ito.components.recipes


import android.view.View
import lt.ito.components.base.BasePresenter
import lt.ito.components.base.ContentItem
import lt.ito.components.base.OnItemClickListener
import lt.ito.components.base.RecyclerViewItem
import lt.ito.components.respositories.RecipesRepository
import lt.ito.components.schedulers.SchedulerProvider
import lt.ito.components.test.IdlingResourceCountable
import lt.ito.models.Difficulty
import lt.ito.models.Recipe
import lt.ito.models.SectionedRecipes
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

     fun createSectionsList(list:ArrayList<RecyclerViewItem>,difficulty: Difficulty, recipes: List<Recipe>, onClickListener: OnItemClickListener<Recipe>): ArrayList<RecyclerViewItem> {
         var i = list.size
         if(list.size>0){
             i-1
         }

         for (recipe in recipes) {
                 if (recipe.difficulty.name == difficulty.name) {
                     list.add(ContentItem(recipe, onClickListener))
                     i++
                 }
         }
        return list
    }
}
