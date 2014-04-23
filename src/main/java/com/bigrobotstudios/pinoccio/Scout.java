package com.bigrobotstudios.pinoccio;

/**
 * Created by hectorurtubia on 4/21/14.
 */
public class Scout {
    private String id = new String();
    private boolean isOnline = false;
    private String name = new String();
    public String getId() {
        return id;
    }
    public void setId(String id){
        this.id = id;
    }
    public boolean getIsOnline() {
        return isOnline;
    }
    public void setIsOnline(boolean isOnline){
        this.isOnline = isOnline;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
}
