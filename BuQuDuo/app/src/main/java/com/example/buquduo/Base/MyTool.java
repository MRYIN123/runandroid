package com.example.buquduo.Base;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.se.omapi.Session;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.widget.Toast;

import com.example.buquduo.User.InvitationCodeActivity;

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



    /**
     * 弹toast
     * */
    public static void makeToast(final Activity activity, final String msg){

        activity.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                Toast.makeText(activity,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * 粘贴
     * */
    public static void pasteAction() {

        //获取剪贴板管理器
//        ClipboardManager cm = (ClipboardManager) get(InvitationCodeActivity.this.getBaseContext().CLIPBOARD_SERVICE);
//// 创建普通字符型ClipData
//        ClipData mClipData = ClipData.newPlainText("Label", "");
//// 将ClipData内容放到系统剪贴板里。
//        cm.setPrimaryClip(mClipData);
    }

    /**
     * 复制
     * */
    public static void copyAction() {
//        ClipData mClipData =mClipboardManager.getPrimaryClip();
//
//        ClipData.Item item = mClipData.getItemAt(0);
//
//        if(item.getText().length() >0) {
//
////                    App.getInstance().toast(item.getText().toString());
//
////还需判断是否是有效的url
//
//            if(Patterns.WEB_URL.matcher(item.getText()).matches()) {
//
////符合标准
//
//                if(Session.current.getMemberInfo() ==null){
//
////                    MyTool.makeToast().getInstance().toast("请先登录");
//
//                    return;
//
//                }
//
//                Intent intent =newIntent(mContext, ArticleContentActivity.class);
//
//                intent.putExtra("implantationType", ContantsApp.IMPLANTATION_TYPE_BY_OUT);
//
//                intent.putExtra("strlink", item.getText());
//
//                mContext.startActivity(intent);
//
//            }else{
//
//                App.getInstance().toast(getString(R.string.main_implantation_btn_err));
//
//            }
//
//        }else{
//
//            App.getInstance().toast(getString(R.string.main_implantation_btn));
//
//        }
    }




}
