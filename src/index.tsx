import { NativeModules, Platform } from 'react-native';

const LINKING_ERROR =
  `The package 'react-native-awesome-library-ln' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go\n';

const AwesomeLibraryLn = NativeModules.AwesomeLibraryLn
  ? NativeModules.AwesomeLibraryLn
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );

export function multiply(a: number, b: number): Promise<number> {
  return AwesomeLibraryLn.multiply(a, b);
}

export function showToast(message: string) {
  return AwesomeLibraryLn.showToast(message);
}

export function registerReceiver() {
  return AwesomeLibraryLn.registerReceiver();
}
export function unregisterReceiver() {
  return AwesomeLibraryLn.unregisterReceiver();
}
//   sendMsgToServer_wizarpos_2 ("3","","","","","");//查询状态
//    sendMsgToServer_wizarpos_2 ("2","","","","","");//取消支付
//  sendMsgToServer_wizarpos_2 ("1","Purchase",金额,"0842","250207","135155");//支付调
export function sendMsgToServer_wizarpos_2(
  Cmd: string,
  TransTyp: string,
  TransAmount: string,
  CurrencyCode: string,
  ReqTransDate: string,
  ReqTransTime: string
) {
  return AwesomeLibraryLn.sendMsgToServer_wizarpos_2(
    Cmd,
    TransTyp,
    TransAmount,
    CurrencyCode,
    ReqTransDate,
    ReqTransTime
  );
}
