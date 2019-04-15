package com.dutch.hdh.dutchpayapp.data.util;

import android.util.Log;

import com.dutch.hdh.dutchpayapp.Constants;

public class LogUtils {
    /**
     * debug log(with global tag)
     * @param message
     */
    public static void d(String message) {
        if(Constants.LOG_PRINT) {
            String methodName = new Throwable().getStackTrace()[1].getMethodName();
            int lineNumber = new Throwable().getStackTrace()[1].getLineNumber();

            Log.d(Constants.LOG_TAG, "methodName : ["+methodName+"], lineNumber : ["+lineNumber+"], message : ["+ message+"]");
        }
    }

    /**
     * info log(with global tag)
     * @param message
     */
    public static void i(String message) {
        if(Constants.LOG_PRINT) {
            String methodName = new Throwable().getStackTrace()[1].getMethodName();
            int lineNumber = new Throwable().getStackTrace()[1].getLineNumber();

            Log.i(Constants.LOG_TAG, "methodName : ["+methodName+"], lineNumber : ["+lineNumber+"], message : ["+ message+"]");
        }
    }

    /**
     * warn log(with global tag)
     * @param message
     */
    public static void w(String message) {
        if(Constants.LOG_PRINT) {
            String methodName = new Throwable().getStackTrace()[1].getMethodName();
            int lineNumber = new Throwable().getStackTrace()[1].getLineNumber();

            Log.w(Constants.LOG_TAG, "methodName : ["+methodName+"], lineNumber : ["+lineNumber+"], message : ["+ message+"]");
        }
    }

    /**
     * error log(with global tag)
     * @param message
     */
    public static void e(String message) {
        if(Constants.LOG_PRINT) {
            String methodName = new Throwable().getStackTrace()[1].getMethodName();
            int lineNumber = new Throwable().getStackTrace()[1].getLineNumber();

            Log.e(Constants.LOG_TAG, "methodName : ["+methodName+"], lineNumber : ["+lineNumber+"], message : ["+ message+"]");
        }
    }

    /**
     * debug log
     * @param tag
     * @param message
     */
    public static void d(String tag, String message) {
        if(Constants.LOG_PRINT) {
            String methodName = new Throwable().getStackTrace()[1].getMethodName();
            int lineNumber = new Throwable().getStackTrace()[1].getLineNumber();

            Log.d(tag, "methodName : ["+methodName+"], lineNumber : ["+lineNumber+"], message : ["+ message+"]");
        }
    }

    /**
     * info log
     * @param tag
     * @param message
     */
    public static void i(String tag, String message) {
        if(Constants.LOG_PRINT) {
            String methodName = new Throwable().getStackTrace()[1].getMethodName();
            int lineNumber = new Throwable().getStackTrace()[1].getLineNumber();

            Log.i(tag, "methodName : ["+methodName+"], lineNumber : ["+lineNumber+"], message : ["+ message+"]");
        }
    }

    /**
     * warn log
     * @param tag
     * @param message
     */
    public static void w(String tag, String message) {
        if(Constants.LOG_PRINT) {
            String methodName = new Throwable().getStackTrace()[1].getMethodName();
            int lineNumber = new Throwable().getStackTrace()[1].getLineNumber();

            Log.w(tag, "methodName : ["+methodName+"], lineNumber : ["+lineNumber+"], message : ["+ message+"]");
        }
    }

    /**
     * error log
     * @param tag
     * @param message
     */
    public static void e(String tag, String message) {
        if(Constants.LOG_PRINT) {
            String methodName = new Throwable().getStackTrace()[1].getMethodName();
            int lineNumber = new Throwable().getStackTrace()[1].getLineNumber();

            Log.e(tag, "methodName : ["+methodName+"], lineNumber : ["+lineNumber+"], message : ["+ message+"]");
        }
    }

    public static void e(Throwable e) {
		if(e != null) {
			e.printStackTrace();
		}
	}
}