package pl.paullettuce.daznrecruitmenttask.data.remote.model.mapper

import org.junit.Assert.assertEquals
import org.junit.Test

class ApiDateParserTest {

    private val apiDateParser = ApiDateParser()

    @Test
    fun `test valid date string`() {
        val dateString = "2024-04-02T12:00:00.000Z"
        val expectedTime = 1709440000000
        assertEquals(expectedTime, apiDateParser.map(dateString))
    }

    @Test
    fun `test invalid date string`() {
        val invalidDateString = "2024-04-02"
        assertEquals(-1L, apiDateParser.map(invalidDateString))
    }
}
