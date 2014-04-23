package com.bigrobotstudios.pinoccio;

import junit.framework.TestCase;
import org.junit.Test;
import java.util.ArrayList;

/**
 * Created by hectorurtubia on 4/21/14.
 */
public class PinoccioSDKTest extends TestCase {
    static String USERNAME = "YOUR_HQ_USERNAME";
    static String PASSWORD = "YOUR_HQ_PASSWORD";

    @Test
    public void testLogin() throws Exception {
        PinoccioSDK sdk = PinoccioSDK.login(USERNAME, PASSWORD);
        TestCase.assertNotNull(sdk);
        sdk = PinoccioSDK.login("asdf","asdf");
        TestCase.assertNull(sdk);
    }

    @Test
    public void testBlinkAllScouts() throws Exception {
        PinoccioSDK sdk = PinoccioSDK.login(USERNAME, PASSWORD);
        ArrayList<Troop> troops = sdk.getTroops();
        for(Troop troop : troops){
            System.out.println("Troop: " + troop.getName());
            ArrayList<Scout> scouts = sdk.getScoutsInTroop(troop);
            if(scouts != null) {
                for (Scout scout : scouts) {
                    System.out.println(scout.getName());
                    System.out.println(sdk.runBitlashCommand(troop, scout, "led.blink(0,0,255,1000)"));
                }
            }
        }
    }
}
