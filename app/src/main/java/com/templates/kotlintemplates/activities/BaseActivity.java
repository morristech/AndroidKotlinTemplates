package com.templates.kotlintemplates.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.templates.javatemplates.utils.AlertUtils;


public abstract class BaseActivity extends DebugActivity {

    private static final String TAG = BaseActivity.class.getSimpleName();

    protected abstract int getLayoutResource();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
    }


    protected Context getContext() {
        return this;
    }

    protected Activity getActivity() {
        return this;
    }

    protected void log(String msg) {
        Log.d(TAG, msg);
    }

    protected void toast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    protected void toast(int msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    protected void alert(String msg) {
        AlertUtils.alert(this, msg);
    }

    protected void alert(String title, String msg) {
        AlertUtils.alert(this, title, msg);
    }

    protected void alert(int msg) {
        AlertUtils.alert(this, getString(msg));
    }

    protected void alert(int title, int msg) {
        AlertUtils.alert(this,getString(title), getString(msg));
    }

    protected void snack(View view, int msg, Runnable runnable) {
        this.snack(view, this.getString(msg), runnable);
    }

    protected void snack(View view, int msg) {
        this.snack(view, this.getString(msg), (Runnable) null);
    }

    protected void snack(View view, String msg) {
        this.snack(view, msg, (Runnable) null);
    }

    protected void snack(View view, String msg, final Runnable runnable) {
        Snackbar.make(view, msg, 0).setAction("Ok", new View.OnClickListener() {
            public void onClick(View v) {
                if (runnable != null) {
                    runnable.run();
                }

            }
        }).show();
    }

}
