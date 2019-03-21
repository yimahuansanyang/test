package com.example.expresslist;

/**
 * Created by zhangdachun on 2017/12/20.
 */

public class KuiDi {
    public KuiDi(String content, String time) {
        this.content = content;
        this.time = time;

    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private String content;
    private String time;
}
