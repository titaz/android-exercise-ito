package lt.ito.models

import com.google.gson.annotations.SerializedName

enum class CookingResult {
    @SerializedName("totalDisaster")
    TOTAL_DISASTER, // User was unable to prepare the dish correctly

    @SerializedName("itWasEdible")
    IT_WAS_EDIBLE,  // User prepared the dish, but it was not completely successful.

    @SerializedName("perfection")
    PERFECTION  // User prepared the dish perfectly.
}