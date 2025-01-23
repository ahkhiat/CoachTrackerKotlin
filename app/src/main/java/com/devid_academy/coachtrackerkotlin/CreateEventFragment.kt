package com.devid_academy.coachtrackerkotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class CreateEventFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_create, container, false)
    }


}

//Données attendues :

//        fetch de la table event type pour afficher autant de boutons que de catégories
// reflechir pour ajouter un champ "isVisible" pour que seuls les catés choisies comme
// visibles s'affichent

// Les boutons dirigent vers les frag AddMatch, AddTraining, AddCamp