package lt.ito.mycooking.recipes

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
import javax.inject.Inject


class RecipesFragment : BaseFragment(), RecipesContract, OnItemClickListener<Recipe> {

    private val adapter: RecipesAdapter = RecipesAdapter(this)

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
        view.recyclerView.adapter = adapter
        view.recyclerView.layoutManager = llm
        view.recyclerView.addItemDecoration(dividerItemDecoration)
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
        adapter.setValues(recipes)
    }

    override fun onClick(position: Int, item: Recipe) {
        findNavController()
            .navigate(RecipesFragmentDirections.navigateToRecipe(item))
    }

}