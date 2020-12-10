package com.hitesh.automatahon.tests.challenges.eight;

import com.hitesh.automatahon.challenges.challenge8.CompanyAPIHelper;
import com.hitesh.automatahon.challenges.challenge8.CompanyDataGenerator;
import com.hitesh.automatahon.json.JSONArray;
import com.hitesh.automatahon.json.JSONObject;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class CompanyAPITests {
    private static final Logger logger = LogManager.getLogger(CompanyAPITests.class.getName());

    @Test(priority = 0)
    public void createCompanies() throws IOException {
        // Prepare Company Data
        CompanyDataGenerator.generateData(10);

        // Get Data
        JSONArray companyDataArray = CompanyDataGenerator.getDataAsJson();

        CompanyAPIHelper companyAPIHelper = new CompanyAPIHelper();
        // Add Companies
        companyDataArray.forEach(companyObject -> {
            JSONObject companyJsonObject = (JSONObject) companyObject;

            // Add Details
            Response companyAddResponse = companyAPIHelper
                    .addCompany(companyJsonObject);

            Assert.assertEquals(companyAddResponse.getStatusCode(), 201);
            logger.info(companyJsonObject + " Added Successfully.");

            // Get Created Company
            Response companyResponse = companyAPIHelper.getCompanyByName(companyJsonObject.getString("name"));

            JSONObject companyResponseAsJSON = new JSONObject(companyResponse.asString());

            boolean isNameString = companyResponseAsJSON.get("name") instanceof String;
            boolean isTelephoneString = companyResponseAsJSON.get("tel") instanceof String;
            boolean isEmailString = companyResponseAsJSON.get("email") instanceof String;

            Assert.assertTrue(isNameString, "Name Datatype mismatch");
            Assert.assertTrue(isTelephoneString, "Telephone Datatype mismatch");
            Assert.assertTrue(isEmailString, "Email Datatype Mistmatch");
        });
    }

    @Test(priority = 1)
    public void deleteAllCreatedCompanies() {
        // Get Data
        JSONArray companyDataArray = CompanyDataGenerator.getDataAsJson();

        companyDataArray.forEach(companyObject -> {
            JSONObject companyJsonObject = (JSONObject) companyObject;
            Object companyToDelete = companyJsonObject.get("name");

            Response deleteResponse = new CompanyAPIHelper()
                    .deleteCompany(String.valueOf(companyToDelete));

            // Assert Deletion Success
            Assert.assertEquals(deleteResponse.getStatusCode(), 204, "Error Deleting Company having Name : " + companyToDelete);
            logger.info("Company [" + companyToDelete + "] deleted successfully.");

            // Assert Deletion - Negative
            Response getDeletedCompany = new CompanyAPIHelper()
                    .getCompanyByName(String.valueOf(companyToDelete));

            // Assert 404
            Assert.assertEquals(getDeletedCompany.getStatusCode(), 404, "Company Was Not Deleted Previously.");
            logger.info("Negative Scenario For Company [" + companyToDelete + "] Deletion Successes.");
        });
    }
}
