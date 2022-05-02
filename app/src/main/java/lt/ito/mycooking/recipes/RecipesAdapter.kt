package lt.ito.mycooking.recipes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.facebook.drawee.view.SimpleDraweeView
import it.ito.mycooking.R
import kotlinx.android.synthetic.main.item_recipe.view.*
import lt.ito.components.base.OnItemClickListener
import lt.ito.models.Recipe
import lt.ito.models.color
import lt.ito.mycooking.utils.stringResId
class RecipesAdapter(
    private val onItemClickListener: OnItemClickListener<Recipe>
) : RecyclerView.Adapter<RecipesAdapter.RecipesHolder>() {

    private val recipesList: MutableList<Recipe> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        return RecipesHolder(v)
    }

    override fun getItemCount(): Int = recipesList.size

    override fun onBindViewHolder(holder: RecipesHolder, position: Int) {
        val dataItem = recipesList[position]
        holder.imageView.setImageURI(recipesList[if(position == 8) 7 else if(position == 7) 8 else position].imageUrl)
        holder.titleView.text = dataItem.title
        holder.difficultyView.setText(dataItem.difficulty.stringResId)
        holder.difficultyView.setTextColor(dataItem.difficulty.color)
        holder.itemView.setOnClickListener { onItemClickListener.onClick(position, dataItem) }

    }

    fun setValues(newRecipes: List<Recipe>) {
        this.recipesList.clear()
        this.recipesList.addAll(newRecipes)
        notifyDataSetChanged()
    }

    inner class RecipesHolder(v: View) : RecyclerView.ViewHolder(v) {
        internal var imageView: SimpleDraweeView = v.imageView
        internal var titleView: TextView = v.titleView
        internal var difficultyView: TextView = v.difficultyView
    }
}