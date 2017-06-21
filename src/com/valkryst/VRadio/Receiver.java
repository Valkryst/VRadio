package com.valkryst.VRadio;

public interface Receiver<D> {
    /**
     * Receives data from a transmission.
     *
     * @param event
     *         The event that caused the transmission.
     *
     * @param data
     *         The received data.
     */
    void receive(String event, D data);
}
