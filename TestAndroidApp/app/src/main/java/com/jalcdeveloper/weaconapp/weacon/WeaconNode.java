package com.jalcdeveloper.weaconapp.weacon;


import java.util.HashMap;
import java.util.Map;

public class WeaconNode {

    private String type;
    private String channel;
    private Map<String, Object> attributes;

    public WeaconNode() {
        this.attributes = new HashMap<>();
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

    public Map<String,Object> getAttributes(){
        return attributes;
    }

    public void setAttributes(Map<String,Object> attributes){
        this.attributes = attributes;
    }

    public int getIntAttribute(String attributeKey){
        return (int)attributes.get(attributeKey);
    }

    public void setIntAttribute(String attributeKey, int value){
            attributes.put(attributeKey, value);
    }

    public double getDoubleAttribute(String attributeKey){
        return (double)attributes.get(attributeKey);
    }

    public void setDoubleAttribute(String attributeKey, double value){
        attributes.put(attributeKey, value);
    }

}
