package com.example.nit3213final

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val apiService = mock(ApiService::class.java)
    private val viewModel = DashboardViewModel(apiService)

    @Test
    fun `fetchDashboardItems success updates items`() {
        val call = mock(Call::class.java) as Call<JsonObject>
        `when`(apiService.getDashboardItems(anyString(), anyString(), anyString())).thenReturn(call)

        val observerItems = mock(Observer::class.java) as Observer<List<DashboardItem>>
        val observerError = mock(Observer::class.java) as Observer<String?>

        viewModel.items.observeForever(observerItems)
        viewModel.errorMessage.observeForever(observerError)

        viewModel.fetchDashboardItems("key", "user", "pass")

        val callbackCaptor = ArgumentCaptor.forClass(Callback::class.java) as ArgumentCaptor<Callback<JsonObject>>
        verify(call).enqueue(callbackCaptor.capture())
        val callback = callbackCaptor.value

        val jsonObject = JsonObject()
        val entities = JsonArray()
        val entityObject = JsonObject()
        entityObject.addProperty("id", "123")
        entityObject.addProperty("name", "Test Item")
        entities.add(entityObject)
        jsonObject.add("entities", entities)

        callback.onResponse(call, Response.success(jsonObject))

        verify(observerItems).onChanged(anyList())
        verify(observerError).onChanged(null)
    }

    @Test
    fun `fetchDashboardItems failure updates errorMessage`() {
        val call = mock(Call::class.java) as Call<JsonObject>
        `when`(apiService.getDashboardItems(anyString(), anyString(), anyString())).thenReturn(call)

        val observerError = mock(Observer::class.java) as Observer<String?>

        viewModel.errorMessage.observeForever(observerError)

        viewModel.fetchDashboardItems("key", "user", "pass")

        val callbackCaptor = ArgumentCaptor.forClass(Callback::class.java) as ArgumentCaptor<Callback<JsonObject>>
        verify(call).enqueue(callbackCaptor.capture())
        val callback = callbackCaptor.value

        callback.onFailure(call, Throwable("Network error"))

        verify(observerError).onChanged("Error: Network error")
    }
}
