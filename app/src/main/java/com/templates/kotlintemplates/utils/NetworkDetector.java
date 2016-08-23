/*
 *  Copyright (c) 2016-present, Muume, Inc. All rights reserved.
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

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import static android.content.Context.CONNECTIVITY_SERVICE;
import static android.net.ConnectivityManager.TYPE_MOBILE;
import static android.net.ConnectivityManager.TYPE_WIFI;

public class NetworkDetector {

    private final NetworkInfo networkInfo;

    public NetworkDetector(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        networkInfo = connectivityManager.getActiveNetworkInfo();
    }

    public boolean isOnline() {
        return isWifi() || isMobile();
    }

    private boolean isOnlineNetwork(int type) {
        boolean connected = false;
        if (networkInfo != null) {
            if (networkInfo.getType() == type) {
                connected = networkInfo.isAvailable() && networkInfo.isConnected();
            }
        }
        return connected;
    }

    public boolean isMobile() {
        return isOnlineNetwork(TYPE_MOBILE);
    }

    public boolean isWifi() {
        return isOnlineNetwork(TYPE_WIFI);
    }
}
