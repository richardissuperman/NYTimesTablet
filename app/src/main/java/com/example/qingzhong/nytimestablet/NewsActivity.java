package com.example.qingzhong.nytimestablet;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import org.json.JSONObject;


public class NewsActivity extends ActionBarActivity {

    public WebView newsWebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        newsWebview=(WebView)findViewById(R.id.newswebview);

        newsWebview.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                view.loadUrl(url);
                return true;
            }



        });
        JSONObject detail=null;
        try {
            detail = new JSONObject(getIntent().getStringExtra("object"));
        }

        catch (Exception e){
            Log.e("can not resolve the json",e.toString());
        }


        if(detail!=null) {
            try {
                newsWebview.loadUrl(detail.getString("web_url"));
            }
            catch(Exception e){
                Log.e("erorr when parsing the jsonobject",e.toString());
            }
        }
        else{
            Toast.makeText(this,"can not load detail to webview",Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_news, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
