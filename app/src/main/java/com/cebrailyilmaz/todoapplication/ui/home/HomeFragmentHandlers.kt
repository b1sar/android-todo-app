package com.cebrailyilmaz.todoapplication.ui.home

import android.view.View
import androidx.navigation.findNavController
import com.cebrailyilmaz.todoapplication.data.Todo
import com.cebrailyilmaz.todoapplication.ui.SharedViewModel

class HomeFragmentHandlers {

    fun onNoteClick(view: View, sharedViewModel: SharedViewModel, currentTodo: Todo) {
        sharedViewModel.setCurrentTodo(currentTodo)
        val action = HomeFragmentDirections.actionHomeFragmentToUpdateFragment()
        view.findNavController().navigate(action)
    }
}