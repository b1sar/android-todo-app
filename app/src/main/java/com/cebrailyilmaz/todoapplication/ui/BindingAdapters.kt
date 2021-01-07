package com.cebrailyilmaz.todoapplication.ui

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.cebrailyilmaz.todoapplication.data.Todo
import com.cebrailyilmaz.todoapplication.ui.home.HomeFragmentDirections

class BindingAdapters {
    companion object {

        @BindingAdapter("android:fromHomeNavigateToUpdate")
        @JvmStatic
        fun fromHomeNavigateToUpdate(view: View, viewModel: SharedViewModel) {

            view.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToUpdateFragment()
                view.findNavController().navigate(action)
            }

        }
    }
}