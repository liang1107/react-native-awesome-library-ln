package com.awesomelibraryln;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

public class AwesomeLibraryLnModule extends ReactContextBaseJavaModule {
    public static final String NAME = "AwesomeLibraryLn";
  public static final String  BAIWEI_WIZARPOS_SET_ACTION="android.baiwei.wizarpos.hskst.set.action";  //屏幕发送wizarpos命令
  public static final String MSG_WIZARPOS_SET_TYPE = "json_type";
  public static final String MSG_WIZARPOS_TRANSTYPE= "data1";
  public static final String MSG_WIZARPOS_TRANSAMOUNT= "data2";
  public static final String MSG_WIZARPOS_CURRENCYCODE= "data3";
  public static final String MSG_WIZARPOS_REQTRANSDATE= "data4";
  public static final String MSG_WIZARPOS_REQTRANSTIME= "data5";
  public static final String MSG_WIZARPOS_CMD= "data6";
  private static ReactContext myContext;

  public AwesomeLibraryLnModule(ReactApplicationContext reactContext) {
        super(reactContext);
        myContext=reactContext;
  }
  public static final String  BAIWEI_WIZARPOS_GET_ACTION="android.baiwei.wizarpos.hskst.get.action";  //屏幕接收wizarpos命令
  public static AwesomeReceiver lAwesomeReceiver ;
  @NonNull
  @Override
    public String getName() {
        return NAME;
    }

    // Example method
    // See https://reactnative.dev/docs/native-modules-android
    @ReactMethod
    public void multiply(double a, double b, Promise promise) {
        promise.resolve(a * b);
    }

    // 添加android toast
    @ReactMethod
    public void showToast(String message) {
        Toast.makeText(getReactApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
    //注册接收Android广播

    @ReactMethod
    public void registerReceiver() {
      //注册广播单例模式
      registerReceiverJava();
    }
    //注销接收Android广播
    @ReactMethod
    public void unregisterReceiver() {
      unregisterReceiverJava();
    }
    // 发送数据
//    sendMsgToServer_wizarpos_2 ("3","","","","","");//查询状态
//    sendMsgToServer_wizarpos_2 ("2","","","","","");//取消支付
//  sendMsgToServer_wizarpos_2 ("1","Purchase",金额,"0842","250207","135155");//支付调用


    @ReactMethod
    public void sendMsgToServer_wizarpos_2(String Cmd,String TransType,String TransAmount,String CurrencyCode,String ReqTransDate,String ReqTransTime) {
      Intent intent = new Intent();
      intent.putExtra(MSG_WIZARPOS_SET_TYPE,MSG_WIZARPOS_SET_TYPE);
      intent.putExtra(MSG_WIZARPOS_TRANSTYPE,TransType);
      intent.putExtra(MSG_WIZARPOS_TRANSAMOUNT,TransAmount);
      intent.putExtra(MSG_WIZARPOS_CURRENCYCODE,CurrencyCode);
      intent.putExtra(MSG_WIZARPOS_REQTRANSDATE,ReqTransDate);
      intent.putExtra(MSG_WIZARPOS_REQTRANSTIME,ReqTransTime);
      intent.putExtra(MSG_WIZARPOS_CMD,Cmd);
      intent.setAction(BAIWEI_WIZARPOS_SET_ACTION);
      getReactApplicationContext().sendBroadcast(intent);
    }
    //注册
  @SuppressLint("UnspecifiedRegisterReceiverFlag")
  public static void registerReceiverJava(){
    if (lAwesomeReceiver != null) {
      return;
    }
    lAwesomeReceiver = new AwesomeReceiver();
    IntentFilter mIntentFilter = new IntentFilter();
    mIntentFilter.addAction(BAIWEI_WIZARPOS_GET_ACTION);
    myContext.registerReceiver(lAwesomeReceiver, mIntentFilter);
  }
  //注销
  public static void unregisterReceiverJava(){
    if (lAwesomeReceiver != null) {
      myContext.unregisterReceiver(lAwesomeReceiver);
    }
  }
  //建立监听通过原生传递参数给react-native
  public static void sendEventToRn(String eventName, WritableMap nestedMap) {
//    WritableMap nestedMap = Arguments.createMap();

    myContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
      .emit(eventName, nestedMap);


  }


}
