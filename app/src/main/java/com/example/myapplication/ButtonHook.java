package com.example.myapplication;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class ButtonHook implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        if (loadPackageParam.packageName.equals("com.example.myapplication")) {
            Class clazz = loadPackageParam.classLoader.loadClass("com.example.myapplication.MainActivity");
            XposedHelpers.findAndHookMethod(clazz, "makeToast", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("你已经被劫持！");
                    XposedBridge.log("别看了，老子已经成功Hook");
                    super.beforeHookedMethod(param);
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    param.setResult("你已被劫持");
                }
            });
        }
    }
}
