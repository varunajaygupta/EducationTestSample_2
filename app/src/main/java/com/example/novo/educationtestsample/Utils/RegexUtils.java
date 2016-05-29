package com.example.novo.educationtestsample.Utils;

/**
 * Created by Hisham on 1/18/2016.
 */
public final class RegexUtils {
    // private constructor
    private RegexUtils() {
    }

    /**
     * Format to match phone numbers: xxx-xxx-xxxx <br>
     *
     * @param phoneNumber
     * @return true if a match is found
     */
    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^\\d{3}\\-\\d{3}\\-\\d{4}$");
    }

    /**
     * Format to match ssn number: xxx-xx-xxxx
     *
     * @param ssn
     * @return true if a match is found
     */
    public static boolean isValidSSNNumber(String ssn) {
        return ssn.matches("^(?!666|000|9\\d{2})\\d{3}-(?!00)\\d{2}-(?!0{4})\\d{4}$");
    }

    /**
     * Format to match ein number: xx-xxxxxxx
     *
     * @param ein
     * @return true if a match is found
     */
    public static boolean isValidEINNumber(String ein) {
        return ein.matches("^\\d{2}\\-\\d{7}$");
    }
}
