package com.templates.kotlintemplates.activities


import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.templates.kotlintemplates.activities.DebugActivity

abstract class BaseActivityNoToolbar: DebugActivity(){
    protected abstract var lay: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(lay)
    }

    protected val context: Context
        get() = this

    protected val activity: Activity
        get() = this

    protected fun toast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    protected fun toast(msg: Int) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}

