package com.example.test.features.main.data.response

import junit.framework.Assert.assertEquals
import org.junit.Test

class ApiResponseTest {

    private val owner = ApiOwner(
        login = "Fulano Silva",
        avatar = "https://github.com.br/img"
    )

    private val model = ApiResponse(
        name = "Fulano",
        forksCount = 1200,
        starsCount = 1500,
        apiOwner = owner
    )

    @Test
    fun `validation model ApiResponse`() {
        assertEquals("Fulano", model.name)
        assertEquals(1200, model.forksCount)
        assertEquals(1500, model.starsCount)
        assertEquals(1500, model.starsCount)
        assertEquals("Fulano Silva", model.apiOwner?.login)
        assertEquals("https://github.com.br/img", model.apiOwner?.avatar)
    }
}