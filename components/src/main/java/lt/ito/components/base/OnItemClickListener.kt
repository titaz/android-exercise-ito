package lt.ito.components.base

import android.view.View

interface OnItemClickListener<T> {
    fun onClick(position: Int, item: T)
}