package com.devid_academy.coachtrackerkotlin.data.repository

import android.util.Log
import com.devid_academy.coachtrackerkotlin.data.network.ApiService
import com.devid_academy.coachtrackerkotlin.data.dto.SeasonDTO
import com.devid_academy.coachtrackerkotlin.data.dto.StadiumDTO
import com.devid_academy.coachtrackerkotlin.data.dto.VisitorTeamDTO

class SpinnerRepository() {

    private val api = ApiService.getApi()

    suspend fun getVisitorTeamList(): List<VisitorTeamDTO> {
        return try {
            val response = api.getVisitorTeamList() ?: emptyList()
            Log.i("API CALL", "REPO VISITOR TEAM LIST : $response")
            response
        } catch (e: Exception) {
            Log.e("API CALL", "Erreur : ${e.message}", e)
            emptyList()
        }
    }
    suspend fun getStadiumList(): List<StadiumDTO> {
        return try {
            val response = api.getStadiumList() ?: emptyList()
            Log.i("API CALL", "REPO STADIUM LIST : $response")
            response
        } catch (e: Exception) {
            Log.e("API CALL", "Erreur : ${e.message}", e)
            emptyList()
        }
    }
    suspend fun getSeasonList(): List<SeasonDTO> {
        return try {
            val response = api.getSeasonList() ?: emptyList()
            Log.i("API CALL", "REPO SEASON LIST : $response")
            response
        } catch (e: Exception) {
            Log.e("API CALL", "Erreur : ${e.message}", e)
            emptyList()
        }
    }




}