pinocciosdk-java
================
A SDK to make it easy to communicate to the pinoccio API (https://docs.pinocc.io/api.html) using Java. This is a work in progress, so stay tuned.

### To use on a Processing sketch
1. Grab a **jar** release from: https://github.com/urtubia/pinocciosdk-java/releases
2. Drop this jar into your sketch's Processing window (this will place the jar under a `code` subdirectory of your sketch. 
3. Write your sketch! You can use the following as a starting point. It will blink all your scouts on all your troops blue for a second.

    
        PinoccioSDK sdk;

        void setup()
        {
            sdk = PinoccioSDK.login("YOUR_HQ_EMAIL","YOUR_HQ_PASSWORD");
        }

        void draw()
        {
            background(255,255,255);
        }

        void mousePressed()
        {
            for(Troop troop : sdk.getTroops()){
                for(Scout scout : sdk.getScoutsInTroop(troop)){
                    sdk.runBitlashCommand(troop, scout, "led.blink(0,0,255,1000)");
                }
            }
        }
        
### To use on a Java application
1.- Grab a release jar and place it on your classpath.

### To use on Android
TBD

## Documentation
http://htmlpreview.github.io/?https://github.com/urtubia/pinocciosdk-java/blob/master/docs/index.html
