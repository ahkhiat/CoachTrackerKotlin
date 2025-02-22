package com.devid_academy.coachtrackerkotlin.presentation.ui.coach

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devid_academy.coachtrackerkotlin.R
import com.devid_academy.coachtrackerkotlin.databinding.FragmentCallUpSummaryBinding
import com.devid_academy.coachtrackerkotlin.databinding.FragmentTeamBinding


class CallUpSummaryFragment : Fragment() {

    private var _binding: FragmentCallUpSummaryBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCallUpSummaryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }


}

// Recapitulatif de la creation du match
// Date, lieu, equipe adverse, liste des joueurs convoqués
// Bouton Valider.

// Requete INSERT envoyée en BDD