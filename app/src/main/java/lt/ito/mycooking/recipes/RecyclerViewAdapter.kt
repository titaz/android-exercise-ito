package lt.ito.mycooking.recipes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import it.ito.mycooking.R
import kotlinx.android.synthetic.main.item_recipe.view.*
import kotlinx.android.synthetic.main.item_recipes_sectioned.view.*
import lt.ito.components.base.ContentItem
import lt.ito.components.base.RecyclerViewItem
import lt.ito.components.base.SectionItem
import lt.ito.models.Recipe
import lt.ito.models.color

const val VIEW_TYPE_SECTION = 1
const val VIEW_TYPE_ITEM = 2

class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>()  {


        var data = listOf<RecyclerViewItem>()

        override fun getItemViewType(position: Int): Int {
            if (data[position] is SectionItem) {
                return VIEW_TYPE_SECTION
            }
            return VIEW_TYPE_ITEM
        }

        override fun getItemCount(): Int {
            return data.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            if (viewType == VIEW_TYPE_SECTION) {
                return SectionViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.item_recipes_sectioned, parent, false)
                )
            }
            return ContentViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
            )
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val item = data[position]
            if (holder is SectionViewHolder && item is SectionItem) {
                holder.bind(item)
            }
            if (holder is ContentViewHolder && item is ContentItem) {
                holder.itemView.setOnClickListener { item.onCLickListener.onClick(position, item.recipe) }
                holder.bind(item)
            }
        }

        internal inner class SectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bind(item: SectionItem) {
                itemView?.sectionText.text = item.title
            }
        }

        internal inner class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bind(item: ContentItem) {
                itemView.imageView.setImageURI(item.recipe.imageUrl)
                itemView.titleView.text = item.recipe.title
                itemView.difficultyView.text = item.recipe.difficulty.name
                itemView.difficultyView.setTextColor(item.recipe.difficulty.color)
            }
        }
    fun setValues(listItems: List<RecyclerViewItem>) {
        data = listItems
        notifyDataSetChanged()
    }
    }