package com.valkryst.VRadio;

import lombok.NonNull;

public interface Receiver<D> {
    /**
     * Receives data from a transmission.
     *
     * @param event
     *         The event that caused the transmission.
     *
     * @param data
     *         The received data.
     *
     * @throws NullPointerException
     *        If the event or data is null.
     */
    void receive(final @NonNull String event, final @NonNull D data);
}
