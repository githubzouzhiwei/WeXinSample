package com.zzw.wexinsample.ui.widget;

import android.content.Context;
import android.widget.Toast;

/**
 * Toast单例
 * Created by zouzhiwei on 2015/8/28.
 */
public class SingleToast {

    private static Toast toast;

    /**
     * 短时间显示
     *
     * @param context
     * @param text
     */
    public static void showShort(Context context, CharSequence text) {
        if (null == toast) {
            toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        } else {
            toast.setText(text);
        }
        toast.show();
    }

    /**
     * 长时间显示
     *
     * @param context
     * @param text
     */
    public static void showLong(Context context, CharSequence text) {
        if (null == toast) {
            toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        } else {
            toast.setText(text);
        }
        toast.show();
    }

}
