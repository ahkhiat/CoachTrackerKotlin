package com.devid_academy.coachtrackerkotlin.data.repository

import android.util.Log
import com.devid_academy.coachtrackerkotlin.data.network.ApiService
import com.devid_academy.coachtrackerkotlin.data.dto.auth.RegisterDTO
import com.devid_academy.coachtrackerkotlin.data.dto.auth.StatusAuthDTO
import retrofit2.HttpException
import java.io.IOException

class RegisterRepository {

    private val api = ApiService.getApi()

    suspend fun register(user: RegisterDTO): Result<StatusAuthDTO>  {
        return try {
            val response = api.registerUser(user)
            Log.i("REPO AUTH", "Réponse du repo auth : $response")
            if (!response.token.isNullOrEmpty()) {
                Result.success(response)
            } else {
                Result.failure(Exception("Réponse invalide : Token vide"))
            }
        } catch (e: HttpException) {
            Log.e("REPO AUTH", "Erreur HTTP : ${e.code()} - ${e.message()}")
            Result.failure(Exception("Erreur HTTP ${e.code()}: ${e.message()}"))
        } catch (e: IOException) {
            Log.e("REPO AUTH", "Erreur réseau : ${e.message}")
            Result.failure(Exception("Erreur réseau, vérifiez votre connexion"))
        } catch (e: Exception) {
            Log.e("REPO AUTH", "Erreur inconnue : ${e.message}")
            Result.failure(Exception("Une erreur inconnue s'est produite"))
        }
    }
}