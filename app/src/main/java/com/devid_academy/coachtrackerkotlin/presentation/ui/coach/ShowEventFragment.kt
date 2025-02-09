package com.devid_academy.coachtrackerkotlin.presentation.ui.coach

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.devid_academy.coachtrackerkotlin.R
import com.devid_academy.coachtrackerkotlin.data.dto.EventDTO
import com.devid_academy.coachtrackerkotlin.util.EVENT_KEY
import java.util.Locale


class ShowEventFragment : Fragment() {

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_show_event, container, false)
        val event: EventDTO? = arguments?.getParcelable(EVENT_KEY)

        val dateString = event?.date
        val dateFormatted = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.getDefault())
            .parse(dateString)
//
        val day = SimpleDateFormat("EEEE", Locale.getDefault()).format(dateFormatted)
        val date = SimpleDateFormat("dd", Locale.getDefault()).format(dateFormatted)
        val month = SimpleDateFormat("MMMM", Locale.getDefault()).format(dateFormatted)
        val time = SimpleDateFormat("HH:mm", Locale.getDefault()).format(dateFormatted)

        view.findViewById<TextView>(R.id.toolbar_back_tv)
            .text = "$day $date $month"

        view.findViewById<TextView>(R.id.showevent_tv_visitor_team)
            .text = event?.visitorTeam?.club?.name

        view.findViewById<TextView>(R.id.showevent_tv_team_name)
            .text = event?.team?.name

        view.findViewById<TextView>(R.id.showevent_tv_time)
            .text = time

        view.findViewById<TextView>(R.id.showevent_tv_location)
            .text = "${event?.stadium?.name}, ${event?.stadium?.}"



        return view
    }


}