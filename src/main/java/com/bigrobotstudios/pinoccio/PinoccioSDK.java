package com.bigrobotstudios.pinoccio;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;

/**
 * Created by hector urtubia on 4/20/14.
 */
public class PinoccioSDK {

    public static class PinoccioSDKConfig {
        private String baseUrl;

        public PinoccioSDKConfig()
        {
            baseUrl = "https://api.pinocc.io/v1/";
        }

        public String getBaseUrl(){
            return this.baseUrl;
        }
    }

    public static PinoccioSDK login(String username, String password){
        PinoccioSDKConfig config = new PinoccioSDKConfig();
        RestClient restClient = new RestClient(config.getBaseUrl() + "login");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", username);
        jsonObject.put("password", password);
        restClient.setJsonData(jsonObject);
        String response = restClient.post();
        JSONTokener tokener = new JSONTokener(response);
        Object root = tokener.nextValue();
        if(root instanceof  JSONObject){
            try {
                Object data = ((JSONObject) root).get("data");
                String token = (String) ((JSONObject) data).get("token");
                return new PinoccioSDK(token);
            }catch (JSONException e){}
        }
        return null;
    }

    private PinoccioSDKConfig config;
    private String token;
    private boolean isLoggedIn;
    private boolean isReadOnly;

    private PinoccioSDK(String token){
        this.token = token;
        this.config = new PinoccioSDKConfig();
    }

    public ArrayList<Troop> getTroops()
    {
        RestClient restClient = new RestClient(config.getBaseUrl() + "troops?token=" + token);
        String response = restClient.get();
        JSONTokener tokener = new JSONTokener(response);
        Object root = tokener.nextValue();
        if(root instanceof JSONObject){
            try{
                ArrayList<Troop> returnValue = new ArrayList<Troop>();
                JSONArray data = (JSONArray)((JSONObject) root).get("data");
                for(int i = 0; i < data.length(); i++){
                    JSONObject troopObject = (JSONObject)data.get(i);
                    Troop troop = new Troop();
                    troop.setId(troopObject.getInt("id") + "");
                    troop.setName(troopObject.getString("name"));
                    troop.setIsOnline(troopObject.getBoolean("online"));
                    returnValue.add(troop);
                }
                return returnValue;

            }catch(JSONException e){}
        }
        return null;
    }

    public ArrayList<Scout> getScoutsInTroop(Troop troop){
        RestClient restClient = new RestClient(config.baseUrl + troop.getId() + "/scouts?token=" + token);
        String response = restClient.get();
        JSONTokener tokener = new JSONTokener(response);
        Object root = tokener.nextValue();
        if(root instanceof JSONObject){
            try{
                ArrayList<Scout> returnValue = new ArrayList<Scout>();
                JSONArray data = (JSONArray)((JSONObject) root).get("data");
                for(int i = 0; i < data.length(); i++){
                    JSONObject scoutObject = (JSONObject)data.get(i);
                    Scout scout = new Scout();
                    scout.setId(scoutObject.getInt("id") + "");
                    scout.setName(scoutObject.getString("name"));
                    returnValue.add(scout);
                }
                return returnValue;

            }catch(JSONException e){}
        }
        return null;
    }

    public String runBitlashCommand(Troop troop, Scout scout, String command){
        RestClient restClient = new RestClient(config.baseUrl + troop.getId() + "/" + scout.getId() + "/command?token=" + token);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("command", command);
        restClient.setJsonData(jsonObject);
        String response = restClient.post();
        return response;
    }
}
