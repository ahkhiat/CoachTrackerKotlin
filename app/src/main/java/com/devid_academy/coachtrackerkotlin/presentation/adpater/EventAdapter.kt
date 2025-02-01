package com.devid_academy.coachtrackerkotlin.presentation.adpater

import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devid_academy.coachtrackerkotlin.R
import com.devid_academy.coachtrackerkotlin.data.dto.EventDTO
import com.devid_academy.coachtrackerkotlin.presentation.ui.shared.CalendarFragment
import java.util.Locale


class EventAdapter(): RecyclerView.Adapter<EventAdapter.EventHolder>() {

    private val eventsList : MutableList<EventDTO> = mutableListOf()

    private var rva : CalendarFragment? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventAdapter.EventHolder {
        return LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rv_event, parent, false)
            .let {
                EventHolder(it)
            }
    }



    override fun onBindViewHolder(holder: EventAdapter.EventHolder, position: Int) {
        val event = eventsList.get(position)

        val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()) // Format reçu
        val dayFormat = SimpleDateFormat("EEEE", Locale.getDefault()) // Jour ex: Monday
        val dateFormat = SimpleDateFormat("dd", Locale.getDefault()) // Jour numérique
        val monthFormat = SimpleDateFormat("MMM", Locale.getDefault()) // Mois court ex: Jan
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault()) // Heure 24h

        val eventDate = inputFormat.parse(event.date) // Conversion de la chaîne

        with(holder) {
            tvTitle.text = event.eventType.name
            tvLocation.text = event.stadium.name
            eventDate?.let {
                tvDay.text = dayFormat.format(it)
                tvDate.text = dateFormat.format(it)
                tvMonth.text = monthFormat.format(it)
                tvTime.text = timeFormat.format(it)
            }
        }
    }

    override fun getItemCount(): Int {
        return eventsList.size
    }

    fun setEvents(events: List<EventDTO>) {
        with(eventsList) {
            clear()
            addAll(events)
        }
        notifyDataSetChanged() // force l'adapter à recharger
    }

    fun setRva(rva: CalendarFragment) {
        this.rva = rva
    }

    class EventHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvDay = itemView.findViewById<TextView>(R.id.item_tv_day)
        val tvDate = itemView.findViewById<TextView>(R.id.item_tv_date)
        val tvMonth = itemView.findViewById<TextView>(R.id.item_tv_month)
        val tvTime = itemView.findViewById<TextView>(R.id.item_tv_time)

        val tvTitle = itemView.findViewById<TextView>(R.id.item_tv_title)
        val tvLocation = itemView.findViewById<TextView>(R.id.item_tv_location)
    }

}

