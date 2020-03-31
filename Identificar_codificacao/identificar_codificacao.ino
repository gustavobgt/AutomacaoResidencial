#include <IRremote.h>

int RECV_PIN = 3;

IRrecv irrecv(RECV_PIN);
decode_results results;


void setup()
{
  
  Serial.begin(9600);
  irrecv.enableIRIn(); //Start the receiver
}



//Infinite loop
void loop() {
  if (irrecv.decode(&results)) {
    Serial.println(results.value, DEC);
    irrecv.resume(); //Receive the next value
  }
}
