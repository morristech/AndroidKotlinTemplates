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

package com.templates.kotlintemplates.utils

import android.net.Uri

object QRCodeUtils {

    // http://open.visualead.com/ possible alternative if google removes service

    const val DEFAULT_SIZE = 540
    const val DEFAULT_MARGIN = 1
    const private val ROOT_URL = "https://chart.googleapis.com/chart?"
    const private val DEFAULT_ENCODING_LEVEL = "L"

    @JvmOverloads fun getQRCode(dataToEncode: String,
                                size: Int = DEFAULT_SIZE,
                                margin: Int = DEFAULT_MARGIN,
                                encodingLevel: String = DEFAULT_ENCODING_LEVEL): Uri {
        return Uri.parse(ROOT_URL)
                .buildUpon()
                .appendQueryParameter("chs", "${size}x$size")
                .appendQueryParameter("cht", "qr")
                .appendQueryParameter("chl", dataToEncode)
                .appendQueryParameter("choe", "UTF-8")
                .appendQueryParameter("chld", encodingLevel + "|" + margin)
                .build()
    }

}
