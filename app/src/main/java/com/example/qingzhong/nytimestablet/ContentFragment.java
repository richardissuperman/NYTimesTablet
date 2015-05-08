package com.example.qingzhong.nytimestablet;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by qingzhong on 9/4/15.
 */
public class ContentFragment extends Fragment {

    public TextView testText;
    public ListView contentList;
    public ImageView loadingImg;
    public AsyncTaskLoader<JSONArray> contentLoader;
    public ArrayList<String> adpList;
    public  ContentListAdapter adp;

    private SwipeRefreshLayout swipeRefreshLayout;
    //private ScrollView scrollView;
    private String type;
    private replaceContentFragmentInterface replaceCallBack;

    public int pagecount=0;

    public ArrayList<JSONObject> detailList=new ArrayList<JSONObject>();

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
      //  contentLoader=getLoaderManager().initLoader(0,null,this);


    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Ashley","Create View");


        return inflater.inflate(R.layout.content_fragment,container,false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        Log.d("Ashley","Activity Created");

        super.onActivityCreated(savedInstanceState);
        testText=(TextView)getActivity().findViewById(R.id.testtext);
        contentList=(ListView)getActivity().findViewById(R.id.contentList);
        loadingImg=(ImageView)getActivity().findViewById(R.id.loadingImg);
        swipeRefreshLayout=(SwipeRefreshLayout)getActivity().findViewById(R.id.swipelayout);
       // scrollView=(ScrollView)getActivity().findViewById(R.id.scrollview);






        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);

               // new Thread.UncaughtExceptionHandler()
                ( new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        new ContentLoader(ContentFragment.this).execute(type,pagecount+"");
                        pagecount++;
                        swipeRefreshLayout.setRefreshing(false);

                    }
                },2000);

            }
        });



        contentList.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                Log.e("scroll state", scrollState + "");
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, final int totalItemCount) {

                  if(firstVisibleItem + visibleItemCount >= totalItemCount && totalItemCount != 0 && !swipeRefreshLayout.isRefreshing() && pagecount > 0){
                      setRefresh();
                  }

//
            }


        });


        //swipeRefreshLayout.canChildScrollUp();

        if(savedInstanceState!=null){
            Log.e("AAAAAAAA","yep savedinstance is captured");

            if(savedInstanceState.getString("type")!=null){
                Log.e("AAAAAAAA","yep type is captured");


                updateContent(savedInstanceState.getString("type"));
            }
        }
    }


    private synchronized void setRefresh(){

        //if (firstVisibleItem + visibleItemCount >= totalItemCount && totalItemCount != 0 && !swipeRefreshLayout.isRefreshing() && pagecount > 0) {

            swipeRefreshLayout.setRefreshing(true);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {


                    //if(pagecount>0) {

                    // float differ=   swipeRefreshLayout.getY();

                    Log.e("the Y change", "the Y of last listview is " + contentList.getChildAt(contentList.getChildCount() - 1).getY() + " and the Y of father layout is  " + (swipeRefreshLayout.getY() + swipeRefreshLayout.getHeight()));
                    pagecount--;
                    //new ContentLoader(ContentFragment.this).execute(type,pagecount+"");

                    //  }

                    // new ContentLoader(ContentFragment.this).execute(type,pagecount+"");
                    // swipeRefreshLayout.setRefreshing(false);
                    swipeRefreshLayout.setRefreshing(false);

                }
            }, 2000);


      //  }

    }

    public void updateContent(String type){

        this.type=type;

        Toast.makeText(getActivity(), getResources().getString(R.string.testupdatecontent)+" "+type,Toast.LENGTH_SHORT).show();
        testText.setText(type);

        adpList=new ArrayList<String>();

        adp=new ContentListAdapter(getActivity(),detailList);



        contentList.setAdapter(adp);
        //trigger the network access

        contentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Intent intent=new Intent(getActivity(),NewsActivity.class);
                //intent.putExtra("object",detailList.get(position).toString());
                //startActivity(intent);
                //Log.e("detaillist "+position,detailList.get(position).toString());
                Log.e("detaillist "+position,detailList.get(position).toString());

                replaceCallBack.replaceContent(detailList.get(position));


            }
        });

        new ContentLoader(this).execute(type,pagecount+"");
        pagecount++;
    }


    public void changeTextTest(){
        this.testText.setText("fuck change");
    }


    @Override
    public void onAttach(Activity activity) {
        Log.d("Ashley","onAttach");

        super.onAttach(activity);

        try{
            this.replaceCallBack=(replaceContentFragmentInterface)activity;


        }

        catch(Exception e){
            Log.e("Downcast Error",Log.getStackTraceString(e));
        }


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        Log.d("Ashley","SAVE INSTANCE");
        super.onSaveInstanceState(outState);

        outState.putString("type",this.testText.getText().toString());

    }

    public interface replaceContentFragmentInterface{

        public void replaceContent(JSONObject object);


    }



}
