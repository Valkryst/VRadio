package com.valkryst.VRadio;

import lombok.Getter;
import org.junit.Assert;
import org.junit.Test;

public class ReceiverTest implements Receiver<String> {
    @Getter private String event = null;
    @Getter private String data = null;

    @Override
    public void receive(final String event, final String data) {
        this.event = event;
        this.data = data;
    }

    @Test
    public void transmitA() {
        final Radio<String> radio = new Radio<>();
        final ReceiverTest receiver = new ReceiverTest();

        radio.addReceiver("Test Event", receiver);
        radio.transmit("Test Event");

        Assert.assertEquals(null, receiver.getData());
    }

    @Test
    public void transmitB() {
        final Radio<String> radio = new Radio<>();
        final ReceiverTest receiver = new ReceiverTest();

        radio.addReceiver("Test Event", receiver);
        radio.transmit("Test Event", null);

        Assert.assertEquals(null, receiver.getData());
    }

    @Test
    public void transmitC() {
        final Radio<String> radio = new Radio<>();
        final ReceiverTest receiver = new ReceiverTest();

        radio.addReceiver("Test Event", receiver);
        radio.transmit("Test Event", "Hello");

        Assert.assertEquals("Hello", receiver.getData());
    }

    @Test
    public void transmitD() {
        final Radio<String> radio = new Radio<>();
        final ReceiverTest receiver = new ReceiverTest();

        radio.addReceiver("Test Event", receiver);
        radio.transmit("Other Event", "Hello");

        Assert.assertEquals("", receiver.getData());
    }
}
