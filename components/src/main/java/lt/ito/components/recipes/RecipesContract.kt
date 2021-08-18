package lt.ito.components.recipes

import lt.ito.components.base.BaseScreenContract
import lt.ito.models.Recipe

interface RecipesContract : BaseScreenContract {

    fun updateDataList(recipes: List<Recipe>)
}