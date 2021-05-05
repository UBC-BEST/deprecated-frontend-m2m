package com.example.tutorial.android.ui.main.ui.games

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.tutorial.android.R

class GamesFragment : Fragment() {

    private lateinit var gamesViewModel: GamesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity?)?.supportActionBar?.hide()
        gamesViewModel =
            ViewModelProvider(this).get(GamesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_games, container, false)
        val textView: TextView = root.findViewById(R.id.text_games)
        val startGameButton: Button = root.findViewById(R.id.start_game)
        val flappyBirdButton: Button = root.findViewById(R.id.start_flappy_birb)

        gamesViewModel.text.observe(viewLifecycleOwner, {
        })
        startGameButton.setOnClickListener {
            val intent = Intent(activity, TappingGameActivity::class.java)
            startActivity(intent)
        }
        flappyBirdButton.setOnClickListener {
            val intent = Intent(activity, FlappyGameActivity::class.java)
            startActivity(intent)
        }
        return root
    }
}