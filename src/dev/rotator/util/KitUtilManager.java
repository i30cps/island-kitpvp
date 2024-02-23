package dev.rotator.util;


public class KitUtilManager {
    private static KitUtilManager kitUtilManager;

    public static KitUtilManager getKitUtilManager() {
        if (kitUtilManager != null) return kitUtilManager;
        return new KitUtilManager();
    }

    private KitUtilManager() {
        
    }


}
