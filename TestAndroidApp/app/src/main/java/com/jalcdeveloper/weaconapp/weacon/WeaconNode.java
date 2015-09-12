package com.jalcdeveloper.weaconapp.weacon;

public class WeaconNode {

    private String type;
    private String channel;
    private String description;
    private String message;
    private WeaconNodeListener weaconNodeListener;

    public interface WeaconNodeListener{
        void onUpdate(WeaconNode weacon);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public WeaconNodeListener getListener() {
        return weaconNodeListener;
    }

    public void setListener(WeaconNodeListener listener) {
        this.weaconNodeListener = listener;
    }

}
