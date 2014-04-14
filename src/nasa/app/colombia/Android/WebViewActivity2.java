package nasa.app.colombia.Android;

import nasa.app.colombia.Android.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebChromeClient;


public class WebViewActivity2 extends Activity {

	WebView mWebView;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wbview1);
        
        //String rawHTML = "<HTML>"+  
        // 	     "<body><h1>Hello Android </h1></body>"+  
    	//     "</HTML>";
        
        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient1()); 
        //mWebView.loadData(rawHTML, "text/HTML", "UTF-8");
        
        mWebView.loadUrl("file:///android_asset/www/graphstacked.html");
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
           
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    
    private class WebViewClient1 extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true; 
        } 
    }
    
    
}