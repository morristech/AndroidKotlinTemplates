/*
 * Copyright (c) 2016-present, Muume, Inc. All rights reserved.
 * <p/>
 * You are hereby granted a non-exclusive, worldwide, royalty-free license to use,
 * copy, modify, and distribute this software in source code or binary form for use
 * in connection with the web services and APIs provided by Muume.
 * <p/>
 * As with any software that integrates with the Muume platform, your use of
 * this software is subject to the Muume Developer Principles and Policies
 * [http://developers.muume.com/policy/]. This copyright notice shall be
 * included in all copies or substantial portions of the software.
 * <p/>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NON INFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.templates.kotlintemplates.utils;

import android.net.Uri;

public class QRCodeUtils {

    // http://open.visualead.com/ possible alternative if google removes service

    public static final int DEFAULT_SIZE = 540;
    public static final int DEFAULT_MARGIN = 1;
    private final static String ROOT_URL = "https://chart.googleapis.com/chart?";
    private static final String DEFAULT_ENCODING_LEVEL = "L";

    private QRCodeUtils() {
    }

    /**
     * Method returns QR CODE IMAGE URL, with given data to encode,size of 540, encoding option:UTF-8
     * error_correction_level = L - Allows recovery of up to 7% data loss
     * and 1 margin
     *
     * @param dataToEncode
     * @return
     */
    public static Uri getQRCode(String dataToEncode) {
        return getQRCode(dataToEncode, DEFAULT_SIZE, DEFAULT_MARGIN, DEFAULT_ENCODING_LEVEL);
    }

    /**
     * Method returns QR CODE IMAGE URL, with given data to encode, encoding option:UTF-8
     * error_correction_level = L - Allows recovery of up to 7% data loss
     * and 1 margin
     * max image size = 540
     *
     * @param dataToEncode
     * @return
     */
    public static Uri getQRCode(String dataToEncode, int size) {
        return getQRCode(dataToEncode, size, DEFAULT_MARGIN, DEFAULT_ENCODING_LEVEL);
    }

    /**
     * Method returns QR CODE IMAGE URL, with given data to encode, encoding option:UTF-8
     * error_correction_level = L - Allows recovery of up to 7% data loss
     * max image size = 540
     *
     * @param dataToEncode
     * @return
     */
    public static Uri getQRCode(String dataToEncode, int size, int margin, String encodingLevel) {
        return Uri.parse(ROOT_URL).buildUpon()
                .appendQueryParameter("chs", size + "x" + size)
                .appendQueryParameter("cht", "qr")
                .appendQueryParameter("chl", dataToEncode)
                .appendQueryParameter("choe", "UTF-8")
                .appendQueryParameter("chld", encodingLevel + "|" + margin)
                .build();
    }

}
