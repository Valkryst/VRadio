package com.valkryst.VRadio;

import java.util.Collections;
import java.util.HashSet;
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
     *         The event to transmit.
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
     */
    public final void transmit(final String event, final D data) {
        if (! isStringNullOrEmpty(event)) {
            this.receivers.getOrDefault(event, Collections.emptySet())
                          .forEach(receiver -> receiver.receive(event, data));
        }
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
     */
    public final void addReceiver(final String event, final Receiver<D> receiver) {
        if (areArgumentsInValidState(event, receiver)) {
            receivers.putIfAbsent(event, new HashSet<>());
            receivers.get(event).add(receiver);
        }
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
     */
    public final void removeReceiver(final String event, final Receiver<D> receiver) {
        if (areArgumentsInValidState(event, receiver)) {
            this.receivers.getOrDefault(event, Collections.emptySet())
                          .remove(receiver);
        }
    }

    /**
     * Removes all receivers from an event.
     *
     * Does nothing if the event is null or empty.
     *
     * @param event
     *         The event to remove receivers from.
     */
    public final void removeReceivers(final String event) {
        if (! isStringNullOrEmpty(event)) {
            this.receivers.getOrDefault(event, Collections.emptySet())
                          .clear();
        }
    }

    /**
     * Determines if the string is non-null and non-empty and if the object is non-null.
     *
     * @param string
     *         The string to check.
     *
     * @param object
     *         The object to check.
     *
     * @return
     *         Whether or not the string is non-null and non-empty and if the object is non-null.
     */
    private boolean areArgumentsInValidState(final String string, final Object object) {
        boolean canProceed = isStringNullOrEmpty(string) == false;
        canProceed &= object != null;

        return canProceed;
    }

    /**
     * Determines whether or not a string is null or empty.
     *
     * @param string
     *         The string to check.
     *
     * @return
     *         Whether or not the string is null or empty.
     */
    private boolean isStringNullOrEmpty(final String string) {
        return string == null || string.isEmpty();
    }
}
