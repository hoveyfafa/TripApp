package com.jiangjh.tripapp.util;

import android.app.Application;

/**
 * Created by JiaHao.Huang on 2018/2/22.
 */

public class AgentApplication extends Application  {

    private static AgentApplication application = null;

    /**
     * Constructor
     */
    public AgentApplication() {
        super();
        application = this;
    }

    /**
     * Get current application instance
     */
    public static AgentApplication getInstance() {
        return application;
    }
}
