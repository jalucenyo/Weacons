/* This program is free software: you can redistribute it and/or modify
*  it under the terms of the GNU General Public License as published by
*  the Free Software Foundation, either version 3 of the License, or
*  (at your option) any later version.
*
*  This program is distributed in the hope that it will be useful,
*  but WITHOUT ANY WARRANTY; without even the implied warranty of
*  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*  GNU General Public License for more details.
*
*  You should have received a copy of the GNU General Public License
*  along with this program.  If not, see <http://www.gnu.org/licenses/>.
*  
*  <jalucenyo@gmail.com> 13 of August, 2015
*  Jose A. Luceño
*/
#include <ESP8266WiFi.h>

//#define DEBUG
#define STRIP_PIN  2

// Generally, you should use "unsigned long" for variables that hold time
const long interval = 2000;              // interval at which to read sensor

const char* ssid     = "YOUR WIFI SSID";
const char* password = "PASSWORD WIFI";

const char* publish_key = "pub-YOUR PUBLISH KEY";
const char* suscribe_key = "sub-YOUR SUSCRIBE KEY";
const char* secret_key = "sec-c-YOUR SECREY KEY";
const char* channel = "strip_001";
const char* host = "pubsub.pubnub.com";
const int httpPort = 80;

//Ticker tasks;
WiFiClient client;
String timetoken = "0";
int value = 0;

void setup() {
  
  pinMode(STRIP_PIN, OUTPUT);
    
  Serial.begin(115200);
  delay(10);
  
  // We start by connecting to a WiFi network

  Serial.println();
  Serial.println();
  Serial.print("Connecting to ");
  Serial.println(ssid);
  
  WiFi.begin(ssid, password);
  
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }

  Serial.println("");
  Serial.println("WiFi connected");  
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());

  delay(2000);

  // Attach Ticker Tasks
  //tasks.attach(2.0, heartbeatTask); // HeartBeatTask every 2s

}

// -----------------------------------------
// TASK PUBLISH HearBeat
// -----------------------------------------
void heartbeatTask(){

  if (!client.connect(host, httpPort)) {
    Serial.println("connection failed");
    return;
  }
  
  String url = "/publish";
  url += "/";
  url += publish_key;
  url += "/";
  url += suscribe_key;
  url += "/";
  url += secret_key;
  url += "/";
  url += channel;
  url += "_heartbeat";
  url += "/0";
  url += "/%22Heartbear:"+String((int)millis())+"%22";
  
  Serial.print("Requesting URL: ");
  Serial.println(url);
  
  // This will send the request to the server
  client.print(String("GET ") + url + " HTTP/1.1\r\n" +
               "Host: " + host + "\r\n" + 
               "Connection: close\r\n\r\n");
  delay(10);
  
  // Read all the lines of the reply from server and print them to Serial
  while(client.available()){
    String line = client.readStringUntil('\r');
    Serial.print(line);
  }
  
  Serial.println();
  Serial.println("closing connection");

}
// -----------------------------------------
// END TASK - PUBLISH HearBeat
// -----------------------------------------


void loop() {

  #ifndef DEBUG
  Serial.print("connecting to ");
  Serial.println(host);
  #endif
  // Use WiFiClient class to create TCP connections
  delay(10);
  if (!client.connect(host, httpPort)) {
    Serial.println("connection failed");
    return;
  }

  String suscribeUrl = "/subscribe";
  suscribeUrl += "/";
  suscribeUrl += suscribe_key;
  suscribeUrl += "/";
  suscribeUrl += channel;
  suscribeUrl += "_value";
  suscribeUrl += "/0";
  suscribeUrl += "/";
  suscribeUrl += timetoken;

  #ifndef DEBUG
  Serial.print("Requesting URL: ");
  Serial.println(suscribeUrl);
  #endif

  // This will send the request to the server
  client.print(String("GET ") + suscribeUrl + " HTTP/1.1\r\n" +
               "Host: " + host + "\r\n" + 
               "Connection: keep-alive\r\n"+
               "Accept: */*\r\n\r\n");
               //"Connection: close\r\n\r\n");

  delay(10);
  while(client.connected()){
      while(client.available()){
        
        String line = client.readStringUntil('\r');
        //Serial.println(line);

        if(line.substring(1,3) == "[["){
          if(timetoken == "0"){
            timetoken = line.substring(6,23);
            client.stop();

            #ifndef DEBUG
            Serial.println("First Step: ");
            Serial.println(timetoken);
            Serial.println(line);
            #endif
            
          }else{
            if(line.length() > 2){
              timetoken = line.substring(line.lastIndexOf(',')+2,line.lastIndexOf(']')-1);
              int value = line.substring(line.lastIndexOf(',')-3,line.lastIndexOf(':')+2).toInt();          
              analogWrite(STRIP_PIN, value);
              Serial.println(value);
            }
            client.stop();
            #ifndef DEBUG
            Serial.println("Second Step: ");
            Serial.println(line);
            Serial.println(timetoken);
            #endif
            
          } 
        }
      }
  }

  #ifndef DEBUG
  Serial.println();
  Serial.println("closing connection");
  #endif
}


