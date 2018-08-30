package com.torn.extend;

import android.app.Activity;

import com.alibaba.weex.plugin.annotation.WeexModule;
import com.benmu.framework.constant.WXEventCenter;
import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.impl.dispatcher.DispatchEventManager;
import com.benmu.framework.model.WeexEventBean;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;

/**
 * Created by liuyuanxiao on 2018/5/4.
 */
@WeexModule(name = "trExtend", lazyLoad = true)
public class ExtendModule extends WXModule {
    JSCallback mJSCallback = null;

    /**
     * 监听返回键事件
     *
     * @param params
     * @param callback
     */
    @JSMethod(uiThread = true)
    public void onBack(String params, JSCallback callback) {
        mJSCallback = callback;
        Activity activity = null;
    }

    @Override
    public boolean onActivityBack() {
        return super.onActivityBack();
    }
}
