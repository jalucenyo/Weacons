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
package com.jalcdeveloper.weaconapp.communication;

public class PubNubHelper  {

    public static final String PUBLISH_KEY = "pub-YOUR PUBLISH KEY";
    public static final String SUSCRIBE_KEY = "sub-YOUR SUSCRIBE KEY";
    public static final String SECRET_KEY = "sec-c-YOUR SECREY KEY";
    public static final String CHANNEL_WEACON_LIST = "sensors_list";

//    private static PubNubHelperListener listener;
//    private static List<WeaconNode> sensorsList;
//    private static Pubnub pubnub;
//
//    public static Pubnub build(){
//        if(pubnub == null){
//            pubnub = new Pubnub(PUBLISH_KEY, SUSCRIBE_KEY, SECRET_KEY);
//            sensorsList = new ArrayList<>();
//        }
//
//        try {
//            assert pubnub != null;
//
//            /**
//             * Suscribe to channel "sensors_list" every sensor emit
//             * periodical message in channel, add in list sensors_list.
//             */
//            pubnub.subscribe(CHANNEL_SENSORS_LIST, new Callback() {
//                @Override
//                public void successCallback(String channel, Object message) {
//                    super.successCallback(channel, message);
//
//                    /**
//                     *   Search of duplicate sensor.
//                     */
//                    for (WeaconNode sensor:sensorsList) {
//                        if (sensor.getChannel().equals(message.toString())){
//                            return;
//                        }
//                    }
//
//                    final WeaconNode sensor =  new WeaconNode();
//                    sensor.setChannel(message.toString());
//
//                    /**
//                     *  Suscribe channel of sensor.
//                     */
//                    try {
//                        pubnub.subscribe(sensor.getChannel(), new Callback() {
//                            @Override
//                            public void successCallback(String channel, Object message) {
//                                super.successCallback(channel, message);
//                                sensor.setMessage(message.toString());
//                                if(sensor.getListener() != null) sensor.getListener().onUpdate();
//                            }
//                        });
//                    }catch (PubnubException ex){ ex.printStackTrace(); }
//
//                    sensorsList.add(sensor);
//                    if(listener != null) listener.onSensorDiscovery(sensor);
//
//                }
//            });
//        }catch (PubnubException ex){ ex.printStackTrace(); }
//
//        return pubnub;
//    }
//
//    public static void setListener( PubNubHelperListener listener1){
//        listener = listener1;
//    }
//
//    public static List<WeaconNode> getSensors(){
//        return sensorsList;
//    }
//
//    public static String getPublishKey(){
//        return PUBLISH_KEY;
//    }
//
//    public static String getSuscribeKey(){
//        return SUSCRIBE_KEY;
//    }
//
//    public static String getSecretKey(){
//        return SECRET_KEY;
//    }
//
//    public interface PubNubHelperListener{
//        void onSensorDiscovery(WeaconNode sensor);
//    }


}
