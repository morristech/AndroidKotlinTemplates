package com.templates.kotlintemplates.utils

import android.os.Environment

object StorageUtils {
    /**
     * Checks if the SD Card is mounted on the device.

     */
    val isSDCARDMounted: Boolean
        @Deprecated("use {@link #isSdCardMounted()}\n      **")
        get() {
            val status = Environment.getExternalStorageState()
            if (status == Environment.MEDIA_MOUNTED)
                return true
            return false
        }

    val isSdCardMounted: Boolean
        get() {
            val status = Environment.getExternalStorageState()
            if (status == Environment.MEDIA_MOUNTED)
                return true
            return false
        }

}
