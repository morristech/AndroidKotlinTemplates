package com.templates.kotlintemplates.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Ricardo Lecheta on 22/09/2014.
 */
public abstract class DebugFragment extends Fragment{
    protected static final String TAG = "livroandroid";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (isLogLifecycle()) {
            log(getClassName() + ".onCreate(): " + savedInstanceState);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
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

    public String getClassName() {
        // Retorna o nome da classe sem o pacote
        Class cls = ((Object) this).getClass();
        String s = cls.getSimpleName();
        return s;
    }

    protected void log(String msg) {
        if (isLogOn()) {
            Log.d(TAG, msg);
        }
    }

    protected boolean isLogOn() {
        return true;
    }

    protected boolean isLogLifecycle() {
        return false;
    }
}
