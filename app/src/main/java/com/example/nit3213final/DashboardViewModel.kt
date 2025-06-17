package com.example.nit3213final

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardViewModel(
    private val apiService: ApiService = ApiClient.apiService
) : ViewModel() {

    private val _items = MutableLiveData<List<DashboardItem>>()
    val items: LiveData<List<DashboardItem>> = _items

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    fun fetchDashboardItems(keypass: String, username: String, password: String) {
        apiService.getDashboardItems(keypass.lowercase(), username, password)
            .enqueue(object : Callback<JsonObject> {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    if (response.isSuccessful && response.body() != null) {
                        val entities: JsonArray = response.body()!!.getAsJsonArray("entities") ?: JsonArray()
                        val itemList = mutableListOf<DashboardItem>()
                        entities.forEach { elem ->
                            val obj = elem.asJsonObject
                            val map = obj.entrySet().associate { (k, v) ->
                                k to if (v.isJsonPrimitive) v.asString else v.toString()
                            }
                            itemList.add(DashboardItem(map))
                        }
                        _items.postValue(itemList)
                        _errorMessage.postValue(null)
                    } else {
                        _errorMessage.postValue("Failed to load data")
                    }
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    _errorMessage.postValue("Error: ${t.message}")
                }
            })
    }
}
