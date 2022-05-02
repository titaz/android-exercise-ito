package lt.ito.my


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import it.ito.mycooking.R
import kotlinx.android.synthetic.main.controller_recipes.view.*
import lt.ito.components.Recommendations.RecommendationsContract
import lt.ito.components.Recommendations.RecommendationsPresenter
import lt.ito.components.base.OnItemClickListener
import lt.ito.models.Recipe
import lt.ito.mycooking.ITOApplication
import lt.ito.mycooking.base.BaseFragment
import lt.ito.components.base.RecyclerViewItem
import lt.ito.mycooking.recipes.RecyclerViewAdapter
import lt.ito.components.base.SimpleTextItem
import lt.ito.models.Difficulty
import lt.ito.models.Dish
import javax.inject.Inject


class RecommendationsFragment : BaseFragment(), RecommendationsContract, OnItemClickListener<Recipe> {

    private var adapter: RecyclerViewAdapter? = null

    private var recipes:MutableList<Recipe> = mutableListOf()
    @Inject
    lateinit var recommendationsPresenter: RecommendationsPresenter

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup?): View {
        ITOApplication.appComponent(activity?.applicationContext!!).inject(this)
        return inflater.inflate(R.layout.controller_recommendations, container, false)
    }

    override fun onViewBound(view: View) {
        val llm = LinearLayoutManager(activity?.applicationContext!!)
        val dividerItemDecoration = DividerItemDecoration(
            activity?.applicationContext!!,
            llm.orientation)

        adapter = RecyclerViewAdapter()
        view.rv.adapter = adapter
        view.rv.layoutManager = llm
        view.rv.addItemDecoration(dividerItemDecoration)
    }

    override fun onStart() {
        super.onStart()
        recommendationsPresenter.view = this
        recommendationsPresenter.onAttach()
    }

    override fun onStop() {
        super.onStop()
        recommendationsPresenter.view = null
    }

    override fun updateDataList(recipes: List<Recipe>) {
        this.recipes = recipes.toMutableList()
    }

    fun setAdapter(dataSet:ArrayList<RecyclerViewItem>){
        adapter?.setValues(dataSet)
    }


    override fun displayDishes(it: List<Dish>?) {
        if(it?.size==0){
            setAdapter(recommendationsPresenter.createList(Difficulty.EASY,recipes,this))
        }else
        setAdapter(recommendationsPresenter.determineSkillLevel(it,recipes,this))

    }

    override fun onClick(position: Int, item: Recipe) {
        findNavController()
            .navigate(RecipesFragmentDirections.navigateToRecipe(item))
    }

}