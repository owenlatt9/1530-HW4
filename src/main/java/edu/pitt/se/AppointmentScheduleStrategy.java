package edu.pitt.se;

public class AppointmentScheduleStrategy implements DataProcessingStrategy{
    // TODO: implement.
    @Override 
    public String process(String text){
        String result = text;

        // Normalize time (simulated, naive way)
        result = result.replace("9am", "09:00");
        result = result.replace("10am", "10:00");
        result = result.replace("2pm", "14:00");
        result = result.replace("3pm", "15:00");

        return "[SCHEDULE] " + result;
    }
}