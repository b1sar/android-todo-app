package com.cebrailyilmaz.todoapplication.ui.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.cebrailyilmaz.todoapplication.R
import com.cebrailyilmaz.todoapplication.data.Todo
import com.cebrailyilmaz.todoapplication.databinding.HomeFragmentBinding
import com.cebrailyilmaz.todoapplication.ui.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

//TODO: Sağa doğru çekince pinleme tuşu gelsin [Feature Request]a
//TODO: Sola doğru çekince silme ve arşivleme tuşları gelsin []
@AndroidEntryPoint
class HomeFragment : Fragment(), HomeListAdapter.OnItemClickListener {

    val viewModel: SharedViewModel by activityViewModels()
    private lateinit var homeFragmentBinding: HomeFragmentBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeFragmentBinding = HomeFragmentBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return homeFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeBindings()
    }

    private fun initializeBindings() {
        val theAdapter = HomeListAdapter(viewModel, this)
        theAdapter.submitList(viewModel.todos.value)
        homeFragmentBinding.apply {
            floatingActionButton.setOnClickListener { onGoToNoteButtonClicked() }
            todosCycler.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = theAdapter
                this.invalidate()
            }
        }
        viewModel.todos.observe(viewLifecycleOwner) { todos ->
            //update the recyclerview adapter on data change
            todos.let { theAdapter.submitList(it) }
        }
    }

    private fun onGoToNoteButtonClicked() {
        viewModel.newTodo()
        val action = HomeFragmentDirections.actionHomeFragmentToUpdateFragment()
        findNavController().navigate(action)
    }

    private fun onDeleteAllTodosButtonClicked() {
        viewModel.deleteAllTodos()
    }

    override fun onClick(todo: Todo) {
        viewModel.setCurrentTodo(todo)
        val action = HomeFragmentDirections.actionHomeFragmentToUpdateFragment()
        findNavController().navigate(action)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.delete_all_option -> {
                onDeleteAllTodosButtonClicked()
                true
            }
            else -> false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_option_menu, menu)
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}