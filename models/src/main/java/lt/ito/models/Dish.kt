package lt.ito.models

data class Dish(
    val id: Long,
    val recipe: Recipe,
    val result: CookingResult
)