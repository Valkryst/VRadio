package com.valkryst.radio;

public interface Receiver<Data> {
    /**
     * Receives data from a transmission.
     *
     * @param event
     *         The event that caused the transmission.
     *
     * @param data
     *         The received data.
     */
    void receive(final String event, final Data data);
}
