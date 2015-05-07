package com.example.qingzhong.nytimestablet;

import android.app.Activity;
import android.support.v4.app.ListFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by qingzhong on 9/4/15.
 */
public class ChoiceFragment extends ListFragment {

    public onChoiceSelectedListener cCallBack;

    public interface onChoiceSelectedListener{
        public void updateContent(int position);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_activated_1,Constant.lists));

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            cCallBack=(onChoiceSelectedListener)activity;
        }
        catch(Exception e){

            Toast.makeText(getActivity(),R.string.unsuccesfullattach,Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onStart() {
        super.onStart();
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        //super.onListItemClick(l, v, position, id);

        getListView().setItemChecked(position,true);

        cCallBack.updateContent(position);
    }


}
