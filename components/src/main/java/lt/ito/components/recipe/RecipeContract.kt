package lt.ito.components.recipe

import lt.ito.components.base.BaseScreenContract
import lt.ito.models.Recipe

interface RecipeContract: BaseScreenContract {
    fun showRecipe(it: Recipe)
}