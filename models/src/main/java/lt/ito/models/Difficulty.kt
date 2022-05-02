package lt.ito.models

import android.graphics.Color
import com.google.gson.annotations.SerializedName

enum class Difficulty {
    @SerializedName("easy")
    EASY ,

    @SerializedName("normal")
    NORMAL,

    @SerializedName("hard")
    HARD;
}

val Difficulty.color: Int
    get() {
        when (this) {
            Difficulty.EASY -> return Color.GREEN
            Difficulty.NORMAL -> return Color.YELLOW
            Difficulty.HARD -> return Color.RED
            else -> return Color.BLACK
        }
    }

val Difficulty.name: String
    get() {
        when (this) {
            Difficulty.EASY -> return "EASY"
            Difficulty.NORMAL -> return "NORMAL"
            Difficulty.HARD -> return "HARD"
            else -> return "NA"
        }
    }
