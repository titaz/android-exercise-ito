package lt.ito.components.Recommendations

import lt.ito.components.base.BaseScreenContract
import lt.ito.models.Dish
import lt.ito.models.Recipe

interface RecommendationsContract : BaseScreenContract {

    fun updateDataList(recipes: List<Recipe>)
     fun displayDishes(it: List<Dish>?)
}