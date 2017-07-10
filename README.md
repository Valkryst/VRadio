#### License: 

Use this code however you wish. Modifications and improvements are welcome.

Please refer to the LICENSE file for additional information.

---

[![MIT License](https://img.shields.io/badge/license-MIT_License-green.svg)](https://github.com/Valkryst/VRadio/blob/master/LICENSE) ![](https://travis-ci.org/Valkryst/VRadio.svg?branch=master) [![codecov](https://codecov.io/gh/Valkryst/VRadio/branch/master/graph/badge.svg)](https://codecov.io/gh/Valkryst/VRadio) [![Release](https://jitpack.io/v/Valkryst/VRadio.svg)](https://jitpack.io/#Valkryst/VRadio)

## Jar Files & Maven

To use this project as a Maven dependency, click on the JitPack badge [![Release](https://jitpack.io/v/Valkryst/VRadio.svg)](https://jitpack.io/#Valkryst/VRadio), select a version, click the "Get it!" button, and then follow the instructions.

If you would rather use a Jar file, then you can find the Jars on the [releases](https://github.com/Valkryst/VRadio/releases) page.


## How to Use:

1) Create an instance of *Radio* to keep track of which receivers receive which events and to handle event transmissions.

2) Create one or more types of *Receivers* which implement the *Receiver* class.

3) Add instances of your *Receiver* objects to the *Radio*.

3) Transmit events through the *Radio* to the *Receivers* who will then handle the data as they see fit.

A rough example of this process can be seen in the *test* directory.



## JavaDoc Documentation:

Whenever a Travis CI build passes, the JavaDocs are auto-generated and made available at the following link.

https://valkryst.github.io/VRadio/.
