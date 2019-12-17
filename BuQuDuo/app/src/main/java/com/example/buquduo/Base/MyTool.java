package com.example.buquduo.Base;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;

public class MyTool {


    /**
     * @Description: 跟服务器版本比对，是否需要更新
     * @Params version_Service 服务器最新APP版本号
     */
    public static boolean isNeedUpdate(Context context, String version_service) {
        boolean isNeed = false;
        try {
            //获取当前版本
            String version_current = String.valueOf(getVersionCode(context));

            if (!TextUtils.isEmpty(version_service) && !TextUtils.isEmpty(version_current)) {
                String[] service = version_service.split("\\.");
                String[] current = version_current.split("\\.");
                //这里因为服务器和本地版本号的格式一样，所以随便哪个的长度都可以使用
                for (int i = 0; i < service.length; i++) {
                    int s = Integer.parseInt(service[i]);
                    int c = Integer.parseInt(current[i]);
                    if (c > s) {
                        isNeed = false;
                        break;
                    }
                    if (c < s) {
                        isNeed = true;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("","比较版本号时出错");
        }
        return isNeed;
    }


    public static int getVersionCode(Context mContext) {
        int versionCode = 0;
        try {
            //获取软件版本号，对应AndroidManifest.xml下android:versionCode
            versionCode = mContext.getPackageManager().
                    getPackageInfo(mContext.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

}
