package com.jalcdeveloper.testandroidhome.weacon;

import com.jalcdeveloper.testandroidhome.communication.PubNubHelper;
import com.pubnub.api.Callback;
import com.pubnub.api.Pubnub;
import com.pubnub.api.PubnubException;

import java.util.ArrayList;
import java.util.List;

public class WeaconManager {

    private static Pubnub pubnub;
    private static List<WeaconNode> weaconList;
    private static WeaconListener weaconListener;

    public interface WeaconListener {
        void onNewWeacon(WeaconNode weacon);
        void onError(String message);
    }

    public static void startDiscovery(final WeaconListener listener){
        weaconListener = listener;

        if(weaconList == null) weaconList = new ArrayList<>();

        if(pubnub == null ) pubnub = new Pubnub(PubNubHelper.PUBLISH_KEY,
                                                PubNubHelper.SUSCRIBE_KEY,
                                                PubNubHelper.SECRET_KEY);

        try {

            pubnub.subscribe(PubNubHelper.CHANNEL_WEACON_LIST, new Callback() {
                @Override
                public void successCallback(String channel, Object message) {
                    super.successCallback(channel, message);

                    if(!isContentWeaconNode(message.toString())) {

                        WeaconNode weacon = new WeaconNode();
                        weacon.setChannel(message.toString());
                        weaconList.add(weacon);
                        if (listener != null) weaconListener.onNewWeacon(weacon);


                    }
                }
            });
        }catch (PubnubException pex){
            if (listener != null )listener.onError(pex.getMessage());
        }

    }

    public static void suscribeWeacon(final WeaconNode weacon, WeaconNode.WeaconNodeListener listener){
        weacon.setListener(listener);
        try {
            pubnub.subscribe(weacon.getChannel(), new Callback() {
                @Override
                public void successCallback(String channel, Object message) {
                    super.successCallback(channel, message);
                    weacon.setMessage(message.toString());
                    weacon.getListener().onUpdate(weacon);
                }
            });
        }catch (PubnubException pex){ pex.printStackTrace(); }
    }

    public static void unsuscribeWeacon(WeaconNode weacon){

    }

    public static void publishWeacon(WeaconNode weacon){

    }

    public static boolean isContentWeaconNode(String channelWeacon){

        for (WeaconNode weacon:weaconList) {
            if (weacon.getChannel().equals(channelWeacon)){
                return true;
            }
        }
        return false;

    }

    public static List<WeaconNode> getWeaconList(){
        return weaconList;
    }

}
