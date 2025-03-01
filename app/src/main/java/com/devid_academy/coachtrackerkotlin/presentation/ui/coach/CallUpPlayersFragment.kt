package com.devid_academy.coachtrackerkotlin.presentation.ui.coach

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devid_academy.coachtrackerkotlin.R
import com.devid_academy.coachtrackerkotlin.databinding.FragmentCallUpPlayersBinding
import com.devid_academy.coachtrackerkotlin.databinding.FragmentTeamBinding


class CallUpPlayersFragment : Fragment() {

    private var _binding: FragmentCallUpPlayersBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCallUpPlayersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

// Liste de tous les joueurs de l'equipe
// Chaque item est un bouton qui reste cliqué vert quand on appuie dessus

// Bouton valider

// C'est un formulaire qui enregistre tous les boutons qui ont été cliqués
// Voir si je peux faire un querySelector de tous les boutons qui ont une proprieté verte ?