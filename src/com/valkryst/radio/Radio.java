package com.valkryst.radio;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListSet;

public class Radio <D> {
    /** A HashMap of events mapped to listening receivers. */
    private final ConcurrentMap<String, Set<Receiver<D>>> receivers = new ConcurrentHashMap<>();

    /**
     * Transmits an event without data.
     *
     * Does nothing if the event is null or empty.
     *
     * @param event
     *         The event to transmit.
     */
    public final void transmit(final String event) {
        if (! isStringNullOrEmpty(event)) {
            transmit(event, null);
        }
    }

    /**
     * Transmits an event with data.
     *
     * Does nothing if the event is null or empty.
     * Does nothing if the data is null.
     *
     * @param event
     *         The event whose receivers are to be transmitted to.
     *
     * @param data
     *         The data to transmit to the receivers.
     */
    public final void transmit(final String event, final D data) {
        boolean canProceed = isStringNullOrEmpty(event) == false;
        canProceed &= data != null;

        if (canProceed) {
            final Set<Receiver<D>> receivers = this.receivers.get(event);

            if (receivers != null) {
                receivers.forEach(receiver -> receiver.receive(event, data));
            }
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
        boolean canProceed = isStringNullOrEmpty(event) == false;
        canProceed &= receiver != null;

        if (canProceed) {
            receivers.putIfAbsent(event, new ConcurrentSkipListSet<>());
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
        boolean canProceed = isStringNullOrEmpty(event) == false;
        canProceed &= receiver != null;

        if (canProceed) {
            final Set<Receiver<D>> receivers = this.receivers.get(event);

            if (receivers != null) {
                receivers.remove(receiver);
            }
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
            final Set<Receiver<D>> receivers = this.receivers.get(event);

            if (receivers != null) {
                receivers.clear();
            }
        }
    }

    /**
     * Determines whether or not a string is null or empty.
     *
     * @param s
     *         The string to check.
     *
     * @return
     *         Whether or not the string is null or empty.
     */
    private boolean isStringNullOrEmpty(final String s) {
        return s == null || s.isEmpty();
    }
}
