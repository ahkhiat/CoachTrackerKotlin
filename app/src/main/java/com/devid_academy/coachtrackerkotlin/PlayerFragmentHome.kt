package com.devid_academy.coachtrackerkotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.findViewTreeViewModelStoreOwner


class PlayerFragmentHome : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_player_home, container, false)

        val btnProfil: Button = view.findViewById(R.id.btn_profile_fg_player)
        btnProfil.setOnClickListener {

            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fg_container, PlayerProfileFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }
        return view
    }
}
