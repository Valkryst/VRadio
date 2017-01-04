package com.valkryst.radio;

import org.junit.Assert;
import org.junit.Test;

public class ReceiverTest implements Comparable<ReceiverTest>, Receiver<String> {
    private String data = "";

    @Override
    public void receive(final String event, final String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    @Override
    public int compareTo(final ReceiverTest other) {
        return this.getData().compareTo(other.getData());
    }

    @Test
    public void transmitA() {
        final Radio<String> radio = new Radio<>();
        final ReceiverTest receiver = new ReceiverTest();

        radio.addReceiver("Test Event", receiver);
        radio.transmit("Test Event");

        Assert.assertEquals(receiver.getData(), "");
    }

    @Test
    public void transmitB() {
        final Radio<String> radio = new Radio<>();
        final ReceiverTest receiver = new ReceiverTest();

        radio.addReceiver("Test Event", receiver);
        radio.transmit("Test Event", null);

        Assert.assertEquals(receiver.getData(), "");
    }

    @Test
    public void transmitC() {
        final Radio<String> radio = new Radio<>();
        final ReceiverTest receiver = new ReceiverTest();

        radio.addReceiver("Test Event", receiver);
        radio.transmit("Test Event", "Hello");

        Assert.assertEquals(receiver.getData(), "Hello");
    }

    @Test
    public void transmitD() {
        final Radio<String> radio = new Radio<>();
        final ReceiverTest receiver = new ReceiverTest();

        radio.addReceiver("Test Event", receiver);
        radio.transmit("Other Event", "Hello");

        Assert.assertEquals(receiver.getData(), "");
    }
}
