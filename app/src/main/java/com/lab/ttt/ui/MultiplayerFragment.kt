package com.lab.ttt.ui
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import com.lab.ttt.R

class MultiplayerFragment : BaseFragment() {

    override val layoutRes: Int
        get() = R.layout.multiplayer_fragment


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.multiplayer_fragment, null)

    }


}
