package com.valkryst.radio;

import org.junit.Test;

public class RadioTest {
    @Test
    public void removeReceiverA() {
        final Radio<String> radio = new Radio<>();
        radio.removeReceiver(null, null);
    }

    @Test
    public void removeReceiverB() {
        final Radio<String> radio = new Radio<>();
        radio.removeReceiver("Test Event", null);
    }

    @Test
    public void removeReceiverC() {
        final Radio<String> radio = new Radio<>();
        final ReceiverTest receiver = new ReceiverTest();

        radio.removeReceiver("Test Event", receiver);
    }

    @Test
    public void removeReceiverD() {
        final Radio<String> radio = new Radio<>();
        final ReceiverTest receiver = new ReceiverTest();

        radio.addReceiver("Test Event", receiver);
        radio.removeReceiver("Test Event", receiver);
    }

    @Test
    public void removeReceiversA() {
        final Radio<String> radio = new Radio<>();
        radio.removeReceivers(null);
    }

    @Test
    public void removeReceiversB() {
        final Radio<String> radio = new Radio<>();
        radio.removeReceivers("Test Event");
    }

    @Test
    public void removeReceiversC() {
        final Radio<String> radio = new Radio<>();
        final ReceiverTest receiver = new ReceiverTest();

        radio.addReceiver("Test Event", receiver);
        radio.removeReceivers("Test Event");
    }
}
