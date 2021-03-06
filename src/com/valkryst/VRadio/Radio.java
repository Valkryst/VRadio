package com.valkryst.VRadio;

import lombok.NonNull;

import java.util.Collections;
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
     *
     * @throws NullPointerException
     *        If the event is null.
     */
    public final void transmit(final @NonNull String event) {
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
    public final void transmit(final @NonNull String event, final D data) {
        if (event.isEmpty()) {
            throw new IllegalArgumentException("The event cannot be empty.");
        }

        this.receivers.getOrDefault(event, Collections.emptySet())
                      .forEach(receiver -> receiver.receive(event, data));
    }

    /**
     * Adds a receiver to an event.
     *
     * @param event
     *         The event to add a receiver to.
     *
     * @param receiver
     *         The receiver to add.
     *
     * @throws NullPointerException
     *        If the event or receiver is null.
     */
    public final void addReceiver(final @NonNull String event, final @NonNull Receiver<D> receiver) {
        receivers.putIfAbsent(event, ConcurrentHashMap.newKeySet());
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
     *
     * @throws NullPointerException
     *        If the event or receiver is null.
     */
    public final void removeReceiver(final @NonNull String event, final @NonNull Receiver<D> receiver) {
        receivers.getOrDefault(event, Collections.emptySet()).remove(receiver);
    }

    /**
     * Removes all receivers from an event.
     *
     * @param event
     *         The event to remove receivers from.
     *
     * @throws NullPointerException
     *        If the event is null.
     */
    public final void removeReceivers(final @NonNull String event) {
        receivers.getOrDefault(event, Collections.emptySet()).clear();
    }
}
