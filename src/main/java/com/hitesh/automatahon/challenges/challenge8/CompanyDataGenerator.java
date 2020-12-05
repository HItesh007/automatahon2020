package com.hitesh.automatahon.challenges.challenge8;

import com.hitesh.automatahon.challenges.constants.FileConstants;
import com.hitesh.automatahon.json.*;
import com.hitesh.automatahon.utils.FileUtility;
import com.hitesh.automatahon.utils.SystemUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class CompanyDataGenerator {
    private static final Logger logger = LogManager.getLogger(CompanyDataGenerator.class.getName());
    private final String USER_DIR = SystemUtility.getUserDirectory();

    public CompanyDataGenerator() {
        // To Do
    }

    public static void generateData(int maxDataSize) throws IOException {
        JSONObject dataJsonObject = new JSONObject();
        JSONArray dataArray = new JSONArray();
        for(int i=0; i < maxDataSize; i++) {
            JSONObject eachRowObject = new JSONObject();
            eachRowObject.put("name", CompanyFaker.getCompanyName());
            eachRowObject.put("tel", CompanyFaker.getCompanyPhone());
            eachRowObject.put("email", CompanyFaker.getCompanyEmail());
            dataArray.put(eachRowObject);
        }

        dataJsonObject.put("CompanyData", dataArray);


        // Store this in a file
        new JSONFileWriter()
                .saveJSONStringToFile(
                        FileConstants.COMPANY_DATA_FILE,
                        dataJsonObject.toString()
                );
    }

    public static JSONArray getDataAsJson() {
        return new JSONParser()
                .parse(FileConstants.COMPANY_DATA_FILE)
                .getJSONArray("CompanyData");
    }
}
