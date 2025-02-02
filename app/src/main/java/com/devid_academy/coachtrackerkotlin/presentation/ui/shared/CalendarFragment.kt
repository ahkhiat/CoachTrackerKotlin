package com.devid_academy.coachtrackerkotlin.presentation.ui.shared

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devid_academy.coachtrackerkotlin.R
import com.devid_academy.coachtrackerkotlin.data.api.getEvents
import com.devid_academy.coachtrackerkotlin.presentation.adpater.EventAdapter
import com.devid_academy.coachtrackerkotlin.presentation.ui.coach.CreateEventFragment

class CalendarFragment : Fragment() {

    private lateinit var eventAdapter: EventAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calendar, container, false)

        progressBar = view.findViewById(R.id.progressBar)


        val btnCreate = view.findViewById<Button>(R.id.fg_coach_home_btn_create_event)
        btnCreate.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fg_container, CreateEventFragment())
                .addToBackStack(null)
                .commit()
        }
        eventAdapter = EventAdapter()

        recyclerView = view.findViewById<RecyclerView>(R.id.rv_calendar).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = eventAdapter
        }

        refresh()
        return view
    }
    private fun refresh() {
        progressBar.visibility = View.VISIBLE
        getEvents {
            eventAdapter.setEvents(it)
            recyclerView.scrollToPosition(eventAdapter.itemCount - 1)
            progressBar.visibility = View.GONE
            Log.d("DEBUG", "Nombre d'événements récupérés : ${eventAdapter.itemCount}")
        }
    }


}


// Sur cette page, on va afficher le calendrier de tous les événements à venir,
// Entrainements et matchs, sous forme d'un RecyclerView

// Données attendues :
// Tous les Events : - Date
// - Visitor team
// - Stadium