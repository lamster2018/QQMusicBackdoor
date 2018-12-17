package com.xposed.lahm.qqmusicbackdoor;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findClass;

/**
 * Project Name:QQMusicBackdoor
 * Package Name:com.xposed.lahm.qqmusicbackdoor
 * Created by lahm on 2018/12/14 5:06 PM .
 */
public class qqmusic implements IXposedHookLoadPackage {
    private void log(String msg) {
        XposedBridge.log("qqmusiclog--" + msg);
    }

    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        if (loadPackageParam.packageName.contains("com.tencent.qqmusic")) {
            try {
                XposedBridge.hookAllMethods(findClass("com.tencent.qqmusic.activity.IdentifyingCodeActivity", loadPackageParam.classLoader),
                        "c", new XC_MethodHook() {
                            @Override
                            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                super.beforeHookedMethod(param);
                                log("before");
                            }

                            @Override
                            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                super.afterHookedMethod(param);
                                param.setResult(true);
                                log("after");
                            }
                        });
            } catch (Exception e) {
                log("exception");
            }
        }
    }
}