package edu.pitt.se;

public class DataEntryProcessor {

    private DataProcessingStrategy strategy;

    public void setStrategy(DataProcessingStrategy strategy) {
        this.strategy = strategy;
    }

    public String run(String input) {
        if (strategy == null) {
            throw new IllegalStateException("No processing strategy configured!");
        }

        return strategy.process(input);
    }

}
