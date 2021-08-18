package lt.ito.mycooking.attempts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.facebook.drawee.view.SimpleDraweeView
import it.ito.mycooking.R
import kotlinx.android.synthetic.main.item_recipe.view.*
import lt.ito.components.base.OnItemClickListener
import lt.ito.models.Dish
import lt.ito.mycooking.utils.stringResId

class AttemptsAdapter(
    private val onItemClickListener: OnItemClickListener<Dish>
) : RecyclerView.Adapter<AttemptsAdapter.AttemptHolder>() {


    private val dishesList: MutableList<Dish> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttemptHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        return AttemptHolder(v)
    }

    override fun getItemCount(): Int = dishesList.size

    override fun onBindViewHolder(holder: AttemptHolder, position: Int) {
        val dataItem = dishesList[position]
        holder.imageView.setImageURI(dataItem.recipe.imageUrl)
        holder.titleView.text = dataItem.recipe.text
        holder.difficultyView.setText(dataItem.result.stringResId)
        holder.itemView.setOnClickListener { onItemClickListener.onClick(position, dataItem) }
    }

    fun setValues(newRecipesList: List<Dish>) {
        dishesList.clear()
        dishesList.addAll(newRecipesList)
        notifyDataSetChanged()
    }

    inner class AttemptHolder(v: View) : RecyclerView.ViewHolder(v) {
        internal var imageView: SimpleDraweeView = v.imageView
        internal var titleView: TextView = v.titleView
        internal var difficultyView: TextView = v.difficultyView
    }
}
