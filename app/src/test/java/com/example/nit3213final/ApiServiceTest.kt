package com.example.nit3213final



import com.google.common.truth.Truth.assertThat
import com.google.gson.GsonBuilder
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: ApiService

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/")) // use the mock server URL
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(ApiService::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun login_returnsKeypassSuccessfully() {
        // Arrange - fake response
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody("""{"keypass":"testkey123"}""")
        mockWebServer.enqueue(mockResponse)

        // Act - perform the call synchronously
        val request = LoginRequest(username = "Nishan", password = "s12345678")
        val response = apiService.login(request).execute()

        // Assert
        assertThat(response.isSuccessful).isTrue()
        assertThat(response.body()?.keypass).isEqualTo("testkey123")
    }
}
