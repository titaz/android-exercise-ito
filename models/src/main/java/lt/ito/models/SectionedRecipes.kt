package lt.ito.models

import lt.ito.models.Recipe

class SectionedRecipes(sourceName: String?) {
    private var sourceName: String? = sourceName
    private var recipesList: ArrayList<Recipe>? = ArrayList()


    fun getSourceName(): String? {
        return sourceName
    }

    fun getRecipesList(): List<Recipe>? {
        return recipesList
    }

    fun addItem(recipe: Recipe) {
        recipesList?.add(recipe)
    }
}
