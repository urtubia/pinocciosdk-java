package com.bigrobotstudios.pinoccio;

/**
 * Created by hectorurtubia on 4/21/14.
 */
public class Troop {
    private String id;
    private String name;
    private boolean isOnline;
    private PinoccioSDK sdk;

    void setId(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }

    void setName(String name){
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    void setIsOnline(boolean isOnline){
        this.isOnline = isOnline;
    }

    public boolean getIsOnline(){
        return isOnline;
    }

    @Override
    public String toString(){
        return name;
    }
}
