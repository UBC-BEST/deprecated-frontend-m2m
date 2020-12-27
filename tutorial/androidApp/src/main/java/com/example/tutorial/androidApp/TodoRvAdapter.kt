package com.example.tutorial.androidApp

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
        private val stringTodo = itemView.findViewById<TextView>(R.id.todo)

        fun bindData(todo_input: Todo) {
            val ctx = itemView.context
            stringTodo.text = ctx.getString(R.string.todo_field, todo_input.todo)
        }
    }
}

