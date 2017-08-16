package com.valkryst.VRadio;

import lombok.Getter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RadioTest {
    private Radio<String> radio;
    private ReceiverTest receiver;
    private final String event = "Test Event";
    private final String data = "Test Data";

    @Before
    public void initializeRadioAndReceiver() {
        radio = new Radio<>();
        receiver = new ReceiverTest();
    }

    @Test
    public void testTransmit_oneParam_withValidInput() {
        radio.addReceiver(event, receiver);
        radio.transmit(event);
        Assert.assertEquals(event, receiver.getEvent());
    }

    @Test
    public void testTransmit_twoParams_withValidInput() {
        radio.addReceiver(event, receiver);
        radio.transmit(event, data);
        Assert.assertEquals(event, receiver.getEvent());
        Assert.assertEquals(data, receiver.getData());
    }

    @Test(expected=NullPointerException.class)
    public void testTransmit_twoParams_withNullEvent() {
        radio.addReceiver(event, receiver);
        radio.transmit(null, data);
    }

    @Test
    public void testTransmit_twoParams_withNullData() {
        radio.addReceiver(event, receiver);
        radio.transmit(event, null);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testTransmit_twoParams_withEmptyEvent() {
        radio.addReceiver(event, receiver);
        radio.transmit("", data);
    }

    @Test
    public void testAddReceiver_withValidInput() {
        radio.addReceiver(event, receiver);
    }

    @Test(expected=NullPointerException.class)
    public void testAddReceiver_withNullEvent() {
        radio.addReceiver(null, receiver);
    }

    @Test
    public void testAddReceiver_withEmptyEvent() {
        radio.addReceiver("", receiver);
    }

    @Test(expected=NullPointerException.class)
    public void testAddReceiver_withNullReceiver() {
        radio.addReceiver(event, null);
    }

    @Test
    public void testRemoveReceiver_withValidInput() {
        radio.addReceiver(event, receiver);
        radio.removeReceiver(event, receiver);
    }

    @Test(expected=NullPointerException.class)
    public void testRemoveReceiver_withNullEvent() {
        radio.addReceiver(event, receiver);
        radio.removeReceiver(null, receiver);
    }

    @Test
    public void testRemoveReceiver_withEmptyEvent() {
        radio.addReceiver(event, receiver);
        radio.removeReceiver("", receiver);
    }

    @Test(expected=NullPointerException.class)
    public void testRemoveReceiver_withNullReceiver() {
        radio.addReceiver(event, receiver);
        radio.removeReceiver(event, null);
    }

    @Test
    public void testRemoveReceivers_withValidInput() {
        radio.addReceiver(event, receiver);
        radio.removeReceivers(event);
    }

    @Test(expected=NullPointerException.class)
    public void testRemoveReceivers_withNullEvent() {
        radio.addReceiver(event, receiver);
        radio.removeReceivers(null);
    }

    @Test
    public void testRemoveReceivers_withEmptyEvent() {
        radio.addReceiver(event, receiver);
        radio.removeReceivers("");
    }

    @Test
    public void ensureConcurrentAccessWorks() {
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

    public class ReceiverTest implements Receiver<String> {
        @Getter private String event = null;
        @Getter private String data = null;

        @Override
        public void receive(final String event, final String data) {
            this.event = event;
            this.data = data;
        }
    }
}
