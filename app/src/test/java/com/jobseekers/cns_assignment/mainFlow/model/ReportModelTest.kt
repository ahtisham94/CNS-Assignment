package com.jobseekers.cns_assignment.mainFlow.model

import com.jobseekers.cns_assignment.mainFlow.models.ReportResponse
import junit.framework.TestCase
import org.junit.Test


/**
 * This TestClass is for Test Response Model
 */
class ReportModelTest {

    @Test
    fun reportFormIdTest() {
        val item = ReportResponse(formid = 11233)
        TestCase.assertEquals(item.formid, 11233)
    }

    @Test
    fun reportDateTest() {
        val item = ReportResponse(date = "TestingUri")
        TestCase.assertEquals(item.date, "TestingUri")
    }

    @Test
    fun reportCreatedByTest() {
        val item = ReportResponse(createdBy = "TestingUrl")
        TestCase.assertEquals(item.createdBy, "TestingUrl")
    }

    @Test
    fun reportStatusTest() {
        val item = ReportResponse(reportStatus = "12,Mar 2022")
        TestCase.assertEquals(item.reportStatus, "12,Mar 2022")
    }

    @Test
    fun reportCreatedByImageTest() {
        val item = ReportResponse(createdByImage = "TestingUpdated")
        TestCase.assertEquals(item.createdByImage, "TestingUpdated")
    }

    @Test
    fun reportLocationTest() {
        val item = ReportResponse(location = "TestingSection")
        TestCase.assertEquals(item.location, "TestingSection")
    }

    @Test
    fun reportDetailsTest() {
        val item = ReportResponse(details = "TestingType")
        TestCase.assertEquals(item.details, "TestingType")
    }

    @Test
    fun reportSiteNameTest() {
        val item = ReportResponse(siteName = "Olivia")
        TestCase.assertEquals(item.siteName, "Olivia")
    }

    @Test
    fun reportCheckListTest() {
        val item = ReportResponse(checklists = arrayListOf("1", "2"))
        TestCase.assertEquals(item.checklists[0], "1")
    }

    @Test
    fun reportAttachmentsTest() {
        val item = ReportResponse(attachments = arrayListOf("1", "2"))
        TestCase.assertEquals(item.attachments[0], "1")
    }
}