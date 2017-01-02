package com.valkryst.radio;

public class ReceiverTest implements Receiver<String> {
    private String data = "";

    @Override
    public void receive(final String event, final String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}
