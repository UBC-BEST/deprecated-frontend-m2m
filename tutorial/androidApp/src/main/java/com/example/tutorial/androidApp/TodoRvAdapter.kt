package com.example.tutorial.androidApp

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tutorial.shared.entity.Todo

class TodoRvAdapter(var todos: List<Todo>) : RecyclerView.Adapter<TodoRvAdapter.TodoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = todos.count()

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bindData(todos[position])
    }

    inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindData(todo: Todo) {
            val ctx = itemView.context

        }
    }
}

