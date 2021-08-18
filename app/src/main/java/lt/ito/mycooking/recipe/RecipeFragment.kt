package lt.ito.mycooking.recipe

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import it.ito.mycooking.R
import kotlinx.android.synthetic.main.controller_recipe.*
import kotlinx.android.synthetic.main.dialog_rate.view.*
import lt.ito.components.recipe.RecipeContract
import lt.ito.components.recipe.RecipePresenter
import lt.ito.models.CookingResult
import lt.ito.models.Dish
import lt.ito.models.Recipe
import lt.ito.models.color
import lt.ito.mycooking.ITOApplication
import lt.ito.mycooking.base.BaseFragment
import lt.ito.mycooking.component.CustomDialog
import lt.ito.mycooking.component.CustomDialogListener
import lt.ito.mycooking.utils.stringResId
import javax.inject.Inject


class RecipeFragment : BaseFragment(), RecipeContract {

    @Inject lateinit var recipePresenter: RecipePresenter

    val args by navArgs<RecipeFragmentArgs>()

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup?): View {
        ITOApplication.appComponent(activity as Context).inject(this)
        return inflater.inflate(R.layout.controller_recipe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showRecipe(args.recipe)
    }

    override fun onStart() {
        super.onStart()
        recipePresenter.view = this
        recipePresenter.load(args.recipe)
    }

    override fun onStop() {
        super.onStop()
        recipePresenter.view = null
    }

    override fun showRecipe(it: Recipe) {
        imageView.setImageURI(args.recipe.imageUrl)
        titleView.text = args.recipe.title
        difficultyView.text = args.recipe.difficulty.name.toLowerCase().capitalize()
        difficultyView.setTextColor(args.recipe.difficulty.color)
        descriptionView.text = args.recipe.text
        closeButton.setOnClickListener { activity?.onBackPressed() }
        rateButton.setOnClickListener { saveAttemptedRecipe() }
        toolbar.setupWithNavController(findNavController())
    }

    private fun saveAttemptedRecipe() {
        context?.let {
            CustomDialog(it, R.style.AlertDialog, R.layout.dialog_rate, object : CustomDialogListener {
                override fun onCreate(view: View, dialog: CustomDialog) {
                    view.dialogView.setOnClickListener { dismiss(view, dialog) }
                    view.containerView.startAnimation(AnimationUtils.loadAnimation(it, R.anim.slide_up))

                    val list = CookingResult.values().map { resources.getString(it.stringResId) }
                    val adapter = ArrayAdapter<String>(it, R.layout.item_attempt_rate, android.R.id.text1, list)
                    view.actionListView.adapter = adapter
                    view.actionListView.setOnItemClickListener(object : AdapterView.OnItemClickListener {
                        override fun onItemClick(p0: AdapterView<*>?, child: View?, position: Int, id: Long) {
                            recipePresenter.saveAttempt(Dish(System.currentTimeMillis(), args.recipe, CookingResult.PERFECTION))
                            dismiss(view, dialog)
                        }
                    })
                }

                private fun dismiss(view: View, dialog: CustomDialog) {
                    view.containerView.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.slide_out_down)
                        .apply {
                            setAnimationListener(object : Animation.AnimationListener {
                                override fun onAnimationRepeat(p0: Animation?) {}

                                override fun onAnimationEnd(p0: Animation?) {
                                    dialog.dismiss()
                                }

                                override fun onAnimationStart(p0: Animation?) {
                                }
                            })
                        })
                }

            }).showDialog()
        }
    }
}