package com.thealamin.isecure;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MyWb extends Activity {

    WebView web;
    ProgressBar progressBar;
    Button retry;
    TextView error;

    ConnectivityManager cm;

    private ValueCallback<Uri> mUploadMessage;
    private final static int FILECHOOSER_RESULTCODE=1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {
        if(requestCode==FILECHOOSER_RESULTCODE)
        {
            if (null == mUploadMessage) return;
            Uri result = intent == null || resultCode != RESULT_OK ? null
                    : intent.getData();
            mUploadMessage.onReceiveValue(result);
            mUploadMessage = null;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);


        web = (WebView) findViewById(R.id.webView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        retry = (Button) findViewById(R.id.refresh);
        error = (TextView) findViewById(R.id.error);

        cm = (ConnectivityManager) this.getSystemService(Activity.CONNECTIVITY_SERVICE);

        retry.setVisibility(View.INVISIBLE);
        error.setVisibility(View.INVISIBLE);

    /*    if(cm.getActiveNetworkInfo().isConnected()){
            web.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
            web.loadUrl("http://www.script-tutorials.com/demos/199/index.html");
        } else {
            web.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            web.loadUrl("http://www.script-tutorials.com/demos/199/index.html");
        } */

        web = new WebView(this);
        web.getSettings().setJavaScriptEnabled(true);

     //   web.loadUrl("http://thealamin.com/isecure_forum/home.php");
        web.loadUrl("http://thealamin.com/isecure_forum/home.php");




        web.setWebViewClient(new myWebClient());
        web.setWebChromeClient(new WebChromeClient()
        {


            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                view.loadUrl("file:///android_asset/error.html");

              //  web.setVisibility(View.INVISIBLE);
                error.setVisibility(View.VISIBLE);
                retry.setVisibility(View.VISIBLE);

            }
            //The undocumented magic method override
            //Eclipse will swear at you if you try to put @Override here
            // For Android 3.0+
            public void openFileChooser(ValueCallback<Uri> uploadMsg) {

                mUploadMessage = uploadMsg;
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("image/*");
                MyWb.this.startActivityForResult(Intent.createChooser(i,"File Chooser"), FILECHOOSER_RESULTCODE);

            }

            // For Android 3.0+
            public void openFileChooser( ValueCallback uploadMsg, String acceptType ) {
                mUploadMessage = uploadMsg;
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("*/*");
                MyWb.this.startActivityForResult(
                        Intent.createChooser(i, "File Browser"),
                        FILECHOOSER_RESULTCODE);
            }

            //For Android 4.1
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture){
                mUploadMessage = uploadMsg;
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("image/*");
                MyWb.this.startActivityForResult( Intent.createChooser( i, "File Chooser" ), MyWb.FILECHOOSER_RESULTCODE );

            }

        });


        setContentView(web);


    }


    public void refresh(View view){

        finish();
        startActivity(getIntent());
    }

    public class myWebClient extends WebViewClient
    {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl)
        {

            view.loadUrl("file:///android_asset/error.html");
            error.setVisibility(View.VISIBLE);
            retry.setVisibility(View.VISIBLE);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub

            view.loadUrl(url);
            return true;

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);

            progressBar.setVisibility(View.GONE);
        }
    }

    //flipscreen not loading again
    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
    }

// To handle "Back" key press event for WebView to go back to previous screen.
/*@Override
public boolean onKeyDown(int keyCode, KeyEvent event)
{
    if ((keyCode == KeyEvent.KEYCODE_BACK) && web.canGoBack()) {
        web.goBack();
        return true;
    }
    return super.onKeyDown(keyCode, event);
}*/


}
