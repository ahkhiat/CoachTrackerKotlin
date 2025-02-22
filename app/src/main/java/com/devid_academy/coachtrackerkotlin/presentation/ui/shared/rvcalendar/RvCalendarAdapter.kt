package com.devid_academy.coachtrackerkotlin.presentation.ui.shared.rvcalendar

import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.devid_academy.coachtrackerkotlin.data.dto.EventDTO
import com.devid_academy.coachtrackerkotlin.databinding.ItemRvEventBinding
import java.util.Locale


class RvCalendarAdapter(
    private val onItemClick: (EventDTO) -> Unit
) : ListAdapter<EventDTO, RvCalendarAdapter.EventHolder>(ContactDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventHolder {
        val binding = ItemRvEventBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return EventHolder(binding)
    }


    override fun onBindViewHolder(holder: EventHolder, position: Int) {
        val event = getItem(position)

         with(holder.binding) {
            if (event.eventType.name == "Match") {
                itemTvTitle.text = event.visitorTeam!!.club.name
            } else {
                itemTvTitle.text = event.eventType.name
                itemTvTitle.textSize = 14F
            }

            itemTvLocation.text = event.stadium.name

            // Conversion de la date
            val dateString = event.date
            val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.getDefault()).parse(dateString)

            itemTvDay.text = SimpleDateFormat("EEEE", Locale.getDefault()).format(date)
            itemTvDate.text = SimpleDateFormat("dd", Locale.getDefault()).format(date)
            itemTvMonth.text = SimpleDateFormat("MMM", Locale.getDefault()).format(date)
            itemTvTime.text = SimpleDateFormat("HH:mm", Locale.getDefault()).format(date)

            root.setOnClickListener { onItemClick(event) }
        }
    }

    class EventHolder(val binding: ItemRvEventBinding) : RecyclerView.ViewHolder(binding.root)

}
object ContactDiffCallback : DiffUtil.ItemCallback<EventDTO>() {
    override fun areItemsTheSame(oldItem: EventDTO, newItem: EventDTO): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: EventDTO, newItem: EventDTO): Boolean {
        return oldItem == newItem
    }

}
