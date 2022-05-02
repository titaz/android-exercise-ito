package lt.ito.components.base

import android.view.View
import lt.ito.models.Recipe

open class RecyclerViewItem
class SimpleTextItem(val text: String) : RecyclerViewItem()
class SectionItem(val title: String) : RecyclerViewItem()
class ContentItem(val recipe:Recipe, val onCLickListener:OnItemClickListener<Recipe>) : RecyclerViewItem()
