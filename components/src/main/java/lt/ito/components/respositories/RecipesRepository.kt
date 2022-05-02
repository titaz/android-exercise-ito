package lt.ito.components.respositories

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Single
import lt.ito.AppFileUtils
import lt.ito.components.R
import lt.ito.models.Recipe
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RecipesRepository @Inject constructor(
    private val gson: Gson,
    private val appFileUtils: AppFileUtils
) {
    fun getRecipes(): Single<List<Recipe>> {
        return Single.just(
            gson.fromJson<List<Recipe>>(
                appFileUtils.getRawText(R.raw.recipes) ?: "",
                object : TypeToken<List<Recipe>>() {}.type
            )
        )
    }

    fun getRecipe(recipe: Recipe): Single<Recipe> {
        return Single.just(recipe)
    }
}