package com.example.nit3213final

import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import org.junit.Test

class LoginResponseParsingTest {

    @Test
    fun loginResponse_parsedCorrectly() {
        val json = """{ "keypass": "abc123xyz" }"""
        val response = Gson().fromJson(json, LoginResponse::class.java)

        assertThat(response.keypass).isEqualTo("abc123xyz")
    }
}
