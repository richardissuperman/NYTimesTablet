package com.example.qingzhong.nytimestablet;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;


//import java.net.URLConnection;

//import java.net.URL;


public class MainActivity extends  FragmentActivity implements ChoiceFragment.onChoiceSelectedListener, ContentFragment.replaceContentFragmentInterface {

    //com.android.okhttp
    ContentFragment contentFragment;

    WebviewFragment webviewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contentFragment=new ContentFragment();
        webviewFragment=new WebviewFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.contentfragmentlayout,contentFragment).addToBackStack(null).commit();
        //contentFragment=(ContentFragment)getSupportFragmentManager().findFragmentById(R.id.content_fragment);
        //this.fuboTest();
    }

    public void fuboTest(){

        try {
            URL url = new URL("https://192.168.1.5:7443/worklightconsole");
            URLConnection urlConnection = url.openConnection();

            InputStream in = urlConnection.getInputStream();
            Log.e("HTTPS!!!!!!!!!", in.toString());
        }

        catch (Exception e){
            String ant=e.toString();
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
            Log.e("HTTPSError",e.toString());
        }
        //copyInputStreamToOutputStream(in, System.out);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

       this.contentFragment.changeTextTest();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void updateContent(int position) {

        String choice=Constant.lists[position];



        switch (choice){

            case "article":
                contentFragment.updateContent(choice);
                break;
            case "movie":
                contentFragment.updateContent(choice);
                break;
            case "books":
                contentFragment.updateContent(choice);
                break;
            default:
                contentFragment.updateContent(choice);
                break;
        }



    }

    @Override
    public void replaceContent(JSONObject object) {

        Log.d("replace!",object.toString());

       // FragmentManager manager=getFragmentManager();

        FragmentManager manager=getSupportFragmentManager();

       // this.webviewFragment=new WebviewFragment();

        Bundle bundle=new Bundle();
        bundle.putString("type","FUCK");
        contentFragment.onSaveInstanceState(bundle);


        try {
            webviewFragment.setUrl(object.getString("web_url"));
        }
        catch(Exception e){
            Log.e("set url error",Log.getStackTraceString(e));
        }


        manager.beginTransaction().replace(R.id.contentfragmentlayout,this.webviewFragment).addToBackStack(null).commit();
        try {
            Log.e("web_url is",object.getString("web_url"));
            Toast.makeText(this,object.getString("web_url"),Toast.LENGTH_SHORT).show();

          //  this.webviewFragment.changeUrl(object.getString("web_url"));

        }
        catch(Exception e){
            Log.e("can not read the JsonObject, web_url is missing",Log.getStackTraceString(e));
        }

    }


    //onChoice
}
