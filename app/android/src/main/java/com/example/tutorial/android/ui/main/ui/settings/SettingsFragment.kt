package com.example.tutorial.android.ui.main.ui.settings

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tutorial.android.R
import com.example.tutorial.android.ui.auth.AuthActivity
import com.google.firebase.auth.FirebaseAuth

class SettingsFragment : Fragment() {

    private lateinit var settingsViewModel: SettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        settingsViewModel =
            ViewModelProvider(this).get(SettingsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_settings, container, false)
        val textView: TextView = root.findViewById(R.id.text_settings)
        val logoutButton: Button = root.findViewById(R.id.logout)
        settingsViewModel.text.observe(viewLifecycleOwner, {
            textView.text = it
        })
        logoutButton.setOnClickListener {
            val firebaseAuth = FirebaseAuth.getInstance()
            firebaseAuth.signOut()
            val intent = Intent(activity, AuthActivity::class.java)
            startActivity(intent)
        }
        return root
    }
}