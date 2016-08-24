package code.qr.welcomescreen.activities

import android.app.Activity
import android.content.Context
import android.os.Bundle

import android.util.Log
import android.view.View
import android.widget.Toast
import com.templates.kotlintemplates.activities.DebugActivity


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


}
