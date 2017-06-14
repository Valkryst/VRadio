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

    @Test
    public void ensureConcurrentAccessWorks() {
        final Radio<String> radio = new Radio<>();

        final Runnable runnableA = () -> {
            for (int i = 0 ; i < 500 ; i++) {
                radio.addReceiver(String.valueOf(i), new ReceiverTest());
            }

            for (int i = 0 ; i < 500 ; i++) {
                radio.transmit(String.valueOf(i));
            }

            for (int i = 0 ; i < 500 ; i++) {
                radio.removeReceivers(String.valueOf(i));
            }
        };

        final Runnable runnableB = () -> {
            for (int i = 500 ; i < 1000 ; i++) {
                radio.addReceiver(String.valueOf(i), new ReceiverTest());
            }

            for (int i = 500 ; i < 1000 ; i++) {
                radio.transmit(String.valueOf(i));
            }

            for (int i = 500 ; i < 1000 ; i++) {
                radio.removeReceivers(String.valueOf(i));
            }
        };


        final Thread threadA = new Thread(runnableA);
        final Thread threadB = new Thread(runnableB);
        threadA.start();
        threadB.start();

        try {
            threadA.join();
            threadB.join();
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
    }
}
