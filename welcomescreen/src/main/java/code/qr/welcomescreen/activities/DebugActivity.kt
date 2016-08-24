package com.templates.kotlintemplates.activities

import android.os.Bundle
import android.util.Log

/**
 * Created by Ricardo Lecheta on 22/09/2014.
 */
abstract class DebugActivity : android.support.v7.app.AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (isLogLifecycle) {
            log(className + ".onCreate(): " + savedInstanceState)
        }
    }

    override fun onStart() {
        super.onStart()
        if (isLogLifecycle) {
            log(className + ".onStart().")
        }
    }

    override fun onRestart() {
        super.onRestart()
        if (isLogLifecycle) {
            log(className + ".onRestart().")
        }
    }

    override fun onResume() {
        super.onResume()
        if (isLogLifecycle) {
            log(className + ".onResume().")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (isLogLifecycle) {
            log(className + ".onSaveInstanceState().")
        }
    }

    override fun onPause() {
        super.onPause()
        if (isLogLifecycle) {
            log(className + ".onPause().")
        }
    }

    override fun onStop() {
        super.onStop()
        if (isLogLifecycle) {
            log(className + ".onStop().")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isLogLifecycle) {
            log(className + ".onDestroy().")
        }
    }

    // Retorna o nome da classe sem o pacote
    val className: String
        get() {
            val cls = (this as Any).javaClass
            val s = cls.simpleName
            return s
        }

    protected open fun log(msg: String) {
        if (isLogOn) {
            Log.d(TAG, msg)
        }
    }

    protected val isLogOn: Boolean
        get() = true

    protected val isLogLifecycle: Boolean
        get() = false

    companion object {
        protected val TAG = "livroandroid"
    }
}
