//Include the SoftwareSerial library
#include "SoftwareSerial.h"
#define RELE_1 6
#define RELE_2 7

//Create a new software  serial
SoftwareSerial bluetooth(0, 1); // TX, RX (Bluetooth)
  
int incomingByte;      // a variable to read incoming serial data into

void setup() {
  //Initialize the software serial
  bluetooth.begin(9600);
  
  // initialize the LED pin as an output:
  pinMode(RELE_1, OUTPUT);
  pinMode(RELE_2, OUTPUT);
}

void loop() {
  // see if there's incoming serial data:
  if (bluetooth.available() > 0) {
    // read the oldest byte in the serial buffer:
    incomingByte = bluetooth.read();
    // if it's a capital H (ASCII 72), turn on the LED:

    /******************RELE 1*******************/
    if (incomingByte == 'q') {
      digitalWrite(RELE_1, HIGH);
      bluetooth.println("LED: ON");
    }
    // if it's an L (ASCII 76) turn off the LED:
    if (incomingByte == 'w') {
      digitalWrite(RELE_1, LOW);
      bluetooth.println("LED: OFF");
    }
    
/******************RELE 2*******************/

    if (incomingByte == 'e') {
      digitalWrite(RELE_2, HIGH);
      bluetooth.println("LED: ON");
    }
    // if it's an L (ASCII 76) turn off the LED:
    if (incomingByte == 'r') {
      digitalWrite(RELE_2, LOW);
      bluetooth.println("LED: OFF");
    }
    
  }
}
