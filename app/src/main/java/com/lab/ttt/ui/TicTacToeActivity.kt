package com.lab.ttt.ui

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

import androidx.appcompat.app.AppCompatActivity

import com.lab.ttt.App
import com.lab.ttt.game.MainActivity
import com.lab.ttt.game.Player
import com.lab.ttt.R
import com.lab.ttt.database.ScoreDao


class TicTacToeActivity : AppCompatActivity() {

     var database: ScoreDao? = null


    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.base)

        database = (this.application as App).getDatabase()?.scoreDao()

    }

   @SuppressLint("InflateParams")
    fun onGameClick() {

       val li: LayoutInflater = LayoutInflater.from(this)
       val namesView: View = li.inflate(R.layout.names, null)


       val mDialogBuilder: AlertDialog.Builder = AlertDialog.Builder(this)
       mDialogBuilder.setView(namesView)


       val player1: EditText = namesView.findViewById(R.id.player_name1) as EditText
       val player2: EditText = namesView.findViewById(R.id.player_name2) as EditText


       mDialogBuilder
           .setCancelable(false)
           .setPositiveButton(
               "Apply", DialogInterface.OnClickListener(
                   fun(dialog: DialogInterface, id: Int) {

                       //not here

                       (this.application as App).player1 =
                           Player(player1.text.toString())
                       (this.application as App).player2 =
                           Player(player2.text.toString())

                       startActivity(Intent(this, MainActivity::class.java))

                       dialog.cancel()
                   })
           )
           .setNegativeButton("Cancel", DialogInterface.OnClickListener(
               fun(dialog: DialogInterface, id: Int) {

                   dialog.cancel()

               }))
       val alertDialog: AlertDialog = mDialogBuilder.create()
       alertDialog.show()
   }

}