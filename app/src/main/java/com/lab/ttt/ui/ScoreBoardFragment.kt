package com.lab.ttt.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lab.ttt.R
import com.lab.ttt.database.ScoreDao
import com.lab.ttt.App

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class ScoreBoardFragment : BaseFragment() {

    private var database: ScoreDao? = null


    private val scoreAdapter = ScoreAdapter()



    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override val layoutRes: Int
        get() = R.layout.scoreboard_fragment


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        return inflater.inflate(R.layout.scoreboard_fragment, null)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        database = (activity?.application as App).getDatabase()?.scoreDao()
        setupRecycler()
    }


    private fun setupRecycler() {
        val recyclerView = view!!.findViewById<RecyclerView>(R.id.score_recycler)
        recyclerView.adapter = scoreAdapter
        val recyclerLayoutManager = LinearLayoutManager(requireContext())
        val dividerItemDecoration = DividerItemDecoration(
                recyclerView.context,
                recyclerLayoutManager.orientation
        )
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = recyclerLayoutManager
        recyclerView.addItemDecoration(dividerItemDecoration)

        GlobalScope.launch {
            val scoreList = async { database!!.getAll() }
            scoreAdapter.updateScores(*scoreList.await().toTypedArray())
        }

    }

    companion object {
        val instance: ScoreBoardFragment
            get() {
                val args = Bundle()
                val fragment = ScoreBoardFragment()
                fragment.arguments = args
                return fragment
            }
    }
}
