package com.hitesh.automatahon.challenges.constants;

import com.hitesh.automatahon.utils.SystemUtility;

public class FileConstants {
    private static final String USER_DIR = SystemUtility.getUserDirectory();
    private static final String FILE_SEPARATOR = SystemUtility.getFileSeparator();
    public static final String COMPANY_DATA_FILE = USER_DIR + FILE_SEPARATOR
            + "test-data"
            + FILE_SEPARATOR
            + "Company-Data.json";
}
