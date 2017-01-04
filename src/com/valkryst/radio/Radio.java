package com.valkryst.radio;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

public class Radio <D> {
    /** A HashMap of events mapped to listening receivers. */
    private final ConcurrentHashMap<String, Set<Receiver<D>>> receivers = new ConcurrentHashMap<>();

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
     *         The data to transmit to the receivers.
     */
    public final void transmit(final String event, final D data) {
        if (event == null || event.isEmpty()) {
            throw new NullPointerException("The event cannot be null or empty.");
        }

        final Set<Receiver<D>> receivers = this.receivers.get(event);

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
    public final void addReceiver(final String event, final Receiver<D> receiver) {
        if (event == null || event.isEmpty()) {
            throw new NullPointerException("The event cannot be null or empty.");
        }

        if (receiver == null) {
            throw new NullPointerException("The receiver cannot be null.");
        }

        if (receivers.containsKey(event) == false) {
            receivers.put(event, new ConcurrentSkipListSet<>());
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
    public final void removeReceiver(final String event, final Receiver<D> receiver) {
        if (event == null || event.isEmpty()) {
            throw new NullPointerException("The event cannot be null or empty.");
        }

        if (receiver == null) {
            throw new NullPointerException("The receiver cannot be null.");
        }

        final Set<Receiver<D>> receivers = this.receivers.get(event);

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

        final Set<Receiver<D>> receivers = this.receivers.get(event);

        if (receivers != null) {
            receivers.clear();
        }
    }
}
