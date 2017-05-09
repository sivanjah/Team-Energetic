#include <LiquidCrystal.h>
#include <Wire.h>
#include <Adafruit_MLX90614.h>

Adafruit_MLX90614 mlx = Adafruit_MLX90614();

LiquidCrystal lcd(12, 11, 5, 4, 3, 2);
int value;

void setup() {
 
  lcd.begin(16, 2);
  Serial.begin(9600);
  pinMode(10, INPUT); // Setup for leads off detection LO +
  pinMode(9, INPUT); // Setup for leads off detection LO -
  mlx.begin(); 
  
}

void loop() {
  
  Serial.print("Object Temp = "); Serial.print(mlx.readObjectTempC()); Serial.println("*C");
  

  lcd.setCursor(0, 1);
  lcd.print("Temp");
  lcd.setCursor(8, 1);
  lcd.print(mlx.readObjectTempC());
  
  if((digitalRead(10) == 1)||(digitalRead(9) == 1))
  {
    Serial.println('Pls Connect');
    lcd.setCursor(0, 0);
      lcd.print("Pls Connect");
     
  }
  else{
    // send the value of analog input 0:
      value=analogRead(A0);
      Serial.println(value);
      lcd.setCursor(0, 0);
      lcd.print("Pulse");
      lcd.setCursor(8, 0);
      lcd.print(value);
      
  }
  //Wait for a bit to keep serial data from saturating
 delay(1000);
}
