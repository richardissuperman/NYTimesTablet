package com.example.qingzhong.nytimestablet;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by qingzhong on 28/4/15.
 */
public class ContentListAdapter extends BaseAdapter {

    private Context context;

    private ArrayList<JSONObject> jsonObjectArrayList;


    private LayoutInflater listContainer;



    public ContentListAdapter(Context context,ArrayList<JSONObject> arrayList){

        this.context=context;

        this.jsonObjectArrayList=arrayList;

        this.listContainer=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return this.jsonObjectArrayList.size();
    }


    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        convertView=this.listContainer.inflate(R.layout.content_list_layout,null);
        ContentListDetail detail=new ContentListDetail();
        detail.titleImg=(ImageView)convertView.findViewById(R.id.content_list_img);
        detail.titleImg.setImageResource(R.drawable.ajaxloader);

        detail.titleText=(TextView)convertView.findViewById(R.id.content_list_text);

        try{

        detail.titleText.setText(this.jsonObjectArrayList.get(position).getJSONObject("headline").getString("main"));
        }

        catch (Exception e){
            Log.e("JSON Error",Log.getStackTraceString(e));
            detail.titleText.setText("Parse Json error");
        }
        //convertView.setTa


        convertView.setTag(detail);
        return convertView;
    }







    public class ContentListDetail{


        TextView titleText;

        ImageView titleImg;

    }
}
