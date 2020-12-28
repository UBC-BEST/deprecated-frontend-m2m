package com.example.tutorial.androidApp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.auth0.android.Auth0
import com.auth0.android.provider.WebAuthProvider
import com.example.tutorial.androidApp.databinding.ActivityMainBinding
import com.example.tutorial.shared.Greeting
import com.example.tutorial.shared.M2MSDK
import com.example.tutorial.shared.cache.DatabaseDriverFactory
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {
    private val sdk = M2MSDK(DatabaseDriverFactory(this))
    private val mainScope = MainScope();
    private lateinit var todoRecyclerView: RecyclerView
    private lateinit var progressBarView: FrameLayout
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private val todoRvAdapter = TodoRvAdapter(listOf())
    var binding: ActivityMainBinding? = null

    override fun onDestroy() {
        super.onDestroy()
        mainScope.cancel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = "M2M"
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // get reference to todoList
        todoRecyclerView = findViewById(R.id.todoListRv)
        progressBarView = findViewById(R.id.progressBar)
        swipeRefreshLayout = findViewById(R.id.swipeContainer)

        // set loggedIn to false
        binding?.loggedIn = false

        todoRecyclerView.adapter = todoRvAdapter
        todoRecyclerView.layoutManager = LinearLayoutManager(this)

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            displayToDo(true)
        }
        displayToDo(false)

        // trigger login when login is pressed
        val loginButton = findViewById<Button>(R.id.login_button)
        loginButton.setOnClickListener{ login() }
    }

    private fun login() {
        val account = Auth0(getString(R.string.auth0_client_id), getString(R.string.auth0_domain))
    }

    // auth0 triggers intent on successful login
    override fun onNewIntent(intent: Intent?) {
        if (WebAuthProvider.resume(intent)) {
            return
        }
        super.onNewIntent(intent)
    }

    private fun displayToDo(needReload: Boolean) {
        progressBarView.isVisible = true
        mainScope.launch {
            kotlin.runCatching {
                sdk.getItems(needReload)
            }.onSuccess {
                todoRvAdapter.todos = it
                todoRvAdapter.notifyDataSetChanged()
            }.onFailure {
                Toast.makeText(this@MainActivity, it.localizedMessage, Toast.LENGTH_SHORT).show()
            }
            progressBarView.isVisible = false
        }
    }
}
