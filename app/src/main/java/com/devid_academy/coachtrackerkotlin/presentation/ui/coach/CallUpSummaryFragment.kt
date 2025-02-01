package com.devid_academy.coachtrackerkotlin.presentation.ui.coach

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devid_academy.coachtrackerkotlin.R


class CallUpSummaryFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_call_up_summary, container, false)
    }


}

// Recapitulatif de la creation du match
// Date, lieu, equipe adverse, liste des joueurs convoqués
// Bouton Valider.

// Requete INSERT envoyée en BDD