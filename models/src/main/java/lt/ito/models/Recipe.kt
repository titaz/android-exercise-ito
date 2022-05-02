package lt.ito.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import androidx.room.Entity
import androidx.room.PrimaryKey

@Parcelize
data class Recipe(
    val id: String,
    val title: String,
    val imageUrl: String,
    val text: String,
    val difficulty: Difficulty): Parcelable