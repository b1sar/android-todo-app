package com.cebrailyilmaz.todoapplication.ui.home


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cebrailyilmaz.todoapplication.data.Todo
import com.cebrailyilmaz.todoapplication.databinding.ItemTodoBinding
import com.cebrailyilmaz.todoapplication.ui.SharedViewModel

class HomeListAdapter(val sharedViewModel: SharedViewModel, val itemClickListener: OnItemClickListener) : ListAdapter<Todo, HomeListAdapter.HomeViewHolder>(
        DIFF_CALLBACK) {

    interface OnItemClickListener {
        fun onClick(todo: Todo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(getItem(position),sharedViewModel, itemClickListener)
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Todo> = object : DiffUtil.ItemCallback<Todo>() {

            override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
                return oldItem == newItem
            }
        }
    }

    class HomeViewHolder(val itemBinding: ItemTodoBinding) :
            RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(currentTodo: Todo, sharedViewModel: SharedViewModel, itemClickListener: OnItemClickListener) {
            itemBinding.apply {
                this.sharedViewModel = sharedViewModel
                this.handler = HomeFragmentHandlers()
                this.currentTodo = currentTodo
                this.title.text = currentTodo.title
                this.content.text = currentTodo.content
                this.dateModified.text = currentTodo.dateLastModified.toString()
                this.root.setOnClickListener { itemClickListener.onClick(currentTodo) }
            }
        }

        companion object {
            fun create(parent: ViewGroup): HomeViewHolder {
                return HomeViewHolder(
                        ItemTodoBinding.inflate(
                                LayoutInflater.from(parent.context),
                                parent,
                                false
                        )
                )
            }
        }
    }
}