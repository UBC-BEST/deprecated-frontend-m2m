package com.example.tutorial.android.ui.auth.ui.login

import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.*
import com.example.tutorial.android.R
import com.example.tutorial.android.ui.main.HomeActivity
import com.example.tutorial.android.ui.onboarding.SelectTrainingActivity

class SignUpFragment : Fragment() {

    companion object {
        fun newInstance() = SignUpFragment()
    }

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel = ViewModelProvider(this, LoginViewModelFactory())
            .get(LoginViewModel::class.java)

        val emailEditText = view.findViewById<EditText>(R.id.email)
        val nameEditText = view.findViewById<EditText>(R.id.name)
        val passwordEditText = view.findViewById<EditText>(R.id.password)
        val signUpButton = view.findViewById<Button>(R.id.sign_up)
        val changeAuthentication = view.findViewById<TextView>(R.id.change_authentication)

        loginViewModel.loginFormState.observe(viewLifecycleOwner,
            Observer { loginFormState ->
                if (loginFormState == null) {
                    return@Observer
                }
                signUpButton.isEnabled = loginFormState.isDataValid
                loginFormState.usernameError?.let {
                    emailEditText.error = getString(it)
                }
                loginFormState.passwordError?.let {
                    passwordEditText.error = getString(it)
                }
            })

        loginViewModel.loginResult.observe(viewLifecycleOwner,
            Observer { loginResult ->
                loginResult ?: return@Observer
                loginResult.error?.let {
                    showLoginFailed(it)
                }
                loginResult.success?.let {
                    updateUiWithUser(it)
                }
            })

        val afterTextChangedListener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // ignore
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // ignore
            }

            override fun afterTextChanged(s: Editable) {
                loginViewModel.loginDataChanged(
                    emailEditText.text.toString(),
                    passwordEditText.text.toString()
                )
            }
        }
        emailEditText.addTextChangedListener(afterTextChangedListener)
        passwordEditText.addTextChangedListener(afterTextChangedListener)
        passwordEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                loginViewModel.tryAuthentication(
                    emailEditText.text.toString(),
                    passwordEditText.text.toString(),
                    true
                )
            }
            false
        }

        signUpButton.setOnClickListener {
            loginViewModel.tryAuthentication(
                emailEditText.text.toString(),
                passwordEditText.text.toString(),
                true
            )
        }

        changeAuthentication.setOnClickListener {
            Log.d("CHANGE AUTH", "Change auth has been pressed")
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(((view as ViewGroup).parent as View).id, LoginFragment.newInstance())
                ?.commitNow()
        }
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        startActivity(Intent(activity, SelectTrainingActivity::class.java))
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, errorString, Toast.LENGTH_LONG).show()
    }
}