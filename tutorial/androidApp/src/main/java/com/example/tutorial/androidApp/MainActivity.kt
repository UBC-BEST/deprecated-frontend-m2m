package com.example.tutorial.androidApp

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.provider.AuthCallback
import com.auth0.android.provider.WebAuthProvider
import com.auth0.android.result.Credentials
import com.example.tutorial.androidApp.databinding.ActivityMainBinding
import com.example.tutorial.shared.Greeting
import com.example.tutorial.shared.M2MSDK
import com.example.tutorial.shared.auth.AuthenticationHandler
import com.example.tutorial.shared.auth.CredentialsManager
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

        // add new todo
        val addItemButton = findViewById<Button>(R.id.add_new_todo_button)
        val itemEditText = findViewById<EditText>(R.id.item)
        addItemButton.setOnClickListener{
            val item = itemEditText.text.toString()
            mainScope.launch {
                kotlin.runCatching {
                    sdk.addItem(item, CredentialsManager.getAccessToken())
                }.onSuccess {
                    itemEditText.text.clear()
                    Toast.makeText(this@MainActivity, "Item added", Toast.LENGTH_SHORT).show()
                    displayToDo(true)
                }.onFailure {
                    Toast.makeText(this@MainActivity, "Sorry an error occurred!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun login() {
        val account = Auth0(getString(R.string.com_auth0_client_id), getString(R.string.auth0_domain))
        account.isOIDCConformant = true
        account.isLoggingEnabled = true

        WebAuthProvider.login(account)
            .withScheme(getString(R.string.scheme))
            .withAudience(getString(R.string.audience))
            .start(this@MainActivity, object : AuthCallback {
                override fun onFailure(dialog: Dialog) {
                    Toast.makeText(this@MainActivity, AuthenticationHandler.text, Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(exception: AuthenticationException) {
                    Toast.makeText(this@MainActivity, "${AuthenticationHandler.text}: $exception", Toast.LENGTH_SHORT).show()
                }

                override fun onSuccess(credentials: Credentials) {
                    binding?.loggedIn = true
                    CredentialsManager.saveCredentials(credentials)
                    MainActivity()
                }
            })
    }

    // auth0 triggers intent on successful login
    override fun onNewIntent(intent: Intent?) {
        if (WebAuthProvider.resume(intent)) {
            binding?.loggedIn = true
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
