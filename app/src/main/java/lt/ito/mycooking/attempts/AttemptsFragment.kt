package lt.ito.mycooking.attempts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import it.ito.mycooking.R
import kotlinx.android.synthetic.main.controller_attempts.view.*
import lt.ito.components.attempts.AttemptsContract
import lt.ito.components.attempts.AttemptsPresenter
import lt.ito.components.base.OnItemClickListener
import lt.ito.models.Dish
import lt.ito.my.RecipesFragmentDirections
import lt.ito.mycooking.ITOApplication
import lt.ito.mycooking.base.BaseFragment
import javax.inject.Inject

class AttemptsFragment : BaseFragment(), AttemptsContract, OnItemClickListener<Dish> {

    @Inject lateinit var attemptsPresenter: AttemptsPresenter

    private val adapter: AttemptsAdapter = AttemptsAdapter(this)

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup?): View {
        ITOApplication.appComponent(activity?.application!!).inject(this)
        return inflater.inflate(R.layout.controller_attempts, container, false)
    }

    override fun onViewBound(view: View) {
        val llm = LinearLayoutManager(activity)
        val dividerItemDecoration = DividerItemDecoration(activity, llm.orientation)
        view.recyclerView.adapter = adapter
        view.recyclerView.layoutManager = llm
        view.recyclerView.addItemDecoration(dividerItemDecoration)
    }

    override fun onStart() {
        super.onStart()
        attemptsPresenter.view = this
        attemptsPresenter.onAttach()
    }

    override fun onStop() {
        super.onStop()
        attemptsPresenter.view = null
    }

    override fun displayDishes(it: List<Dish>) {
        adapter.setValues(it)
        adapter.notifyDataSetChanged()
        view?.recyclerView?.visibility =  View.VISIBLE
        view?.emptyView?.visibility = View.INVISIBLE
    }

    override fun displayNoDishes() {
        view?.recyclerView?.visibility = View.INVISIBLE
        view?.emptyView?.visibility = View.VISIBLE
    }

    override fun onClick(position: Int, item: Dish) {
        findNavController()
            .navigate(RecipesFragmentDirections.navigateToRecipe(item.recipe))
    }
}