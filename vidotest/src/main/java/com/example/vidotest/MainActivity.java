package com.example.vidotest;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    WebView webView;
    RelativeLayout relativeLayout;
    private  boolean isxCustomView=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initWebView("http://app1.yijin-game.com/#/information/detail?id=719");

    }
    void initView(){
        webView=findViewById(R.id.webView);
        relativeLayout=findViewById(R.id.rl_videoplay);
    }

    void initData(){

    }

    void initWebView(String url){
        webView.loadUrl(url);
        WebSettings ws = webView.getSettings();
        ws.setJavaScriptCanOpenWindowsAutomatically(true);
        ws.setPluginState(WebSettings.PluginState.ON);
        ws.setAllowFileAccess(true);
        ws.setLoadWithOverviewMode(true);
        ws.setBuiltInZoomControls(true);// 隐藏缩放按钮
        ws.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);// 排版适应屏幕
        ws.setUseWideViewPort(true);// 可任意比例缩放
        ws.setLoadWithOverviewMode(true);// setUseWideViewPort方法设置webview推荐使用的窗口。setLoadWithOverviewMode方法是设置webview加载的页面的模式。
        ws.setSavePassword(true);
        ws.setSaveFormData(true);// 保存表单数据
        ws.setJavaScriptEnabled(true);
        ws.setDomStorageEnabled(true);
        webView.setSaveEnabled(false);
        ws.setSaveFormData(false);
// 下面的一句话是必须的，必须要打开javaScript不然所做一切都是徒劳的
        ws.setJavaScriptEnabled(true);
        ws.setSupportZoom(false);
//setWebChromeClient主要处理解析，渲染网页等浏览器做的事情
//这个方法必须有，就算类中没有函数也可以，不然视频播放不了
        webView.setWebChromeClient(new xWebChromeClient());
//WebChromeClient是辅助WebView处理Javascript的对话框，网站图标，网站title，加载进度等
        webView.setWebViewClient(new xWebViewClientent());
    }
    /**
     * 视频全屏关键部分
     * 处理Javascript的对话框、网站图标、网站标题以及网页加载进度等
     * 需在配置文件中添加,切换横竖屏不重走生命周期,开启硬件加速
     *
     * @author
     */
    public class xWebChromeClient extends WebChromeClient {
        //进入全屏时会被调用
        @Override
        public void onShowCustomView(View view, CustomViewCallback callback) {//参数含义:播放视频的V,视频播放的回调
            if (isxCustomView){//判断是否处于全屏状态,是则退出
                callback.onCustomViewHidden();
                return;
            } //判断是否处于全屏状态,不是则进入
            isxCustomView=true;
            relativeLayout.addView(view);//将播放控件添加到布局的最上方
            relativeLayout.setVisibility(View.VISIBLE);
            webView.setVisibility(View.INVISIBLE);
        }
        // 视频播放退出全屏会被调用的
        @Override
        public void onHideCustomView() {
            if (!isxCustomView){
                return;
            }
            relativeLayout.removeAllViews();//移除所有控件
            relativeLayout.setVisibility(View.GONE);//隐藏播放布局
            webView.setVisibility(View.VISIBLE);//显示Webview
            isxCustomView=false;
        }
        // 视频加载时进程loading
        @Override
        public View getVideoLoadingProgressView() {

            return super.getVideoLoadingProgressView();
        }


    }
    @Override//关键部分 如果不写可能会出现退出界面视频仍在播放
    protected void onPause() {
        webView.reload();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            webView.onPause(); // 暂停网页中正在播放的视频
        }
        super.onPause();
    }

    public class xWebViewClientent extends WebViewClient {


        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view,url);

        }
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, String url) {

            return super.shouldInterceptRequest(view, url);
        }
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, android.webkit.WebResourceRequest request) {


            return super.shouldInterceptRequest(view, request);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {


            return super.shouldOverrideUrlLoading(view, request);
        }

        @Override
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            return super.shouldOverrideKeyEvent(view, event);
        }
    }

}
