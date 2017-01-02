package com.valkryst.radio;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

public class Radio <Data> {
    /** A {@link HashMap} of events mapped to listening {@link Receiver Receivers}. */
    private final HashMap<String, Set<com.valkryst.radio.Receiver<Data>>> receivers = new HashMap<>();

    /**
     * Transmits an event without data.
     *
     * @param event
     *         The event to transmit.
     */
    public final void transmit(final String event) {
        if (event == null || event.isEmpty()) {
            throw new NullPointerException("A transmitted event cannot be null or empty.");
        }

        transmit(event, null);
    }

    /**
     * Transmits an event with data.
     *
     * @param event
     *         The event whose receivers are to be transmitted to.
     *
     * @param data
     *         The data to transmit to the {@link Receiver Receivers}.
     */
    public final void transmit(final String event, final Data data) {
        if (event == null || event.isEmpty()) {
            throw new NullPointerException("The event cannot be null or empty.");
        }

        final Set<com.valkryst.radio.Receiver<Data>> receivers = this.receivers.get(event);

        if (receivers != null) {
            receivers.forEach(receiver -> receiver.receive(event, data));
        }
    }

    /**
     * Adds a receiver to an event.
     *
     * @param event
     *         The event to add a receiver to.
     *
     * @param receiver
     *         The receiver to add.
     */
    public final void addReceiver(final String event, final com.valkryst.radio.Receiver<Data> receiver) {
        if (event == null || event.isEmpty()) {
            throw new NullPointerException("The event cannot be null or empty.");
        }

        if (receiver == null) {
            throw new NullPointerException("The receiver cannot be null.");
        }

        if (receivers.containsKey(event) == false) {
            receivers.put(event, new LinkedHashSet<>());
        }

        receivers.get(event).add(receiver);
    }

    /**
     * Removes a receiver from an event.
     *
     * @param event
     *         The event to remove a receiver from.
     *
     * @param receiver
     *         The receiver to remove.
     */
    public final void removeReceiver(final String event, final com.valkryst.radio.Receiver<Data> receiver) {
        if (event == null || event.isEmpty()) {
            throw new NullPointerException("The event cannot be null or empty.");
        }

        if (receiver == null) {
            throw new NullPointerException("The receiver cannot be null.");
        }

        final Set<com.valkryst.radio.Receiver<Data>> receivers = this.receivers.get(event);

        if (receivers != null) {
            receivers.remove(receiver);
        }
    }

    /**
     * Removes all receivers from an event.
     *
     * @param event
     *         The event to remove receivers from.
     */
    public final void removeReceivers(final String event) {
        if (event == null || event.isEmpty()) {
            throw new NullPointerException("The event cannot be null or empty.");
        }

        final Set<com.valkryst.radio.Receiver<Data>> receivers = this.receivers.get(event);

        if (receivers != null) {
            receivers.clear();
        }
    }
}
