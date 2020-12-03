package net.mikej.connectors.api;

public enum FlowState {
    STARTED(0),
    STOPPED(1);

    private final int value;
    private FlowState(int value) {
        this.value = value;
    }

    public int getValue() { return value; }
}
