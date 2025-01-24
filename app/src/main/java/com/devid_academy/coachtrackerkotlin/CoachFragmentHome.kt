package com.devid_academy.coachtrackerkotlin

import android.os.Bundle
import android.text.Layout
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast

class CoachFragmentHome : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_coach_home, container, false)

        val btnCreate = view.findViewById<Button>(R.id.fg_coach_home_btn_create_event)
        btnCreate.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fg_container, CreateEventFragment())
                .addToBackStack(null)
                .commit()
        }


        return view
    }


}


// Sur cette page, on va afficher le calendrier de tous les événements à venir,
// Entrainements et matchs, sous forme d'un RecyclerView

// Données attendues :
// Tous les Events : - Date
// - Visitor team
// - Stadium