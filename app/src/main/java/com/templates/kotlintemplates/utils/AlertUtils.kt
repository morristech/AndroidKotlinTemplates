package com.templates.kotlintemplates.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface

object AlertUtils {

    fun alert(activity: Activity, message: String) {
        alert(activity, null, message, 0, 0)
    }

    @JvmOverloads fun alert(activity: Activity, title: String?, message: String, okButton: Int = 0, icon: Int = 0) {
        val builder = AlertDialog.Builder(activity)
        if (icon > 0) {
            builder.setIcon(icon)
        }
        if (title != null) {
            builder.setTitle(title)
        }
        builder.setMessage(message)

        val okString = if (okButton > 0) activity.getString(okButton) else "OK"

        val dialog = builder.create()
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, okString, DialogInterface.OnClickListener { dialog, which -> return@OnClickListener })
        dialog.show()
    }

    fun alert(context: Context, title: Int, message: Int, okButton: Int, runnable: Runnable?) {
        val builder = android.support.v7.app.AlertDialog.Builder(context)
        builder.setTitle(title).setMessage(message)
        val okString = if (okButton > 0) context.getString(okButton) else "OK"
        // Add the buttons
        builder.setPositiveButton(okString) { dialog, id ->
            runnable?.run()
        }
        val dialog = builder.create()
        dialog.show()
    }
}
