package com.templates.kotlintemplates.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.content.res.Resources
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager

import com.templates.javatemplates.R


class AndroidUtils {

    /**
     * Para fazer android:foreground="?android:attr/selectableItemBackground"

     * @param context getActionBar().getThemedContext()
     * *
     * @param attrId  android.R.attr.selectableItemBackground
     */
    fun getDrawableAttr(context: Context, attrId: Int): Drawable {
        val attrs = intArrayOf(attrId)
        val ta = context.obtainStyledAttributes(attrs)
        val drawable = ta.getDrawable(0 /* index */)
        ta.recycle()
        return drawable
    }

    companion object {
        protected val TAG = "livroandroid"

        /**
         *
         */
        fun isNetworkAvailable(context: Context): Boolean {
            try {
                val connectivity = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                if (connectivity == null) {
                    return false
                } else {
                    val info = connectivity.allNetworkInfo
                    if (info != null) {
                        for (i in info.indices) {
                            if (info[i].state == NetworkInfo.State.CONNECTED) {
                                return true
                            }
                        }
                    }
                }
            } catch (e: SecurityException) {
                alertDialog(context, e.javaClass.simpleName, e.message)
            }

            return false
        }

        fun alertDialog(context: Context, title: Int, mensagem: Int) {
            try {
                val dialog = AlertDialog.Builder(context).setTitle(title).setMessage(mensagem).create()
                dialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK") { dialog, which -> }
                dialog.show()
            } catch (e: Exception) {
                Log.e(TAG, e.message, e)
            }

        }

        fun alertDialog(context: Context, title: String, mensagem: String) {
            try {
                val dialog = AlertDialog.Builder(context).setTitle(
                        title).setMessage(mensagem).create()
                dialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK") { dialog, which -> }
                dialog.show()
            } catch (e: Exception) {
                Log.e(TAG, e.message, e)
            }

        }

        // Fecha o teclado virtual se aberto (view com foque)
        fun closeVirtualKeyboard(context: Context, view: View): Boolean {
            // Fecha o teclado virtual
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            if (imm != null) {
                val b = imm.hideSoftInputFromWindow(view.windowToken, 0)
                return b
            }
            return false
        }

        /**
         * Converte de DIP para Pixels
         */
        fun toPixels(context: Context, dip: Float): Float {
            val r = context.resources
            //int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, r.getDisplayMetrics());

            val scale = r.displayMetrics.density
            val px = (dip * scale + 0.5f).toInt()

            return px.toFloat()
        }

        /**
         * Converte de Pixels para DIP
         */
        fun toDip(context: Context, pixels: Float): Float {
            val r = context.resources

            val scale = r.displayMetrics.density

            val dip = (pixels / scale + 0.5f).toInt()
            return dip.toFloat()
        }

        val isAndroid3Honeycomb: Boolean
            get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB

        val isAndroid4ICS: Boolean
            get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH

        val isAndroid5Lollipop: Boolean
            get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP


        // Retorna se a tela é large ou xlarge
        fun isTablet(context: Context): Boolean {
            return context.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK >= Configuration.SCREENLAYOUT_SIZE_LARGE
        }

        // Retona se é um tablet com Android 3.x
        fun isAndroid_3_Tablet(context: Context): Boolean {
            return isAndroid3Honeycomb && isTablet(context)
        }

        private val RES_IDS_ACTION_BAR_SIZE = intArrayOf(R.attr.actionBarSize)

        /**
         * Calculates the Action Bar height in pixels.
         */
        fun getActionBarSize(context: Context?): Int {
            if (context == null) {
                return 0
            }

            val curTheme = context.theme ?: return 0

            val att = curTheme.obtainStyledAttributes(RES_IDS_ACTION_BAR_SIZE) ?: return 0

            val size = att.getDimension(0, 0f)
            att.recycle()
            return size.toInt()
        }

        fun getMaterialThemePrimaryColor(context: Context): Int {
            val typedValue = TypedValue()

            val a = context.obtainStyledAttributes(typedValue.data, intArrayOf(R.attr.colorPrimary))
            val color = a.getColor(0, 0)

            a.recycle()

            return color
        }

        fun getMaterialThemeAccentColor(context: Context): Int {
            val typedValue = TypedValue()

            val a = context.obtainStyledAttributes(typedValue.data, intArrayOf(R.attr.colorAccent))
            val color = a.getColor(0, 0)

            a.recycle()

            return color
        }

        /* Lê a versão do app */
        fun getVersionName(activity: Activity): String {
            val pm = activity.packageManager
            val packageName = activity.packageName
            val versionName: String
            try {
                val info = pm.getPackageInfo(packageName, 0)
                versionName = info.versionName
            } catch (e: PackageManager.NameNotFoundException) {
                versionName = "N/A"
            }

            return versionName
        }
    }

}
