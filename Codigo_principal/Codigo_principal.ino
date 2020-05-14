#include "dht.h"
#include <IRremote.h>


#define RELE_1 6
#define RELE_2 7
#define RELE_3 5

#define pinDHT A2

int RECV_PIN = 3;
bool estado_R1 = 0;
bool estado_R2 = 0;

IRrecv irrecv(RECV_PIN);
decode_results results;
dht DHT;

char aux;

void setup()
{
  
  pinMode(RELE_1,OUTPUT);
  digitalWrite(RELE_1,LOW);
  pinMode(RELE_2,OUTPUT);
  digitalWrite(RELE_2,LOW);
  pinMode(RELE_3, OUTPUT);
  digitalWrite(RELE_3, LOW);
  
  Serial.begin(9600);
  irrecv.enableIRIn(); //Inicia o receptor
}



//Infinite loop
void loop() {
  while(Serial.available()){
    aux = Serial.read();
    
  }
  Serial.println(aux); 

  DHT.read11(pinDHT);
  Serial.println(DHT.humidity);
  Serial.println(DHT.temperature, 0);
 

  switch(aux){
    case 'a':
      Serial.println("RELE 1 LIGADO");
      digitalWrite(RELE_1, HIGH);
      break;

    case 'b':
      Serial.println("RELE 1 DESLIGADO");
      digitalWrite(RELE_1, LOW);
      break;

    case 'c':
      Serial.println("RELE 2 LIGADO");
      digitalWrite(RELE_2, HIGH);
      break;

    case 'd':
      Serial.println("RELE 2 DESLIGADO");
      digitalWrite(RELE_2, LOW);
      break;

    case 'e': 
      Serial.println("RELE 3 LIGADO");
      digitalWrite(RELE_3, HIGH);
      break;
      
    case 'f':
      Serial.println("RELE 3 DESLIGADO");
      digitalWrite(RELE_3, LOW);
      break;
  }
 

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
