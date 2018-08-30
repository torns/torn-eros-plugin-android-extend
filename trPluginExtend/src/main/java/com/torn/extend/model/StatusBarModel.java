package com.torn.extend.model;

import java.io.Serializable;


public class StatusBarModel implements Serializable {

    public String statusBarStyle = "Default";
    public String backgroundColor;
    public boolean through = false;

    public StatusBarModel() {
    }

    public StatusBarModel(String statusBarStyle, String backgroundColor ,boolean through) {
        this.statusBarStyle = statusBarStyle;
        this.backgroundColor = backgroundColor;
        this.through = through;
    }
}
