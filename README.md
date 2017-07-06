#### License: 

Use this code however you wish. Modifications and improvements are welcome.

Please refer to the LICENSE file for additional information.

---

![](https://travis-ci.org/Valkryst/VRadio.svg?branch=master) [![codecov](https://codecov.io/gh/Valkryst/VRadio/branch/master/graph/badge.svg)](https://codecov.io/gh/Valkryst/VRadio) [![Release](https://jitpack.io/v/Valkryst/VRadio.svg)](https://jitpack.io/#Valkryst/VRadio)

## How to Setup:

1. Clone the project from GitHub.
2. Open your project in IntelliJ.
3. Open the *Project Structure* menu. (S + ALT + CTRL + SHIFT)
4. Enter the *Modules* subsection.
5. Click the green "+" at the top of the window.
6. Select *Import Module*.
7. Navigate to the cloned project and double-click on *pom.xml*.
8. Click on the name of your project in the *Modules* subsection.
9. Enter the *Dependencies* tab of your project.
10. Click on the green "+" at the right-edge of the window.
11. Click *Module Dependency...*.
12. Select *VRadio*.

You can now use VRadio in your project.

---

## How to Use:

1) Create an instance of *Radio* to keep track of which receivers receive which events and to handle event transmissions.

2) Create one or more types of *Receivers* which implement the *Receiver* class.

3) Add instances of your *Receiver* objects to the *Radio*.

3) Transmit events through the *Radio* to the *Receivers* who will then handle the data as they see fit.

A rough example of this process can be seen in the *test* directory.



### JavaDoc Documentation:

Whenever a Travis CI build passes, the JavaDocs are auto-generated and made available at the following link.

https://valkryst.github.io/VRadio/.
