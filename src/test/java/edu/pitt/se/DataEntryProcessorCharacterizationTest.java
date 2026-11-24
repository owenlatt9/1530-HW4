package edu.pitt.se;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class DataEntryProcessorCharacterizationTest {

    private final DataEntryProcessor processor = new DataEntryProcessor();

    // Patient test cases

    @Test
    void patient_validRecord_anonymizesName() {
        processor.setStrategy(new PatientDataStrategy());
        String out = processor.run("Name: Katie Bryce, Condition: unknown");
        assertEquals("[PATIENT] Name: *****, Condition: unknown", out);
    }

    @Test
    void patient_missingCondition_returnsInvalid() {
        processor.setStrategy(new PatientDataStrategy());
        assertEquals("Invalid patient record.",
                processor.run("Name: Katie Bryce"));
    }

    @Test
    void patient_typeIsCaseInsensitive() {
        processor.setStrategy(new PatientDataStrategy());
        String out = processor.run("Name: Meredith Grey, Condition: fatigue");
        assertEquals("[PATIENT] Name: *****, Condition: fatigue", out);
    }

    // Notes test cases
    @Test
    void notes_expandsAbbreviationsAndStripsFillers() {
        processor.setStrategy(new PhysicianNotesStrategy());
        String out = processor.run("pt reports uhm pain in left ankle");
        assertEquals("[NOTES] patient reports pain in left ankle", out);
    }

    @Test
    void notes_expandsBp() {
        processor.setStrategy(new PhysicianNotesStrategy());
        assertEquals("[NOTES] bp is elevated".replace("bp", "blood pressure"),
                processor.run("bp is elevated"));
    }

    // Schedule test cases
    @Test
    void schedule_normalizesTimes() {
        processor.setStrategy(new AppointmentScheduleStrategy());
        assertEquals("[SCHEDULE] Appointment at 09:00 with Dr. Grey",
                processor.run("Appointment at 9am with Dr. Grey"));
    }

    // Edge test cases
    // After refactoring: delete unknownType_returnsFallback (no type string anymore),
    // keep noStrategyConfigured_runThrows unchanged (it already uses run()).

    

    @Test
    void noStrategyConfigured_runThrows() {
        assertThrows(IllegalStateException.class, () -> processor.run("anything"));
    }
}
