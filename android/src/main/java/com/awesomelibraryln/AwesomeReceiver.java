package com.awesomelibraryln;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

public class AwesomeReceiver  extends BroadcastReceiver {
  private static final String TAG = "AwesomeReceiver";
  public static final String MSG_WIZARPOS_GET_TYPE = "json_type";
  public static final String MSG_WIZARPOS_GET_STA= "data1";
  @Override
    public void onReceive(android.content.Context context, android.content.Intent intent) {
      Log.d(TAG, "onReceive: "+ intent.getAction());

      if (intent.getAction().equals(AwesomeLibraryLnModule.BAIWEI_WIZARPOS_GET_ACTION)){
        if((byte)0x01 ==(byte)intent.getIntExtra(MSG_WIZARPOS_GET_STA, 0))//支付成功
        {

          Log.d(TAG, "onReceive: wizarpos支付成功");
          WritableMap nestedMap = Arguments.createMap();
          nestedMap.putString("data", "wizarpos支付成功");
          nestedMap.putInt("code", 200);
          AwesomeLibraryLnModule.sendEventToRn("wizarposjson", nestedMap);
        }
        else if((byte)0x02 ==(byte)intent.getIntExtra(MSG_WIZARPOS_GET_STA, 0))//支付失败
        {

            Log.d(TAG, "onReceive: wizarpos支付失败");
            WritableMap nestedMap = Arguments.createMap();
            nestedMap.putString("data", "wizarpos支付失败");
            nestedMap.putInt("code", 100);
            AwesomeLibraryLnModule.sendEventToRn("wizarposjson", nestedMap);
        }
        else if((byte)0x03 ==(byte)intent.getIntExtra(MSG_WIZARPOS_GET_STA, 0))//握手应答
        {

          Log.d(TAG, "onReceive: wizarpos握手成功");
          WritableMap nestedMap = Arguments.createMap();
          nestedMap.putString("data", "wizarpos握手成功");
          nestedMap.putInt("code", 300);
          AwesomeLibraryLnModule.sendEventToRn("wizarposjson", nestedMap);
        }
      }
    }



  // 发送数据到reactnative ,去接收数据 myContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
  //                .emit(eventName, paramss);


}
