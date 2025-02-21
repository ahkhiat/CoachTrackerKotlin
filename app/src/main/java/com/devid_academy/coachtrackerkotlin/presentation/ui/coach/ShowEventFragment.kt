package com.devid_academy.coachtrackerkotlin.presentation.ui.coach

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.TextView
import com.devid_academy.coachtrackerkotlin.R
import com.devid_academy.coachtrackerkotlin.data.dto.EventDTO
import com.devid_academy.coachtrackerkotlin.data.dto.PlayerDTO
import com.devid_academy.coachtrackerkotlin.data.repository.EventRepository
import com.devid_academy.coachtrackerkotlin.util.EVENT_KEY
import com.devid_academy.coachtrackerkotlin.util.getStatus
import java.util.Locale



class ShowEventFragment : Fragment() {

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_show_event, container, false)
        val eventFromParent: EventDTO? = arguments?.getParcelable(EVENT_KEY)

        Log.i("EVENT ID", "EVENT ID : ${eventFromParent?.id}")

        val linearLayout = view.findViewById<LinearLayout>(R.id.linear_layout_player_list)

        EventRepository().getEvent(eventFromParent!!.id) {
            Log.i("EVENT DB", "EVENT DB : ${it.toString()}")
            if((it.presences)!!.isNotEmpty()) {
                Log.i("PRESENCES", "PRESENCES : ${it.presences}")
            } else {
                Log.i("PRESENCES", "Pas de présences")
            }
            if((it.convocations)!!.isNotEmpty()) {
                Log.i("CONVOCATIONS", "CONVOCATIONS : ${it.convocations}")

                for (convocation in it.convocations) {
                    val textView = TextView(context).apply {
                        text = "- " + convocation.player.user.firstname +
                                " (" + getString(getStatus(convocation.status)) + ")"
                        textSize = 16f
                        setPadding(16, 8, 16, 8)
                    }
                    linearLayout.addView(textView)
                }
            } else {
                Log.i("CONVOCATIONS", "Pas de convocations")
            }

            view.findViewById<TextView>(R.id.showevent_tv_location)
                .text = "${it?.stadium?.name}, ${it?.stadium?.adress} ${it?.stadium?.postalCode} ${it?.stadium?.town}"




        }



        val dateString = eventFromParent?.date
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
            .text = eventFromParent?.visitorTeam?.club?.name

        view.findViewById<TextView>(R.id.showevent_tv_team_name)
            .text = eventFromParent?.team?.name

        view.findViewById<TextView>(R.id.showevent_tv_time)
            .text = time



        return view
    }





}