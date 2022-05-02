package lt.ito.my


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import it.ito.mycooking.R
import kotlinx.android.synthetic.main.controller_recipes.view.*
import lt.ito.components.base.OnItemClickListener
import lt.ito.components.recipes.RecipesContract
import lt.ito.components.recipes.RecipesPresenter
import lt.ito.models.Recipe
import lt.ito.mycooking.ITOApplication
import lt.ito.mycooking.base.BaseFragment
import lt.ito.components.base.RecyclerViewItem
import lt.ito.mycooking.recipes.RecyclerViewAdapter
import lt.ito.components.base.SectionItem
import lt.ito.models.Difficulty
import javax.inject.Inject


class RecipesFragment : BaseFragment(), RecipesContract, OnItemClickListener<Recipe> {

    private var adapter: RecyclerViewAdapter? = null

    @Inject
    lateinit var recipesPresenter: RecipesPresenter

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup?): View {
        ITOApplication.appComponent(activity?.applicationContext!!).inject(this)
        return inflater.inflate(R.layout.controller_recipes, container, false)
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
        recipesPresenter.view = this
        recipesPresenter.onAttach()
    }

    override fun onStop() {
        super.onStop()
        recipesPresenter.view = null
    }

    override fun updateDataList(recipes: List<Recipe>) {

        val dataSet = ArrayList<RecyclerViewItem>()

            dataSet.add(SectionItem("EASY"))
            recipesPresenter.createSectionsList(dataSet,Difficulty.EASY,recipes,this)
            dataSet.add(SectionItem("Normal"))
            recipesPresenter.createSectionsList(dataSet,Difficulty.NORMAL,recipes,this)
            dataSet.add(SectionItem("Hard"))
            recipesPresenter.createSectionsList(dataSet,Difficulty.HARD,recipes,this)

        adapter?.setValues(dataSet)
    }

    override fun onClick(position: Int, item: Recipe) {
        findNavController()
            .navigate(RecipesFragmentDirections.navigateToRecipe(item))
    }

}