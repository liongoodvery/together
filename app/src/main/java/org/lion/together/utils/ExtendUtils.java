package org.lion.together.utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.widget.EditText;

import org.lion.together.R;

import java.io.FileOutputStream;
import java.util.List;



public class ExtendUtils {

    /**
     * 判断当前应用程序处于前台还是后台
     */
    public static boolean isAppOnForeground(final Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            String pkname = topActivity.getPackageName();
            if (!TextUtils.isEmpty(pkname) && pkname.equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断activty是否已经启动
     */
    public static boolean isActivityRunning(final Context context, Class clazz) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(10);
        if (null == tasks || tasks.size() < 1) {
            return false;
        }
        for (ActivityManager.RunningTaskInfo taskInfo : tasks) {
            if (taskInfo.baseActivity.getClassName().equals(clazz.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean hasNetWork(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null != connectivity) {
            NetworkInfo[] netInfo = connectivity.getAllNetworkInfo();
            if (null != netInfo) {
                for (int i = 0; i < netInfo.length; i++) {
                    if (netInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 获取当前网络类型
     *
     * @param context
     * @return
     */
    public static String getCurrentNetType(Context context) {
        String type = "";
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivity.getActiveNetworkInfo();
        if (info == null) {
            type = "null";
        } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {
            type = "wifi";
        } else if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
            int subType = info.getSubtype();
            if (subType == TelephonyManager.NETWORK_TYPE_CDMA
                    || subType == TelephonyManager.NETWORK_TYPE_GPRS
                    || subType == TelephonyManager.NETWORK_TYPE_EDGE) {
                type = "2g";
            } else if (subType == TelephonyManager.NETWORK_TYPE_UMTS
                    || subType == TelephonyManager.NETWORK_TYPE_HSDPA
                    || subType == TelephonyManager.NETWORK_TYPE_EVDO_A
                    || subType == TelephonyManager.NETWORK_TYPE_EVDO_0
                    || subType == TelephonyManager.NETWORK_TYPE_EVDO_B) {
                type = "3g";
            } else if (subType == TelephonyManager.NETWORK_TYPE_LTE) {
                type = "4g";
            }
        }

        return type;
    }

    /**
     * 返回当前程序版本名
     */
    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
        } catch (Exception e) {
        }
        return versionName;
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据进程号获取进程名称
     */
    public static String getAppProcessNameByPID(Context context, int pid) {
        ActivityManager manager
                = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo processInfo : manager.getRunningAppProcesses()) {
            if (processInfo.pid == pid) {
                return processInfo.processName;
            }
        }
        return "";
    }

    //根据文件路径获得图片的bitmap
    public static Bitmap getImage(Context context, String imagePath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imagePath, newOpts);
        newOpts.inJustDecodeBounds = false;
        int width = newOpts.outWidth;
        int height = newOpts.outHeight;

        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        float newwWidth = dm.widthPixels;
        float newHight = dm.heightPixels;
        int orientation = 0;
        if (width > height && newwWidth < newHight) {//保持图片方向和屏幕方向一致
            newwWidth = dm.heightPixels;
            newHight = dm.widthPixels;
            orientation = 90;
        }

        int simpleSize = 1;
        if (width > height && width > newwWidth) {
            simpleSize = (int) (newOpts.outWidth / newwWidth);
        } else if (width < height && height > newHight) {
            simpleSize = (int) (newOpts.outHeight / newHight);
        }
        if (simpleSize <= 0)
            simpleSize = 1;
        newOpts.inSampleSize = simpleSize;
        Bitmap bitMap = BitmapFactory.decodeFile(imagePath, newOpts);
        if (null == bitMap) {
            return null;
        }
        Matrix m = new Matrix();
        m.setRotate(orientation);
        Bitmap result = Bitmap.createBitmap(bitMap, 0, 0, bitMap.getWidth(), bitMap.getHeight(), m, true);
        return result;
    }

    //根据文件路径获得图片的bitmap
    public static String compressImage(Context context, String imagePath, String newPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imagePath, newOpts);
        newOpts.inJustDecodeBounds = false;
        int width = newOpts.outWidth;
        int height = newOpts.outHeight;

        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        float newwWidth = dm.widthPixels;
        float newHight = dm.heightPixels;
        int orientation = 0;
        if (width > height && newwWidth < newHight) {//保持图片方向和屏幕方向一致
            newwWidth = dm.heightPixels;
            newHight = dm.widthPixels;
            orientation = 90;
        }

        int simpleSize = 1;
        if (width > height && width > newwWidth) {
            simpleSize = (int) (newOpts.outWidth / newwWidth);
        } else if (width < height && height > newHight) {
            simpleSize = (int) (newOpts.outHeight / newHight);
        }
        if (simpleSize <= 0)
            simpleSize = 1;
        newOpts.inSampleSize = simpleSize;
        Bitmap bitMap = BitmapFactory.decodeFile(imagePath, newOpts);
        if (null == bitMap) {
            return "";
        }
        Matrix m = new Matrix();
        m.setRotate(orientation);
        Bitmap result = Bitmap.createBitmap(bitMap, 0, 0, bitMap.getWidth(), bitMap.getHeight(), m, true);

        try {
            FileOutputStream fos = new FileOutputStream(newPath);
            result.compress(Bitmap.CompressFormat.JPEG, 30, fos);
            fos.flush();
            fos.close();
            bitMap.recycle();
            result.recycle();
        } catch (Exception e) {
            if (null != bitMap) {
                bitMap.recycle();
            }
            if (null != result) {
                result.recycle();
            }
            return "";
        }
        return newPath;
    }

    public static void limitEditTextLength(final EditText et , final int limit){
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                limitTextLength(et,s,limit);
            }
        });
    }

    private static void limitTextLength(EditText et , CharSequence s , int limit){
        Context context = et.getContext();
        if (s.length()>limit){
            ToastUtil.showToast(context,context.getString(R.string.string_limit,limit));
            et.setText(s.subSequence(0,limit));
            et.setSelection(et.getText().length());
        }
    }
}
