package com.templates.kotlintemplates.utils.from_auf_;//package com.templates.javatemplates.utils.from_auf_;
//
//import android.app.Activity;
//import android.content.ActivityNotFoundException;
//import android.content.Context;
//import android.content.Intent;
//import android.content.res.Resources;
//import android.content.res.TypedArray;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.Canvas;
//import android.graphics.Paint;
//import android.graphics.PorterDuff;
//import android.graphics.PorterDuffXfermode;
//import android.graphics.Rect;
//import android.graphics.RectF;
//import android.net.Uri;
//import android.support.annotation.DrawableRes;
//import android.util.DisplayMetrics;
//import android.util.Log;
//import android.widget.Toast;
//
//import com.aufshaus.R;
//import com.google.android.gms.common.ConnectionResult;
//import com.google.android.gms.common.GoogleApiAvailability;
//
//import java.util.Random;
//
//public class Utils {
//
//    public static void openBrowser(Context context, String link) {
//        try {
//            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
//            context.startActivity(intent);
//        } catch (ActivityNotFoundException e) {
//            Toast.makeText(context, "No application can handle this request. Please install a web browser", Toast.LENGTH_LONG).show();
//            Log.e(context.getClass().getSimpleName(), e.getMessage());
//        }
//    }
//
//    public static Bitmap drawableToBitmap(Context context, @DrawableRes int id, int size) {
//        return Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), id), size, size, true);
//    }
//
//    public static void openCustomChromeTab(Context context, Activity activity, String link) {
//        CustomTabs.with(context)
//                .setStyle(new CustomTabs.Style(context)
//                        .setShowTitle(true)
//                        .setStartAnimation(android.R.anim.fade_in, android.R.anim.fade_out)
//                        .setExitAnimation(android.R.anim.fade_in, android.R.anim.fade_out)
//                        .setToolbarColor(R.color.colorPrimary))
//                .openUrl(link, activity);
//    }
//
//    public static Bitmap getCircularBitmap(Bitmap srcBitmap, boolean border, boolean shadow) {
//        int squareBitmapWidth = Math.min(srcBitmap.getWidth(), srcBitmap.getHeight());
//        Bitmap dstBitmap = Bitmap.createBitmap(squareBitmapWidth, squareBitmapWidth, Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(dstBitmap);
//        Paint paint = new Paint();
//        paint.setAntiAlias(true);
//        Rect rect = new Rect(0, 0, squareBitmapWidth, squareBitmapWidth);
//        RectF rectF = new RectF(rect);
//        canvas.drawOval(rectF, paint);
//        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
//        float left = (squareBitmapWidth - srcBitmap.getWidth()) / 2;
//        float top = (squareBitmapWidth - srcBitmap.getHeight()) / 2;
//        canvas.drawBitmap(srcBitmap, left, top, paint);
//        if (border) {
//            dstBitmap = addBorderToCircularBitmap(dstBitmap, 5, 0xFFE15727);
//        }
//        if (shadow) {
//            dstBitmap = addShadowToCircularBitmap(dstBitmap, 4, 0x20000000);
//        }
//        return dstBitmap;
//    }
//
//    public static Bitmap addBorderToCircularBitmap(Bitmap srcBitmap, int borderWidth, int borderColor) {
//        int dstBitmapWidth = srcBitmap.getWidth() + borderWidth * 2;
//        Bitmap dstBitmap = Bitmap.createBitmap(dstBitmapWidth, dstBitmapWidth, Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(dstBitmap);
//        canvas.drawBitmap(srcBitmap, borderWidth, borderWidth, null);
//        Paint paint = new Paint();
//        paint.setColor(borderColor);
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(borderWidth);
//        paint.setAntiAlias(true);
//        canvas.drawCircle(canvas.getWidth() / 2, canvas.getWidth() / 2, canvas.getWidth() / 2 - borderWidth / 2, paint);
//        return dstBitmap;
//    }
//
//    public static Bitmap addShadowToCircularBitmap(Bitmap srcBitmap, int shadowWidth, int shadowColor) {
//        int dstBitmapWidth = srcBitmap.getWidth() + shadowWidth * 2;
//        Bitmap dstBitmap = Bitmap.createBitmap(dstBitmapWidth, dstBitmapWidth, Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(dstBitmap);
//        canvas.drawBitmap(srcBitmap, shadowWidth, shadowWidth, null);
//        Paint paint = new Paint();
//        paint.setColor(shadowColor);
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(shadowWidth);
//        paint.setAntiAlias(true);
//        canvas.drawCircle(dstBitmapWidth / 2, dstBitmapWidth / 2, dstBitmapWidth / 2 - shadowWidth / 2, paint);
//        return dstBitmap;
//    }
//
//    /**
//     * This method converts dp unit to equivalent pixels, depending on device density.
//     *
//     * @param dp      A value in dp (density independent pixels) unit. Which we need to convert into pixels
//     * @param context Context to get resources and device specific display metrics
//     * @return A float value to represent px equivalent to dp depending on device density
//     */
//    public static float convertDpToPixel(int dp, Context context) {
//        Resources resources = context.getResources();
//        DisplayMetrics metrics = resources.getDisplayMetrics();
//        return dp * (metrics.densityDpi / 160f);
//    }
//
//    public static int getToolbarHeight(Context context) {
//        final TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(
//                new int[]{R.attr.actionBarSize});
//        int toolbarHeight = (int) styledAttributes.getDimension(0, 0);
//        styledAttributes.recycle();
//        return toolbarHeight;
//    }
//
//
//    /**
//     * This method converts device specific pixels to density independent pixels.
//     *
//     * @param px      A value in px (pixels) unit. Which we need to convert into db
//     * @param context Context to get resources and device specific display metrics
//     * @return A float value to represent dp equivalent to px value
//     */
//    public static float convertPixelsToDp(float px, Context context) {
//        Resources resources = context.getResources();
//        DisplayMetrics metrics = resources.getDisplayMetrics();
//        return px / (metrics.densityDpi / 160f);
//    }
//
//    /**
//     * Check the device to make sure it has the Google Play Services APK. If
//     * it doesn't, display a dialog that allows users to download the APK from
//     * the Google Play Store or enable it in the device's system settings.
//     */
//    public static boolean checkPlayServices(Context context) {
//        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
//        int resultCode = apiAvailability.isGooglePlayServicesAvailable(context);
//        if (resultCode != ConnectionResult.SUCCESS) {
//            if (apiAvailability.isUserResolvableError(resultCode)) {
//                apiAvailability.showErrorNotification(context, resultCode);
//
//            } else {
//                Log.i("Play Services", "This device is not supported.");
//            }
//            return false;
//        }
//        return true;
//    }
//
//    public static int getRandom(int max) {
//        Random rand = new Random();
//        return rand.nextInt(max);
//    }
//
//}
