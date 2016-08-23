package com.templates.kotlintemplates.activities

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.View
import android.widget.Toast

import com.templates.kotlintemplates.utils.AlertUtils


abstract class BaseActivity : DebugActivity() {

    companion object {
        private val TAG = BaseActivity::class.java.simpleName
    }
    protected abstract val layoutResource: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResource)
    }

    protected val context: Context
        get() = this

    protected val activity: Activity
        get() = this

    override fun log(msg: String) {
        Log.d(TAG, msg)
    }

    protected fun toast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    protected fun toast(msg: Int) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    protected fun alert(msg: String) {
        AlertUtils.alert(this, msg)
    }

    protected fun alert(title: String, msg: String) {
        AlertUtils.alert(this, title, msg)
    }

    protected fun alert(msg: Int) {
        AlertUtils.alert(this, getString(msg))
    }

    protected fun alert(title: Int, msg: Int) {
        AlertUtils.alert(this, getString(title), getString(msg))
    }

    protected fun snack(view: View, msg: Int, runnable: Runnable) {
        this.snack(view, this.getString(msg), runnable)
    }

    protected fun snack(view: View, msg: Int) {
        this.snack(view, this.getString(msg), null as Runnable)
    }

    protected fun snack(view: View, msg: String) {
        this.snack(view, msg, null as Runnable)
    }

    protected fun snack(view: View, msg: String, runnable: Runnable?) {
        Snackbar.make(view, msg, 0).setAction("Ok") {
            runnable?.run()
        }.show()
    }

}
