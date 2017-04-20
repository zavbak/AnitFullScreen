package aniy.com.anitfullscreen.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import aniy.com.anitfullscreen.ConstantManager;
import aniy.com.anitfullscreen.R;
import aniy.com.anitfullscreen.ui.settingactivity.SettingsActivity;

/**
 * Created by user on 20.04.2017.
 */

public class MainActivity extends Activity implements View.OnClickListener {

    WebView webView;
    SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    String getUrl(){
        mSharedPreferences = getSharedPreferences(ConstantManager.APP_NAME_PREF, MODE_PRIVATE);
        String strUrl = mSharedPreferences.getString(ConstantManager.URL_PREF, null);
        return strUrl;
    }

    @Override
    protected void onStart() {
        super.onStart();

        String url = getUrl();
        if(url == null){
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }else{
            startRefresh();
        }

    }

    public void startRefresh() {
        MyTask myTask = new MyTask();
        myTask.execute();
    }

    public void refresh() {

        webView = (WebView) findViewById(R.id.main_webview);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl(getUrl());
        String url = getUrl();

        Toast.makeText(this, url, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        startRefresh();

    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
    }

    class MyTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            refresh();
        }
    }

}