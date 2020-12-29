package com.example.tutorial.shared

import com.example.tutorial.shared.cache.Database
import com.example.tutorial.shared.cache.DatabaseDriverFactory
import com.example.tutorial.shared.entity.Todo
import com.example.tutorial.shared.network.M2MApi

class M2MSDK(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = Database(databaseDriverFactory)
    private val api = M2MApi()

    @Throws(Exception::class) suspend fun getItems(forceReload: Boolean) : List<Todo> {
        val cachedTodos = database.getAllTodos()
        return if (cachedTodos.isNotEmpty() && !forceReload) {
            cachedTodos
        } else {
            api.getItems().also {
                database.clearDatabase()
                database.createTodo(it)
            }
        }
    }

    @Throws(Exception::class) suspend fun addItem(item: String, accessToken: String?) {
        api.addItem(item, accessToken)
    }
}