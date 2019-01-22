package com.ice.plugin.appupdate;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.vector.update_app.UpdateAppManager;
import android.widget.Toast;

/**
 * This class echoes a string called from JavaScript.
 */
public class IceAppUpdate extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("update")) {
            String url = args.getString(0);
            this.update(url, callbackContext);
            return true;
        }
        return false;
    }

    private void update(String url, CallbackContext callbackContext) {
        cordova.getThreadPool().execute(new Runnable() {
            public void run() { 
		new UpdateAppManager
                        .Builder()
                        //当前Activity
                        .setActivity(cordova.getActivity())
                        //更新地址
                        .setUpdateUrl(url)
                        //实现httpManager接口的对象
                        .setHttpManager(new UpdateAppHttpUtil())
                        .build()
                        .update();
                
            }
        });
    }
}
