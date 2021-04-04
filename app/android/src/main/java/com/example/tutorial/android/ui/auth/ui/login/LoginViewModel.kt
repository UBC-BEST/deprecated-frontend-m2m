package com.example.tutorial.android.ui.auth.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import com.example.tutorial.android.ui.auth.data.LoginRepository

import com.example.tutorial.android.R
import com.example.tutorial.android.ui.auth.data.model.LoggedInUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {
    private lateinit var firebaseAuth: FirebaseAuth

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun tryAuthentication(username: String, password: String, newUser: Boolean) {
        val firebaseAuth = FirebaseAuth.getInstance()
        if (newUser) {
            signUp(username, password, firebaseAuth)
        } else {
            login(username, password, firebaseAuth)
        }
    }


    private fun signUp(username: String, password: String, firebaseAuth: FirebaseAuth) {
        try {
            firebaseAuth.createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Log.d("AUTH", "createUserWithEmailAndPassword: Successful")
                        val firebaseUser = firebaseAuth.currentUser
                        val user = LoggedInUser(firebaseUser!!.uid, username, true)
                        Log.d("USER INFO", user.newUser.toString())
                        Log.d("USER INFO", user.displayName)
                        _loginResult.value =
                            LoginResult(
                                success = LoggedInUserView(
                                    displayName = username,
                                    newUser = true
                                )
                            )
                        Log.d("THIS IS FROM THE LOGIN VIEW MODEL", "SignUp was successful.")
                    } else {
                        _loginResult.value = LoginResult(error = R.string.login_failed)
                    }
                }
        } catch (e: FirebaseAuthUserCollisionException) {
            Log.w("AUTH", e)
        }
    }

    private fun login(username: String, password: String, firebaseAuth: FirebaseAuth) {
        try {
            firebaseAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Log.d("AUTH", "createUserWithEmailAndPassword: Successful")
                        val firebaseUser = firebaseAuth.currentUser
                        val user = LoggedInUser(firebaseUser!!.uid, username, false)
                        Log.d("USER INFO", user.newUser.toString())
                        Log.d("USER INFO", user.displayName)
                        _loginResult.value =
                            LoginResult(
                                success = LoggedInUserView(
                                    displayName = username,
                                    newUser = false
                                )
                            )
                        Log.d("THIS IS FROM THE LOGIN VIEW MODEL", "Login was successful.")
                    } else {
                        _loginResult.value = LoginResult(error = R.string.login_failed)
                    }
                }
        } catch (e: FirebaseAuthUserCollisionException) {
            Log.w("AUTH", e)
        }
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains("@")) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}