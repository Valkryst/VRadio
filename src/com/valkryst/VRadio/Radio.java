package com.valkryst.VRadio;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Radio <D> {
    /** A HashMap of events mapped to listening receivers. */
    private final ConcurrentMap<String, Set<Receiver<D>>> receivers = new ConcurrentHashMap<>();

    /**
     * Transmits an event without data.
     *
     * @param event
     *        The event to transmit.
     */
    public final void transmit(final String event) {
        transmit(event, null);
    }

    /**
     * Transmits an event with data.
     *
     * Does nothing if the event is null or empty.
     *
     * @param event
     *         The event whose receivers are to be transmitted to.
     *
     * @param data
     *         The data to transmit to the receivers.
     *
     * @throws NullPointerException
     *        If the event is null.
     *
     * @throws IllegalArgumentException
     *        If the event is empty.
     */
    public final void transmit(final String event, final D data) {
        Objects.requireNonNull(event);

        if (event.isEmpty()) {
            throw new IllegalArgumentException("The event cannot be empty.");
        }

        this.receivers.getOrDefault(event, Collections.emptySet())
                      .forEach(receiver -> receiver.receive(event, data));
    }

    /**
     * Adds a receiver to an event.
     *
     * Does nothing if the event is null or empty.
     * Does nothing if the receiver is null.
     *
     * @param event
     *         The event to add a receiver to.
     *
     * @param receiver
     *         The receiver to add.
     *
     * @return
     *        If the receiver was added.
     */
    public final boolean addReceiver(final String event, final Receiver<D> receiver) {
        if (event != null && event.isEmpty() == false) {
            if (receiver != null) {
                receivers.putIfAbsent(event, ConcurrentHashMap.newKeySet());
                receivers.get(event).add(receiver);
                return true;
            }
        }

        return false;
    }

    /**
     * Removes a receiver from an event.
     *
     * Does nothing if the event is null or empty.
     * Does nothing if the receiver is null.
     *
     * @param event
     *         The event to remove a receiver from.
     *
     * @param receiver
     *         The receiver to remove.
     *
     * @return
     *        If the receiver was removed.
     */
    public final boolean removeReceiver(final String event, final Receiver<D> receiver) {
        if (event != null && event.isEmpty()) {
            if (receiver != null) {
                receivers.getOrDefault(event, Collections.emptySet()).remove(receiver);
                return true;
            }
        }

        return false;
    }

    /**
     * Removes all receivers from an event.
     *
     * Does nothing if the event is null or empty.
     *
     * @param event
     *         The event to remove receivers from.
     *
     * @return
     *        If the receivers were removed.
     */
    public final boolean removeReceivers(final String event) {
        if (event != null && event.isEmpty() == false) {
            receivers.getOrDefault(event, Collections.emptySet()).clear();
            return true;
        }

        return false;
    }
}
