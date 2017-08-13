
#include<SoftwareSerial.h>
char myByte;
char flag;
void setup() {
  // put your setup code here, to run once:
  pinMode(13,OUTPUT);
  pinMode(12,OUTPUT);
  pinMode(11,OUTPUT);
  pinMode(10,OUTPUT);
  
  Serial.begin(9600);
}

void loop() {
  // put your main code here, to run repeatedly:
  
      if(Serial.available()){
           myByte=Serial.read();
          if(myByte=='F'){
             Serial.print(myByte); 
             digitalWrite(10,HIGH);
             digitalWrite(11,LOW);
             digitalWrite(12,HIGH);
             digitalWrite(13,LOW);
            // flag=myByte;
          }
          else if(myByte=='B'){
             digitalWrite(10,LOW);
             digitalWrite(11,HIGH);
             digitalWrite(12,LOW);
             digitalWrite(13,HIGH);
             Serial.print(myByte); 
            // flag=myByte;      
          }
          else if(myByte=='R'){
            if(flag=='B'){

                digitalWrite(10,LOW);
                digitalWrite(11,HIGH);
                digitalWrite(12,LOW);
                digitalWrite(13,LOW);
                Serial.print(myByte);
                 
             }
             else{
              
                digitalWrite(10,HIGH);
                digitalWrite(11,LOW);
                digitalWrite(12,LOW);
                digitalWrite(13,HIGH);
                Serial.print(myByte);
              }
       
            
            
          }
          else if(myByte=='L'){

               if(flag=='B'){

               digitalWrite(10,LOW);
               digitalWrite(11,LOW);
               digitalWrite(12,LOW);
               digitalWrite(13,HIGH);
               Serial.print(myByte); 
            
                 
             }
             else{
              
                digitalWrite(10,LOW);
                digitalWrite(11,HIGH);
                digitalWrite(12,HIGH);
                digitalWrite(13,LOW);
                Serial.print(myByte); 
            
              }

            
             
          }
          else{
             digitalWrite(10,LOW);
             digitalWrite(11,LOW);
             digitalWrite(12,LOW);
             digitalWrite(13,LOW);
             Serial.print(myByte); 
            
            }   
 
}


}
