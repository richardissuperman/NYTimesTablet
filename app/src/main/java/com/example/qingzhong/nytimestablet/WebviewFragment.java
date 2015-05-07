package com.example.qingzhong.nytimestablet;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by qingzhong on 30/4/15.
 */
public class WebviewFragment extends Fragment {

    private WebView webView;
    private String url;



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.e("webviewdebug","On Attch");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);


        Log.e("webviewdebug","On CreateView");
       return inflater.inflate(R.layout.webviewlayout,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.webView=(WebView)getActivity().findViewById(R.id.contentwebview);
        this.webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        this.webView.loadUrl(this.url);
        Log.e("webviewdebug","On Activity Created");

    }

    @Override
    public void onStart() {
        super.onStart();
      //  this.webView.loadUrl("http://www.sina.com.cn");
        Log.e("webviewdebug","On URL loaded");

    }




    public void setUrl(String url){
        this.url=url;
    }
}
