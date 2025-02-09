package com.devid_academy.coachtrackerkotlin.presentation.adpater

import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.devid_academy.coachtrackerkotlin.R
import com.devid_academy.coachtrackerkotlin.data.dto.EventDTO
import com.devid_academy.coachtrackerkotlin.presentation.ui.shared.CalendarFragment
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale


class EventAdapter(private val onItemClick: (EventDTO) -> Unit)  : RecyclerView.Adapter<EventAdapter.EventHolder>() {

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
        with(holder) {
            if (event.eventType.name == "Match") {
                tvTitle.text = event.visitorTeam!!.club.name
            } else {
                tvTitle.text = event.eventType.name
                tvTitle.textSize = 14F

            }
            tvLocation.text = event.stadium.name

            val dateString = event.date

            val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.getDefault())
                        .parse(dateString)

            tvDay.text = SimpleDateFormat("EEEE", Locale.getDefault()).format(date)
            tvDate.text = SimpleDateFormat("dd", Locale.getDefault()).format(date)
            tvMonth.text = SimpleDateFormat("MMM", Locale.getDefault()).format(date)
            tvTime.text = SimpleDateFormat("HH:mm", Locale.getDefault()).format(date)

            itemLayout.setOnClickListener {
                onItemClick(event)
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

        val itemLayout = itemView.findViewById<CardView>(R.id.item_rv_event)
    }

}

