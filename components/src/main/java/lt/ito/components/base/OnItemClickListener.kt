package lt.ito.components.base

interface OnItemClickListener<T> {
    fun onClick(position: Int, item: T)
}