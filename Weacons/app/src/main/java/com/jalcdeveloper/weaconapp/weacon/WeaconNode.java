package com.jalcdeveloper.weaconapp.weacon;


import java.math.BigDecimal;
import java.math.RoundingMode;
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
        return round((double)attributes.get(attributeKey), 2);
    }

    public void setDoubleAttribute(String attributeKey, double value){
        attributes.put(attributeKey, value);
    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}
