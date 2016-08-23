package com.templates.kotlintemplates.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Provides convenience methods and abstractions to some tasks in Android
 * <p>
 * <br/>
 * <br/>
 *
 * @author Jay
 *         **
 */
public class Utils {

    private static final String TAG = Utils.class.getSimpleName();

    @Deprecated
    protected static Context mContext;

    static ProgressDialog mProgressDialog;

    /**
     * Shows a long time duration toast message.
     *
     * @param msg Message to be show in the toast.
     * @return Toast object just shown
     * **
     */
    public static Toast showToast(Context ctx, CharSequence msg) {
        return showToast(ctx, msg, Toast.LENGTH_LONG);
    }

    /**
     * Shows the message passed in the parameter in the Toast.
     *
     * @param msg      Message to be show in the toast.
     * @param duration Duration in milliseconds for which the toast should be shown
     * @return Toast object just shown
     * **
     */
    public static Toast showToast(Context ctx, CharSequence msg, int duration) {
        Toast toast = Toast.makeText(ctx, msg, Toast.LENGTH_SHORT);
        toast.setDuration(duration);
        toast.show();
        return toast;
    }


    /**
     * Shows an alert dialog with the OK button. When the user presses OK button, the dialog
     * dismisses.
     * **
     */
    public static void showAlertDialog(Context ctx, String title, String body) {
        showAlertDialog(ctx, title, body, null);
    }

    /**
     * Shows an alert dialog with OK button
     * *
     */
    public static void showAlertDialog(Context ctx, String title, String body, DialogInterface.OnClickListener okListener) {

        if (okListener == null) {
            okListener = new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            };
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(ctx).setMessage(body).setPositiveButton("OK", okListener);

        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title);
        }

        builder.show();
    }

    /**
     * Serializes the Bitmap to Base64
     *
     * @return Base64 string value of a {@linkplain Bitmap} passed in as a parameter
     * @throws NullPointerException If the parameter bitmap is null.
     *                              **
     */
    public static String toBase64(Bitmap bitmap) {

        if (bitmap == null) {
            throw new NullPointerException("Bitmap cannot be null");
        }

        String base64Bitmap = null;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] imageBitmap = stream.toByteArray();
        base64Bitmap = Base64.encodeToString(imageBitmap, Base64.DEFAULT);

        return base64Bitmap;
    }

    /**
     * Converts the passed in drawable to Bitmap representation
     *
     * @throws NullPointerException If the parameter drawable is null.
     *                              **
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {

        if (drawable == null) {
            throw new NullPointerException("Drawable to convert should NOT be null");
        }

        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        if (drawable.getIntrinsicWidth() <= 0 && drawable.getIntrinsicHeight() <= 0) {
            return null;
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }


    /**
     * Shows a progress dialog with a spinning animation in it. This method must preferably called
     * from a UI thread.
     *
     * @param ctx           Activity context
     * @param title         Title of the progress dialog
     * @param body          Body/Message to be shown in the progress dialog
     * @param isCancellable True if the dialog can be cancelled on back button press, false otherwise
     *                      *
     */
    public static void showProgressDialog(Context ctx, String title, String body, boolean isCancellable) {
        showProgressDialog(ctx, title, body, null, isCancellable);
    }

    /**
     * Shows a progress dialog with a spinning animation in it. This method must preferably called
     * from a UI thread.
     *
     * @param ctx           Activity context
     * @param title         Title of the progress dialog
     * @param body          Body/Message to be shown in the progress dialog
     * @param icon          Icon to show in the progress dialog. It can be null.
     * @param isCancellable True if the dialog can be cancelled on back button press, false otherwise
     *                      *
     */
    public static void showProgressDialog(Context ctx, String title, String body, Drawable icon, boolean isCancellable) {

        if (ctx instanceof Activity) {
            if (!((Activity) ctx).isFinishing()) {
                mProgressDialog = ProgressDialog.show(ctx, title, body, true);
                mProgressDialog.setIcon(icon);
                mProgressDialog.setCancelable(isCancellable);
            }
        }
    }

    /**
     * Check if the {@link ProgressDialog} is visible in the UI.
     * **
     */
    public static boolean isProgressDialogVisible() {
        return (mProgressDialog != null);
    }

    /**
     * Dismiss the progress dialog if it is visible.
     * *
     */
    public static void dismissProgressDialog() {

        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }

        mProgressDialog = null;
    }


    /**
     * Creates a confirmation dialog with Yes-No Button. By default the buttons just dismiss the
     * dialog.
     *
     * @param ctx
     * @param message     Message to be shown in the dialog.
     * @param yesListener Yes click handler
     * @param noListener  **
     */
    public static void showConfirmDialog(Context ctx, String message, DialogInterface.OnClickListener yesListener, DialogInterface.OnClickListener noListener) {
        showConfirmDialog(ctx, message, yesListener, noListener, "Yes", "No");
    }

    /**
     * Creates a confirmation dialog with Yes-No Button. By default the buttons just dismiss the
     * dialog.
     *
     * @param ctx
     * @param message     Message to be shown in the dialog.
     * @param yesListener Yes click handler
     * @param noListener
     * @param yesLabel    Label for yes button
     * @param noLabel     Label for no button
     *                    **
     */
    public static void showConfirmDialog(Context ctx, String message, DialogInterface.OnClickListener yesListener, DialogInterface.OnClickListener noListener, String yesLabel, String noLabel) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        if (yesListener == null) {
            yesListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            };
        }

        if (noListener == null) {
            noListener = new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            };
        }

        builder.setMessage(message).setPositiveButton(yesLabel, yesListener).setNegativeButton(noLabel, noListener).show();
    }

    /**
     * Creates a confirmation dialog that show a pop-up with button labeled as parameters labels.
     *
     * @param ctx                 {@link Activity} {@link Context}
     * @param message             Message to be shown in the dialog.
     * @param dialogClickListener For e.g.
     *                            <p>
     *                            <pre>
     *                            @param positiveBtnLabel    For e.g. "Yes"
     *                            @param negativeBtnLabel    For e.g. "No"
     *                                                       **
     */
    public static void showDialog(Context ctx, String message, String positiveBtnLabel, String negativeBtnLabel, DialogInterface.OnClickListener dialogClickListener) {

        if (dialogClickListener == null) {
            throw new NullPointerException("Action listener cannot be null");
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setMessage(message).setPositiveButton(positiveBtnLabel, dialogClickListener).setNegativeButton(negativeBtnLabel, dialogClickListener).show();
    }

    /**
     * Gets the version name of the application. For e.g. 1.9.3
     * **
     */
    public static String getApplicationVersionNumber(Context ctx) {

        String versionName = null;

        try {
            versionName = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }

        return versionName;
    }

    /**
     * Gets the version code of the application. For e.g. Maverick Meerkat or 2013050301
     * **
     */
    public static int getApplicationVersionCode(Context ctx) {

        int versionCode = 0;

        try {
            versionCode = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }

        return versionCode;
    }

    public static String getOSVersion() {
        return Build.VERSION.RELEASE;
    }


    /**
     * Checks if the input parameter is a valid email.
     * **
     */
    public static boolean isValidEmail(String email) {
        final String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Matcher matcher;
        Pattern pattern = Pattern.compile(emailPattern);

        matcher = pattern.matcher(email);

        if (matcher != null)
            return matcher.matches();
        else
            return false;
    }

    /**
     * Capitalizes each word in the string.
     * **
     */
    public static String capitalizeString(String string) {

        if (string == null) {
            throw new NullPointerException("String to capitalize cannot be null");
        }

        char[] chars = string.toLowerCase().toCharArray();
        boolean found = false;
        for (int i = 0; i < chars.length; i++) {
            if (!found && Character.isLetter(chars[i])) {
                chars[i] = Character.toUpperCase(chars[i]);
                found = true;
            } else if (Character.isWhitespace(chars[i]) || chars[i] == '.' || chars[i] == '\'') { // You
                // can
                // add
                // other
                // chars
                // here
                found = false;
            }
        } // end for

        return String.valueOf(chars);
    }

    /**
     * Transforms Calendar to ISO 8601 string.
     * **
     */
    public static String fromCalendar(final Calendar calendar) {
        // TODO: move this method to DateUtils
        Date date = calendar.getTime();
        String formatted = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(date);
        return formatted.substring(0, 22) + ":" + formatted.substring(22);
    }


    /**
     * Gets random color integer
     * **
     */
    public static int getRandomColor() {
        Random random = new Random();
        int red = random.nextInt(255);
        int green = random.nextInt(255);
        int blue = random.nextInt(255);
        return Color.argb(255, red, green, blue);
    }


}
