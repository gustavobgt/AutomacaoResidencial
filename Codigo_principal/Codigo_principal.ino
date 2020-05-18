#include "dht.h"
#include <IRremote.h>


#define RELE_1 6
#define RELE_2 7
#define RELE_3 5

#define pinDHT A2

int RECV_PIN = 3;
bool estado_R1 = 0;
bool estado_R2 = 0;
char aux;

boolean statusRele1 = false;
boolean statusRele2 = false;
boolean statusRele3 = false;


IRrecv irrecv(RECV_PIN);
decode_results results;
dht DHT;

void setup()
{
  
  pinMode(RELE_1,OUTPUT);
  digitalWrite(RELE_1,LOW);
  pinMode(RELE_2,OUTPUT);
  digitalWrite(RELE_2,LOW);
  pinMode(RELE_3, OUTPUT);
  digitalWrite(RELE_3, LOW);

  pinMode(A0, INPUT); //TEMPORARIO
  
  Serial.begin(9600);
  irrecv.enableIRIn(); //Inicia o receptor
}



//Infinite loop
void loop() {
  while(Serial.available()){
    aux = Serial.read();
    
  }
  DHT.read11(pinDHT);
  //Serial.println(DHT.humidity);
  //Serial.println(DHT.temperature, 0);

  Serial.println(analogRead(A0));
  Serial.println(aux);
  Serial.println(analogRead(A0));
  Serial.println(" ");

  switch(aux){
    case 'a':
      statusRele1 = !statusRele1;
      digitalWrite(RELE_1, statusRele1);
      break;

    case 'b':
      statusRele2 = !statusRele2;
      digitalWrite(RELE_2, statusRele2);
      break;

    case 'c':
      statusRele3 = !statusRele3;
      digitalWrite(RELE_3, statusRele3);
      break;
  }

  aux = 0;

  if (irrecv.decode(&results)) {
    Serial.println(results.value, DEC);
    irrecv.resume(); //Receive the next value
  }

       /****************RELE 1**********************/

  if(results.value == 268570863 && estado_R1 == 0)
  {
    digitalWrite(RELE_1,1);
    delay(20);
    estado_R1 = 1;
    delay(500);
    
    results.value = 0;
    /*precisa receber 0 se não o valor do resultado se manterá 
     o mesmo até a proxima leitura fazendo com que entre no 
     outro "if" e desligue o RELE*/
  }

  if(results.value == 268570863 && estado_R1 == 1)
  {
    digitalWrite(RELE_1,0);
    delay(20);
    estado_R1 = 0;
    delay(500);

    results.value = 0;
  }

       /****************RELE 2**********************/

   if(results.value == 268603503 && estado_R2 == 0)
{
    digitalWrite(RELE_2,1);
    delay(20);
    estado_R2 = 1;
    delay(500);
    
    results.value = 0;
  }

  if(results.value == 268603503 && estado_R2 == 1)
  {
    digitalWrite(RELE_2,0);
    delay(20);
    estado_R2 = 0;
    delay(500);

    results.value = 0;
  }

}
