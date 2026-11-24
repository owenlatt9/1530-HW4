package edu.pitt.se;

public class PhysicianNotesStrategy implements DataProcessingStrategy{
    // TODO: implement.
    @Override 
    public String process(String input){
        String result = input;

        // Replace filler words
        result = result.replace(" uhm", "");
        result = result.replace(" basically", "");

        // Expand on abbreviations
        result = result.replace("pt", "patient");
        result = result.replace("bp", "blood pressure");

        return "[NOTES] " + result;
    }
}
