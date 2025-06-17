package com.example.nit3213final

import com.google.common.truth.Truth.assertThat
import com.google.gson.JsonParser
import org.junit.Test

class DashboardParsingTest {

    @Test
    fun parseJsonObject_toFieldsMap_containsAllBookEntries() {
        val json = """
        {
          "id": 1,
          "title": "Android Development",
          "author": "John Doe",
          "year": 2022,
          "description": "Comprehensive guide to Android development"
        }
    """.trimIndent()
        val jsonObj = JsonParser.parseString(json).asJsonObject
        val map = jsonObj.entrySet().associate { (key, value) ->
            key to if (value.isJsonPrimitive) value.asString else value.toString()
        }

        assertThat(map["id"]).isEqualTo("1")
        assertThat(map["title"]).isEqualTo("Android Development")
        assertThat(map["author"]).isEqualTo("John Doe")
        assertThat(map["year"]).isEqualTo("2022")
        assertThat(map["description"]).isEqualTo("Comprehensive guide to Android development")
    }
}
