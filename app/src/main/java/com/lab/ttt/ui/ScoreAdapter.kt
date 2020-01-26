package com.lab.ttt.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lab.ttt.R
import com.lab.ttt.database.ScoreEntry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

//Адаптер для отображения счета игроков
class ScoreAdapter :
        RecyclerView.Adapter<ScoreAdapter.ScoreViewHolder>() {

    private val scoreList = mutableListOf<ScoreEntry>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ScoreViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.score_item,
                            parent,
                            false
                    )
            )

    override fun getItemCount(): Int =
            scoreList.size

    override fun onBindViewHolder(holder: ScoreViewHolder, position: Int) =
            holder.bind(scoreList[position])

    /*Обновление счета, идет обращение к базе данных в
    фоновом потоке, а затем значения устанавливаются в основном.
     */
    suspend fun updateScores(vararg scoreEntry: ScoreEntry) {
        scoreEntry.forEach {
            if(!scoreList.contains(it)) {
            scoreList.add(it)
        }
        }
        scoreList.sortByDescending { it.score }
        withContext(Dispatchers.Main) {
            notifyDataSetChanged()
        }
    }

    inner class ScoreViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(scoreEntry: ScoreEntry) {
            scoreEntry.apply {
                view.findViewById<TextView>(R.id.place_text).text = (this@ScoreViewHolder.layoutPosition + 1).toString()
                view.findViewById<TextView>(R.id.player_name_text).text = scoreEntry.playerName
                view.findViewById<TextView>(R.id.score_text).text = scoreEntry.score.toString()
            }
        }
    }

}