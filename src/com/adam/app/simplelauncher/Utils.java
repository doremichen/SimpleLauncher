/**
 * ===========================================================================
 * Copyright Adam Sample code
 * All Rights Reserved
 * ===========================================================================
 * 
 * File Name: Utils.java
 * Brief: This file provide the tools 
 * 
 * Author: AdamChen
 * Create Date: 2018/1/17
 */

package com.adam.app.simplelauncher;

import android.util.Log;

/**
 * <h1>Utils</h1>
 * 
 * @autor AdamChen
 * @since 2018/1/17
 */
public final class Utils {

    // Control log
    private static final boolean ISLOG = true;

    // Log TAG
    private static final String TAG = "LauncherDemo";
    
    /**
     * 
     * <h1>print</h1>
     *
     * @param obj This is object
     * @param str This is string
     * @return Nothing
     *
     */
    public static void print(Object obj, String str) {
        if (ISLOG) {
            Log.i(TAG, obj.getClass().getSimpleName() + " " + str);
        }
    }

}
