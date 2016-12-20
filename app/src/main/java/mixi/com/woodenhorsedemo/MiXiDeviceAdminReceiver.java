package mixi.com.woodenhorsedemo;

import android.app.admin.DeviceAdminReceiver;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * 木马之设备管理器关键类
 */

public class MiXiDeviceAdminReceiver extends DeviceAdminReceiver {
    @Override
    public DevicePolicyManager getManager(Context context) {
        return super.getManager(context);
    }

    @Override
    public void onDisabled(Context context, Intent intent) {
        super.onDisabled(context, intent);
    }

    @Override
    public void onPasswordSucceeded(Context context, Intent intent) {
        super.onPasswordSucceeded(context, intent);
        show(context, "密码正确");
    }

    @Override
    public void onEnabled(Context context, Intent intent) {
        super.onEnabled(context, intent);
        show(context, "设备可以用的了");
    }

    @Override
    public CharSequence onDisableRequested(Context context, Intent intent) {
        return "这是一个可选的消息，警告有关禁止用户的请求";
    }

    public void show(Context context, String value) {
        Toast.makeText(context, value, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPasswordChanged(Context context, Intent intent) {
        super.onPasswordChanged(context, intent);
        show(context, "设备管理：密码已经改变");
    }

    @Override
    public void onPasswordFailed(Context context, Intent intent) {
        super.onPasswordFailed(context, intent);
        show(context, "设备管理：密码错误");
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
    }

    @Override
    public ComponentName getWho(Context context) {
        return super.getWho(context);

    }

}
