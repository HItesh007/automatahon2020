package com.hitesh.automatahon.challenges.challenge8;

import com.hitesh.automatahon.json.JSONObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.lang.WordUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.restassured.RestAssured.given;

public class CompanyAPIHelper {
    private static final Logger logger = LogManager.getLogger(CompanyAPIHelper.class.getName());
    private final String BASE_URI = "http://34.68.51.180:4000/api/v1";

    public CompanyAPIHelper() {
        // To Do
        RestAssured.baseURI = BASE_URI;
    }

    public Response getAllCompanies() {
        return given()
                .contentType(ContentType.JSON)
                .get("/companies")
                .then()
                .extract()
                .response();
    }

    public Response getCompanyByName(String companyName) {
        return given()
                .contentType(ContentType.JSON)
                .get("/companies/" + companyName)
                .then()
                .extract()
                .response();
    }

    public Response addCompany(String name, int telephone, String email) {
        return given()
                .contentType(ContentType.JSON)
                .body(prepareRequestObject(name, telephone, email))
                .post("/companies")
                .then()
                .extract()
                .response();
    }

    public Response addCompany(Object requestBody) {
        return given()
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .post("/companies")
                .then()
                .extract()
                .response();
    }

    public Response updateCompany(String name, int telephone, String email) {
        return given()
                .contentType(ContentType.JSON)
                .body(prepareRequestObject(name, telephone, email))
                .put("/companies/" + name)
                .then()
                .extract()
                .response();
    }

    public Response updateCompanyByRequestBody(Object requestBody) {
        // Get Name from Request Body
        String companyName = ((JSONObject) requestBody).getString("name");

        return given()
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .post("/companies/" + companyName)
                .then()
                .extract()
                .response();
    }

    public Response deleteCompany(String companyName) {
        return given()
                .contentType(ContentType.JSON)
                .delete("/companies/" + companyName)
                .then()
                .extract()
                .response();
    }

    private JSONObject prepareRequestObject(String name, int telephone, String email) {
        return new JSONObject()
                .put("name", WordUtils.capitalizeFully(name))
                .put("tel", String.valueOf(telephone))
                .put("email", email);
    }
}
