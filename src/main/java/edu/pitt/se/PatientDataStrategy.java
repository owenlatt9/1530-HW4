package edu.pitt.se;

public class PatientDataStrategy implements DataProcessingStrategy{
    // TODO: implement.
    @Override
    public String process(String input){
        String result = input;

        // Check record structure
        if (!result.contains("Name") || !result.contains("Condition")) {
            return "Invalid patient record.";
        }

        // Anonymize name (naive)
        result = result.replaceAll("Name: [A-Za-z ]+", "Name: *****");

        return "[PATIENT] " + result;

    }
}
