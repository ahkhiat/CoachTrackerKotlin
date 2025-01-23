package com.devid_academy.coachtrackerkotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class CallUpPlayersFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_call_up_players, container, false)
    }


}

// Liste de tous les joueurs de l'equipe
// Chaque item est un bouton qui reste cliqué vert quand on appuie dessus

// Bouton valider

// C'est un formulaire qui enregistre tous les boutons qui ont été cliqués
// Voir si je peux faire un querySelector de tous les boutons qui ont une proprieté verte ?