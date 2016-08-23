package com.templates.kotlintemplates.utils;

import android.os.Environment;

public class StorageUtils {
    /**
     * Checks if the SD Card is mounted on the device.
     *
     * @deprecated use {@link #isSdCardMounted()}
     * **
     */
    public static boolean isSDCARDMounted() {
        String status = Environment.getExternalStorageState();
        if (status.equals(Environment.MEDIA_MOUNTED))
            return true;
        return false;
    }

    public static boolean isSdCardMounted() {
        String status = Environment.getExternalStorageState();
        if (status.equals(Environment.MEDIA_MOUNTED))
            return true;
        return false;
    }

}
