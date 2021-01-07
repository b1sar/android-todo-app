package com.cebrailyilmaz.todoapplication.ui.update


import android.content.Context
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.cebrailyilmaz.todoapplication.databinding.FragmentUpdateBinding
import com.cebrailyilmaz.todoapplication.ui.SharedViewModel
import java.util.logging.Logger


//TODO: When navigating back with the navigation icon, keybord is not autohided, fix that
//TODO: Instead of EditText, use a RelativeLayout and align items in it, in order to add the ability to
//add checkboxes, tables, and images to the content.
class UpdateFragment : Fragment() {
    private val LOGGER = Logger.getLogger(javaClass.name)

    lateinit var updateBinding: FragmentUpdateBinding
    private val viewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        updateBinding = FragmentUpdateBinding.inflate(LayoutInflater.from(this.context), container, false)

        val currentTodo = viewModel.currentTodo.value
        val toastMessage = "id:${currentTodo?.id} titile:${currentTodo?.title} content:${currentTodo?.content} "
        Toast.makeText(this.context, toastMessage, Toast.LENGTH_SHORT).show()

        setNavigation()
        setBindingValues()
        setKeyboardDecorToFitSystemWindows()
        return updateBinding.root
    }

    private fun setNavigation() {
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        updateBinding.toolbarUpdate.setupWithNavController(navController, appBarConfiguration)
    }

    private fun setBindingValues() {
        updateBinding.apply {
            this.viewmodel = viewModel
            this.handlers = UpdateFragmentHandlers()
            this.editContent.apply {
                customSelectionActionModeCallback = StyleCallBack(this)
                backgroundTintMode = PorterDuff.Mode.CLEAR//Remove the activation indicators
                
                onFocusChangeListener = keyboardFocusChangeListener()
            }
        }
    }

    private fun setKeyboardDecorToFitSystemWindows() {
        val window = requireActivity().window
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(true)
        } else {
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        }
    }

    private fun keyboardFocusChangeListener(): View.OnFocusChangeListener =
            object : View.OnFocusChangeListener {
                override fun onFocusChange(v: View?, hasFocus: Boolean) {
                    if (hasFocus) {
                        updateBinding.saveAction.visibility = View.VISIBLE
                        updateBinding.copyAction.visibility = View.GONE
                    } else {
                        hideKeyboard()
                        updateBinding.saveAction.visibility = View.GONE
                        updateBinding.copyAction.visibility = View.VISIBLE
                    }
                }
            }

    private fun hideKeyboard() {
        val view = requireActivity().currentFocus
        view?.let { v ->
            val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }
}