package com.torn.extend;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;

import com.alibaba.weex.plugin.annotation.WeexModule;
import com.benmu.framework.activity.AbstractWeexActivity;
import com.benmu.framework.adapter.router.RouterTracker;
import com.benmu.framework.constant.WXEventCenter;
import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.impl.ParseManager;
import com.benmu.framework.manager.impl.dispatcher.DispatchEventManager;
import com.benmu.framework.manager.impl.status.Helper;
import com.benmu.framework.manager.impl.status.StatusBarManager;
import com.benmu.framework.model.BaseResultBean;
import com.benmu.framework.model.NatigatorModel;
import com.benmu.framework.model.RouterModel;
import com.benmu.framework.model.WeexEventBean;
import com.benmu.widget.utils.ColorUtils;
import com.benmu.widget.view.BaseToolBar;
import com.gyf.barlibrary.ImmersionBar;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;

/**
 * 状态栏
 * 基于https://github.com/gyf-dev/ImmersionBar
 */
@WeexModule(name = "trStatusBar", lazyLoad = true)
public class StatusBarModule extends WXModule {
    //回调
    JSCallback mJSCallback = null;

    /**
     * 设置状态栏
     *
     * @param params
     * @param jscallback
     */
    @JSMethod(uiThread = true)
    public void setStatusBarInfo(String params, final JSCallback jscallback) {
        mJSCallback = jscallback;

        if (TextUtils.isEmpty(params)) return;

        ParseManager parseManager = ManagerFactory.getManagerService(ParseManager.class);
        RouterModel routerModel = parseManager.parseObject(params, RouterModel.class);
        routerModel.navShow = true;

        BaseToolBar toolBar = StatusBarModule.getToolBar();
        if (toolBar == null) return;

        AbstractWeexActivity abs = (AbstractWeexActivity) RouterTracker.peekActivity();
        ImmersionBar.with(abs).statusBarDarkFont(true).init();
        StatusBarManager.setHeaderBg(routerModel, abs);


        // Helper.statusBarLightMode(activity);
    }

    /**
     * 显示状态栏
     */
    @JSMethod(uiThread = true)
    public void showStatusBar() {
        Activity activity = RouterTracker.peekActivity();
        if (activity == null) return;
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN); //隐藏状态栏
    }

    /**
     * 隐藏状态栏
     */
    @JSMethod(uiThread = true)
    public void hideStatusBar() {
        Activity activity = RouterTracker.peekActivity();
        if (activity == null) return;
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN); //隐藏状态栏
    }

    private static BaseToolBar getToolBar() {
        Activity activity = RouterTracker.peekActivity();
        if (activity != null) {
            if (activity instanceof AbstractWeexActivity) {
                AbstractWeexActivity abs = (AbstractWeexActivity) activity;
                return abs.getNavigationBar();
            }
        }
        return null;
    }
}
