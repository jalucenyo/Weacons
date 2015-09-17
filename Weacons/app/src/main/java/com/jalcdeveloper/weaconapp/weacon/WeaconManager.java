package com.jalcdeveloper.weaconapp.weacon;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jalcdeveloper.weaconapp.communication.PubNubHelper;
import com.pubnub.api.Callback;
import com.pubnub.api.Pubnub;
import com.pubnub.api.PubnubError;
import com.pubnub.api.PubnubException;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WeaconManager {

    private static String TAG = WeaconManager.class.getSimpleName();
    private static Pubnub pubnub;
    private static GsonBuilder gsonBuilder = new GsonBuilder();
    private static Gson gson = gsonBuilder.create();
    private static List<WeaconNode> weaconList;
    private static WeaconListener weaconListener;

    public interface WeaconListener {
        void onNewWeacon(WeaconNode weacon);
        void onError(String message);
    }

    /**
     * Discovery weacons sensors connected.
     * The param listener event discover sensor.
     *
     * @param listener
     */
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

                    WeaconNode weacon = gson.fromJson(message.toString(), WeaconNode.class);

                    if(!isContentWeaconNode(weacon.getChannel())) {

                        //WeaconNode weacon = new WeaconNode();
                        //weacon.setChannel(message.toString());
                        weaconList.add(weacon);
                        if (listener != null) weaconListener.onNewWeacon(weacon);

                    }
                }

                @Override
                public void errorCallback(String channel, PubnubError error) {
                    super.errorCallback(channel, error);
                    Log.e(TAG, error.getErrorString());
                }
            });
        }catch (PubnubException pex){
            if (listener != null )listener.onError(pex.getMessage());
        }

    }

    public static void suscribeWeacon(final WeaconNode weacon, final WeaconNodeListener listener){

        try {
            pubnub.subscribe(weacon.getChannel(), new Callback() {
                @Override
                public void successCallback(String channel, Object message) {
                    super.successCallback(channel, message);

                    WeaconNode wtemp = gson.fromJson(message.toString(), WeaconNode.class);
                    weacon.setType(wtemp.getType());
                    weacon.setAttributes(wtemp.getAttributes());

                    if(listener != null) listener.onUpdate(weacon);
                }

                @Override
                public void errorCallback(String channel, PubnubError error) {
                    super.errorCallback(channel, error);
                    Log.e(TAG, error.getErrorString());
                }
            });
        }catch (PubnubException pex){ pex.printStackTrace(); }
    }

    public static void unsuscribeWeacon(WeaconNode weacon){

    }

    public static void publishDiscoverWeacon(WeaconNode weacon){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(gson.toJson(weacon));
        }catch (JSONException ex){ ex.printStackTrace(); }
        pubnub.publish(PubNubHelper.CHANNEL_WEACON_LIST,
                jsonObject,
                new Callback() {
                    @Override
                    public void successCallback(String channel, Object message) {
                        super.successCallback(channel, message);
                        Log.d(TAG, "Ok! publish.");
                    }

                    @Override
                    public void errorCallback(String channel, PubnubError error) {
                        super.errorCallback(channel, error);
                        Log.e(TAG, error.getErrorString());
                    }
                });
    }

    public static void publishWeacon(WeaconNode weacon){

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(gson.toJson(weacon));
        }catch (JSONException ex){ ex.printStackTrace(); }
        pubnub.publish(weacon.getChannel(),
                jsonObject,
                new Callback() {
                    @Override
                    public void successCallback(String channel, Object message) {
                        super.successCallback(channel, message);
                        Log.d(TAG, "Ok! publish.");
                    }

                    @Override
                    public void errorCallback(String channel, PubnubError error) {
                        super.errorCallback(channel, error);
                        Log.e(TAG, error.getErrorString());
                    }
                });

    }

    public static boolean isContentWeaconNode(String channelWeacon){

        for (WeaconNode weacon:weaconList) {
            if (weacon.getChannel().equals(channelWeacon)){
                return true;
            }
        }
        return false;

    }

    public static WeaconNode getWeaconNode(String channel){
        for (WeaconNode weacon:weaconList) {
            if (weacon.getChannel().equals(channel)){
                return weacon;
            }
        }
        return null;
    }

    public static List<WeaconNode> getWeaconList(){
        return weaconList;
    }

}
