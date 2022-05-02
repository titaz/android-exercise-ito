package lt.ito.mycooking.utils

import it.ito.mycooking.R
import lt.ito.models.CookingResult
import lt.ito.models.Difficulty

val CookingResult.stringResId: Int
    get() {
        when (this) {
            CookingResult.TOTAL_DISASTER -> return R.string.total_disaster
            CookingResult.IT_WAS_EDIBLE -> return R.string.it_was_edible
            CookingResult.PERFECTION -> return R.string.perfection
        }
    }
val Difficulty.stringResId: Int
    get() {
        when (this) {
            Difficulty.EASY -> return R.string.easy
            Difficulty.NORMAL -> return R.string.normal
            Difficulty.HARD -> return R.string.hard
        }
    }

val Throwable.stringResId: Int
    get() {
        return R.string.error_generic
    }

