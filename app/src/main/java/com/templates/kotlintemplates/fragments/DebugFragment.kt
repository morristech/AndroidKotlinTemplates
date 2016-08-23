package com.templates.kotlintemplates.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Ricardo Lecheta on 22/09/2014.
 */
abstract class DebugFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (isLogLifecycle) {
            log(className + ".onCreate(): " + savedInstanceState)
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    //    protected void onStart() {
    //        super.onStart();
    //        if (isLogLifecycle()) {
    //            log(getClassName() + ".onStart().");
    //        }
    //    }
    //
    //    protected void onRestart() {
    //        super.onRestart();
    //        if (isLogLifecycle()) {
    //            log(getClassName() + ".onRestart().");
    //        }
    //    }
    //
    //    protected void onResume() {
    //        super.onResume();
    //        if (isLogLifecycle()) {
    //            log(getClassName() + ".onResume().");
    //        }
    //    }
    //
    //    @Override
    //    protected void onSaveInstanceState(Bundle outState) {
    //        super.onSaveInstanceState(outState);
    //        if (isLogLifecycle()) {
    //            log(getClassName() + ".onSaveInstanceState().");
    //        }
    //    }
    //
    //    protected void onPause() {
    //        super.onPause();
    //        if (isLogLifecycle()) {
    //            log(getClassName() + ".onPause().");
    //        }
    //    }
    //
    //    protected void onStop() {
    //        super.onStop();
    //        if (isLogLifecycle()) {
    //            log(getClassName() + ".onStop().");
    //        }
    //    }
    //
    //    protected void onDestroy() {
    //        super.onDestroy();
    //        if (isLogLifecycle()) {
    //            log(getClassName() + ".onDestroy().");
    //        }
    //    }

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
