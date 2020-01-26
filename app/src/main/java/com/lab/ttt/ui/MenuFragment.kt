package com.lab.ttt.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.lab.ttt.R
import com.lab.ttt.game.MainActivity

class MenuFragment: BaseFragment() {
    override val layoutRes: Int
        get() = R.layout.menu2

    private lateinit var scoreButton:Button
    private lateinit var multiplayerButton:Button
    private lateinit var gameButton:Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.menu2, null)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)


        gameButton =  this.view?.findViewById<View>(R.id.button2) as Button
        gameButton.setOnClickListener{
            (this.activity as TicTacToeActivity).onGameClick()
        }
        multiplayerButton =  this.view?.findViewById<View>(R.id.button3) as Button
        multiplayerButton.setOnClickListener{
            findNavController().navigate(R.id.multiplayerFragment)
        }

        scoreButton = this.view?.findViewById<View>(R.id.button4) as Button
        scoreButton.setOnClickListener {
            findNavController().navigate(R.id.scbFragment)
        }
    }


}