package com.templates.kotlintemplates.utils

import android.app.Activity
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager.NameNotFoundException
import android.graphics.Bitmap
import android.graphics.Bitmap.Config
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.text.TextUtils
import android.util.Base64
import android.widget.Toast

import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Random
import java.util.regex.Matcher
import java.util.regex.Pattern


/**
 * Provides convenience methods and abstractions to some tasks in Android
 *
 *
 *
 *

 * @author Jay
 * *         **
 */
object Utils {

    private val TAG = Utils::class.java.simpleName

    @Deprecated("")
    private var mContext: Context? = null

    internal var mProgressDialog: ProgressDialog? = null

    /**
     * Shows the message passed in the parameter in the Toast.

     * @param msg      Message to be show in the toast.
     * *
     * @param duration Duration in milliseconds for which the toast should be shown
     * *
     * @return Toast object just shown
     * * **
     */
    @JvmOverloads fun showToast(ctx: Context, msg: CharSequence, duration: Int = Toast.LENGTH_LONG): Toast {
        val toast = Toast.makeText(ctx, msg, Toast.LENGTH_SHORT)
        toast.duration = duration
        toast.show()
        return toast
    }

    /**
     * Shows an alert dialog with OK button
     * *
     */
    @JvmOverloads fun showAlertDialog(ctx: Context, title: String, body: String, okListener: DialogInterface.OnClickListener? = null) {
        var okListener = okListener

        if (okListener == null) {
            okListener = DialogInterface.OnClickListener { dialog, which -> dialog.cancel() }
        }

        val builder = AlertDialog.Builder(ctx).setMessage(body).setPositiveButton("OK", okListener)

        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title)
        }

        builder.show()
    }

    /**
     * Serializes the Bitmap to Base64

     * @return Base64 string value of a [Bitmap] passed in as a parameter
     * *
     * @throws NullPointerException If the parameter bitmap is null.
     * *                              **
     */
    fun toBase64(bitmap: Bitmap?): String {

        if (bitmap == null) {
            throw NullPointerException("Bitmap cannot be null")
        }

        var base64Bitmap: String? = null
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        val imageBitmap = stream.toByteArray()
        base64Bitmap = Base64.encodeToString(imageBitmap, Base64.DEFAULT)

        return base64Bitmap
    }

    /**
     * Converts the passed in drawable to Bitmap representation

     * @throws NullPointerException If the parameter drawable is null.
     * *                              **
     */
    fun drawableToBitmap(drawable: Drawable?): Bitmap? {

        if (drawable == null) {
            throw NullPointerException("Drawable to convert should NOT be null")
        }

        if (drawable is BitmapDrawable) {
            return drawable.bitmap
        }

        if (drawable.intrinsicWidth <= 0 && drawable.intrinsicHeight <= 0) {
            return null
        }

        val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)

        return bitmap
    }


    /**
     * Shows a progress dialog with a spinning animation in it. This method must preferably called
     * from a UI thread.

     * @param ctx           Activity context
     * *
     * @param title         Title of the progress dialog
     * *
     * @param body          Body/Message to be shown in the progress dialog
     * *
     * @param isCancellable True if the dialog can be cancelled on back button press, false otherwise
     * *                      *
     */
    fun showProgressDialog(ctx: Context, title: String, body: String, isCancellable: Boolean) {
        showProgressDialog(ctx, title, body, null, isCancellable)
    }

    /**
     * Shows a progress dialog with a spinning animation in it. This method must preferably called
     * from a UI thread.

     * @param ctx           Activity context
     * *
     * @param title         Title of the progress dialog
     * *
     * @param body          Body/Message to be shown in the progress dialog
     * *
     * @param icon          Icon to show in the progress dialog. It can be null.
     * *
     * @param isCancellable True if the dialog can be cancelled on back button press, false otherwise
     * *                      *
     */
    fun showProgressDialog(ctx: Context, title: String, body: String, icon: Drawable?, isCancellable: Boolean) {

        if (ctx is Activity) {
            if (!ctx.isFinishing) {
                mProgressDialog = ProgressDialog.show(ctx, title, body, true)
                mProgressDialog!!.setIcon(icon)
                mProgressDialog!!.setCancelable(isCancellable)
            }
        }
    }

    /**
     * Check if the [ProgressDialog] is visible in the UI.
     * **
     */
    val isProgressDialogVisible: Boolean
        get() = mProgressDialog != null

    /**
     * Dismiss the progress dialog if it is visible.
     * *
     */
    fun dismissProgressDialog() {

        if (mProgressDialog != null) {
            mProgressDialog!!.dismiss()
        }

        mProgressDialog = null
    }

    /**
     * Creates a confirmation dialog with Yes-No Button. By default the buttons just dismiss the
     * dialog.

     * @param ctx
     * *
     * @param message     Message to be shown in the dialog.
     * *
     * @param yesListener Yes click handler
     * *
     * @param noListener
     * *
     * @param yesLabel    Label for yes button
     * *
     * @param noLabel     Label for no button
     * *                    **
     */
    @JvmOverloads fun showConfirmDialog(ctx: Context, message: String, yesListener: DialogInterface.OnClickListener?, noListener: DialogInterface.OnClickListener?, yesLabel: String = "Yes", noLabel: String = "No") {
        var yesListener = yesListener
        var noListener = noListener
        val builder = AlertDialog.Builder(ctx)
        if (yesListener == null) {
            yesListener = DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() }
        }

        if (noListener == null) {
            noListener = DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() }
        }

        builder.setMessage(message).setPositiveButton(yesLabel, yesListener).setNegativeButton(noLabel, noListener).show()
    }

    /**
     * Creates a confirmation dialog that show a pop-up with button labeled as parameters labels.

     * @param ctx                 [Activity] [Context]
     * *
     * @param message             Message to be shown in the dialog.
     * *
     * @param dialogClickListener For e.g.
     * *
     *
     *
     * *
     * *
     * @param positiveBtnLabel    For e.g. "Yes"
     * *
     * @param negativeBtnLabel    For e.g. "No"
     * *                                                       **
     */
    fun showDialog(ctx: Context, message: String, positiveBtnLabel: String, negativeBtnLabel: String, dialogClickListener: DialogInterface.OnClickListener?) {

        if (dialogClickListener == null) {
            throw NullPointerException("Action listener cannot be null")
        }
        val builder = AlertDialog.Builder(ctx)
        builder.setMessage(message).setPositiveButton(positiveBtnLabel, dialogClickListener).setNegativeButton(negativeBtnLabel, dialogClickListener).show()
    }

    /**
     * Gets the version name of the application. For e.g. 1.9.3
     * **
     */
    fun getApplicationVersionNumber(ctx: Context): String? {

        var versionName: String? = null

        try {
            versionName = ctx.packageManager.getPackageInfo(ctx.packageName, 0).versionName
        } catch (e: NameNotFoundException) {
            e.printStackTrace()
        }

        return versionName
    }

    /**
     * Gets the version code of the application. For e.g. Maverick Meerkat or 2013050301
     * **
     */
    fun getApplicationVersionCode(ctx: Context): Int {

        var versionCode = 0

        try {
            versionCode = ctx.packageManager.getPackageInfo(ctx.packageName, 0).versionCode
        } catch (e: NameNotFoundException) {
            e.printStackTrace()
        }

        return versionCode
    }

    val osVersion: String
        get() = Build.VERSION.RELEASE


    /**
     * Checks if the input parameter is a valid email.
     * **
     */
    fun isValidEmail(email: String): Boolean {
        val emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
        val matcher: Matcher?
        val pattern = Pattern.compile(emailPattern)

        matcher = pattern.matcher(email)

        if (matcher != null)
            return matcher.matches()
        else
            return false
    }

    /**
     * Capitalizes each word in the string.
     * **
     */
    fun capitalizeString(string: String?): String {

        if (string == null) {
            throw NullPointerException("String to capitalize cannot be null")
        }

        val chars = string.toLowerCase().toCharArray()
        var found = false
        for (i in chars.indices) {
            if (!found && Character.isLetter(chars[i])) {
                chars[i] = Character.toUpperCase(chars[i])
                found = true
            } else if (Character.isWhitespace(chars[i]) || chars[i] == '.' || chars[i] == '\'') { // You
                // can
                // add
                // other
                // chars
                // here
                found = false
            }
        } // end for

        return String(chars)
    }

    /**
     * Transforms Calendar to ISO 8601 string.
     * **
     */
    fun fromCalendar(calendar: Calendar): String {
        // TODO: move this method to DateUtils
        val date = calendar.time
        val formatted = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(date)
        return formatted.substring(0, 22) + ":" + formatted.substring(22)
    }


    /**
     * Gets random color integer
     * **
     */
    val randomColor: Int
        get() {
            val random = Random()
            val red = random.nextInt(255)
            val green = random.nextInt(255)
            val blue = random.nextInt(255)
            return Color.argb(255, red, green, blue)
        }


}
/**
 * Shows a long time duration toast message.

 * @param msg Message to be show in the toast.
 * *
 * @return Toast object just shown
 * * **
 */
/**
 * Shows an alert dialog with the OK button. When the user presses OK button, the dialog
 * dismisses.
 * **
 */
/**
 * Creates a confirmation dialog with Yes-No Button. By default the buttons just dismiss the
 * dialog.

 * @param ctx
 * *
 * @param message     Message to be shown in the dialog.
 * *
 * @param yesListener Yes click handler
 * *
 * @param noListener  **
 */
