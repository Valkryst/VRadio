![](https://travis-ci.org/Valkryst/VRadio.svg?branch=master) [![Release](https://jitpack.io/v/Valkryst/VRadio.svg)](https://jitpack.io/#Valkryst/VRadio)

# VRadio

A thread-safe implementation of the publish-subscribe pattern.

## Jar Files & Maven

To use this project as a Maven dependency, click on the JitPack badge [![Release](https://jitpack.io/v/Valkryst/VRadio.svg)](https://jitpack.io/#Valkryst/VRadio), select a version, click the "Get it!" button, and then follow the instructions.

If you would rather use a Jar file, then you can find the Jars on the [releases](https://github.com/Valkryst/VRadio/releases) page.


## Examples

We'll be using the following Receiver class for all examples.

```java
public class TestReceiver implements Receiver<String> {
    private final String name;

    public TestReceiver(final String name) {
        this.name = name;
    }

    @Override
    public void receive(final String event, final String data) {
        System.out.println(name + " Received:");
        System.out.println("\tEvent:\t" + event);
        System.out.println("\tData:\t" + data);
    }
}
```

---

Construct a Radio and transmit an event with no data to the Receiver.
```java
final TestReceiver receiver = new TestReceiver("ReceiverA");
final Radio<String> radio = new Radio<>();

radio.addReceiver("EventA", receiver);
radio.transmit("EventA");
```

---

Construct a Radio and transmit two different events to three different Receivers.
```java
final TestReceiver receiverA = new TestReceiver("ReceiverA");
final TestReceiver receiverB = new TestReceiver("ReceiverB");
final TestReceiver receiverC = new TestReceiver("ReceiverC");
final Radio<String> radio = new Radio<>();

radio.addReceiver("EventA", receiverA);

radio.addReceiver("EventB", receiverB);

radio.addReceiver("EventA", receiverC);
radio.addReceiver("EventB", receiverC);

System.out.println("Transmitting EventA:");
radio.transmit("EventA", "DataA");

System.out.println("\n\nTransmitting EventB:");
radio.transmit("EventB", "DataB");
```

## JavaDoc Documentation:

Whenever a Travis CI build passes, the JavaDocs are auto-generated and made available at the following link.

https://valkryst.github.io/VRadio/.
