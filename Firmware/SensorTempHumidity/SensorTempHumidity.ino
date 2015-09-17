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
#include <DHT.h>
#include <aJSON.h>

#define DEBUG
#define DHTTYPE DHT22
#define DHTPIN  2

DHT dht(DHTPIN, DHTTYPE, 11); // 11 works fine for ESP8266
float humidity, temp_f;  // Values read from sensor

// Generally, you should use "unsigned long" for variables that hold time
const long interval = 2000;              // interval at which to read sensor

// const char* ssid     = "YOUR WIFI SSID";
// const char* password = "PASSWORD WIFI";
//
// const char* publish_key = "pub-YOUR PUBLISH KEY";
// const char* suscribe_key = "sub-YOUR SUSCRIBE KEY";
// const char* secret_key = "sec-c-YOUR SECREY KEY";

const char* type = "ambient";
const char* channel = "ambient_sensor_001";
const char* channel_sensor_list = "sensors_list";
const char* host = "pubsub.pubnub.com";
const int httpPort = 80;

// Generally, you should use "unsigned long" for variables that hold time
unsigned long previousMillis = 0;        // will store last temp was read
aJsonObject *rootJson;
aJsonObject *attributesJson;

const char* streamId   = "....................";
const char* privateKey = "....................";

void setup() {

  Serial.begin(115200);
  delay(100);

  dht.begin();           // initialize temperature sensor

  // We start by connecting to a WiFi network
  Serial.println("MAC: ");
  Serial.println(WiFi.macAddress());

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
}



void loop() {
  delay(interval);

  Serial.print("connecting to ");
  Serial.println(host);

  // Use WiFiClient class to create TCP connections
  WiFiClient client;
  if (!client.connect(host, httpPort)) {
    Serial.println("connection failed");
    return;
  }

  gettemperature();       // read sensor

  // Create Json
  //{"type":"ambient","channel":"ambient_sensor_001","attributes":{"temp":29.30000,"humidity":55.50000}}
  rootJson=aJson.createObject();
  aJson.addStringToObject(rootJson,"type",type);
  aJson.addStringToObject(rootJson,"channel",channel);
  aJson.addItemToObject(rootJson,"attributes", attributesJson = aJson.createObject());
  aJson.addNumberToObject(attributesJson, "temp", temp_f);
  aJson.addNumberToObject(attributesJson, "humidity", humidity);

  String publishUrl = "/publish";
  publishUrl += "/";
  publishUrl += publish_key;
  publishUrl += "/";
  publishUrl += suscribe_key;
  publishUrl += "/";
  publishUrl += secret_key;
  publishUrl += "/";
  publishUrl += channel;
  publishUrl += "/0/";
  //publishUrl += "/%22T:"+String((int)temp_f)+"-H:"+String((int)humidity)+"%22";
  publishUrl += aJson.print(rootJson);

  Serial.print("Requesting URL: ");
  Serial.println(publishUrl);

  // This will send the request to the server
  client.print(String("GET ") + publishUrl + " HTTP/1.1\r\n" +
               "Host: " + host + "\r\n" +
               "Connection: close\r\n\r\n");
  delay(10);

  // Read all the lines of the reply from server and print them to Serial
  #ifdef DEBUG
  while(client.available()){
    String line = client.readStringUntil('\r');
    Serial.print(line);
  }

  Serial.println();
  Serial.println("closing connection");
  #endif
  client.stop();
  delay(100);
  aJson.deleteItem(rootJson);


  /**
   *  Send list of sensors.
  **/
  if (!client.connect(host, httpPort)) {
    Serial.println("connection failed");
    return;
  }

  // Create Json
  //{"type":"ambient","channel":"ambient_sensor_001","attributes":{"temp":29.30000,"humidity":55.50000}}
  rootJson=aJson.createObject();
  aJson.addStringToObject(rootJson,"type",type);
  aJson.addStringToObject(rootJson,"channel",channel);

  String sensorListUrl = "/publish";
  sensorListUrl += "/";
  sensorListUrl += publish_key;
  sensorListUrl += "/";
  sensorListUrl += suscribe_key;
  sensorListUrl += "/";
  sensorListUrl += secret_key;
  sensorListUrl += "/";
  sensorListUrl += channel_sensor_list;
  sensorListUrl += "/0/";
  //sensorListUrl += "/%22"+String(channel)+"%22";
  sensorListUrl += aJson.print(rootJson);

  Serial.print("Requesting URL: ");
  Serial.println(sensorListUrl);

  // This will send the request to the server
  client.print(String("GET ") + sensorListUrl + " HTTP/1.1\r\n" +
               "Host: " + host + "\r\n" +
               "Connection: close\r\n\r\n");
  delay(10);

  // Read all the lines of the reply from server and print them to Serial
  #ifdef DEBUG
  while(client.available()){
    String line = client.readStringUntil('\r');
    Serial.print(line);
  }

  Serial.println();
  Serial.println("closing connection");
  #endif
  client.stop();
  aJson.deleteItem(rootJson);

}

void gettemperature() {
  // Wait at least 2 seconds seconds between measurements.
  // if the difference between the current time and last time you read
  // the sensor is bigger than the interval you set, read the sensor
  // Works better than delay for things happening elsewhere also
  unsigned long currentMillis = millis();

  if(currentMillis - previousMillis >= interval) {
    // save the last time you read the sensor
    previousMillis = currentMillis;

    // Reading temperature for humidity takes about 250 milliseconds!
    // Sensor readings may also be up to 2 seconds 'old' (it's a very slow sensor)
    humidity = dht.readHumidity();          // Read humidity (percent)
    temp_f = dht.readTemperature();     // Read temperature as Fahrenheit
    // Check if any reads failed and exit early (to try again).
    if (isnan(humidity) || isnan(temp_f)) {
      Serial.println("Failed to read from DHT sensor!");
      return;
    }
  }
}
