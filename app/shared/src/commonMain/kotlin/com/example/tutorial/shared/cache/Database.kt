package com.example.tutorial.shared.cache

import com.example.tutorial.shared.entity.Todo

internal class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.appDatabaseQueries

    internal fun clearDatabase() {
        dbQuery.transaction {
            dbQuery.removeAllTodo()
        }
    }

    internal fun getAllTodos(): List<Todo> {
        return dbQuery.selectAllTodo().executeAsList().map { Todo(todo = it) }
    }
    
    internal fun createTodo(todos: List<Todo>) {
        dbQuery.transaction {
            todos.forEach {
                insertTodo(it)
            }
        }
    }

    private fun insertTodo(todo_to_input: Todo) {
        dbQuery.insertTodo(
            todo = todo_to_input.todo
        )
    }
}