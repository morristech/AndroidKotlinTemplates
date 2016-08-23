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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Utility class for performing various formatting operations on money.
 *
 * @author vgrec, created on 6/15/15.
 */
public class MoneyUtils {

    /**
     * Given an amount of cents, converts it to a decimal number and returns the String representation of it.
     * The default representation is 2 digits after decimal point.
     *
     * @param amount amount of money in cents
     * @return String representation of the decimal number
     * <p/>
     * eg.: centsToString(100) => "1.00"
     */
    public static String centsToString(long amount) {
        return doubleToString(fromCents(amount), 2);
    }

    /**
     * Given an amount of cents, converts it to a decimal number and returns the String representation of it.
     *
     * @param amount amount of money in cents
     * @param digits number of digits after decimal point
     * @return String representation of the decimal number
     * <p/>
     * eg.: centsToString(100, 3) => "1.000"
     */
    public static String centsToString(long amount, int digits) {
        return doubleToString(fromCents(amount), digits);
    }

    private static double fromCents(long amount) {
        return amount / 100.0;
    }

    private static String doubleToString(double money, int digits) {
        return doubleToString(money, digits, null);
    }

    private static String doubleToString(double money, int digits, RoundingMode mode) {
        NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
        if (format instanceof DecimalFormat) {
            ((DecimalFormat) format).setDecimalSeparatorAlwaysShown(true);
        }
        format.setMinimumFractionDigits(digits);
        format.setMaximumFractionDigits(digits);
        if (mode != null) {
            format.setRoundingMode(mode);
        }
        return format.format(money);
    }

    public static int stringToCents(String price) {
        return new BigDecimal(price).multiply(new BigDecimal("100.00")).intValue();
    }
}
