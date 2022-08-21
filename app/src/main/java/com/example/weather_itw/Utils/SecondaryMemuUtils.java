package com.example.weather_itw.Utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import androidx.databinding.BaseObservable;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import com.example.weather_itw.Adapter.MenuExpandableListAdapter;
import com.example.weather_itw.MainActivity;
import com.example.weather_itw.MainViewModel;
import com.example.weather_itw.R;
import com.example.weather_itw.ui.AQI.AQIViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SecondaryMemuUtils extends BaseObservable {
    private final Context context;
    private ExpandableListView expandableListView; // Layout expandablelist.xml
    private final List<String> listGroup;
    private final List<List<String>> listChild;
    private  String city,location;
    private Dialog dialog;
    private final String[] groupDate;
    private final String[][] childDate;//對應的第二層目錄資料
    private MenuExpandableListAdapter mAdapter;
    private View viewList;
    private testClickListener listener;


    public SecondaryMemuUtils(Context context,
                              ExpandableListView expandableListView, List<String> listGroup,
                              List<List<String>> listChild, String[] groupDate,
                              String[][] childDate) {
        super();
        this.context = context;
        this.expandableListView = expandableListView;
        this.listGroup = listGroup;
        this.listChild = listChild;
        this.groupDate = groupDate;//第一層目錄資料
        this.childDate = childDate;//第二層目錄資料
        listener = (testClickListener) context;
    }
    public void addDate(String[] groupDate) {
        // TODO Auto-generated method stub
        for (int i = 0; i < groupDate.length; i++) {
            String group_text = groupDate[i];// 獲取第一層資料
            listGroup.add(group_text);
            List<String> children = new ArrayList<>();
            mforTwoArrays(children,childDate,i);
            listChild.add(children);
        }

    }
    private void mforTwoArrays(List<String> children,String[][] arrs, int j){
        String[] arr2 = arrs[j];
        children.addAll(Arrays.asList(arr2));
    }
    public void create(){
        //final Dialog dialog;
        viewList = ((Activity)context).getLayoutInflater().inflate(R.layout.expandablelistview,null);
        dialog = new Dialog(context);
        dialog.show();
        dialog.getWindow().setLayout(600, 800);
        dialog.setContentView(viewList);
        expandableListView = (ExpandableListView)viewList.findViewById(R.id.ew_dialog);
        expandableListView.setGroupIndicator(null);
        mAdapter = new MenuExpandableListAdapter(context,listGroup,listChild);
        expandableListView.setAdapter(mAdapter);
    }
    public void createExpandableListViewDialog(MainViewModel mainViewModel) {
        create();
        expandableListView.setOnGroupClickListener((parent, v, groupPosition, id) -> false);
        expandableListView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            if(listGroup.get(groupPosition)!=null){
                city = listGroup.get(groupPosition);
                location = listChild.get(groupPosition).get(childPosition);
                //Log.d("REQUIRED",listGroup.get(groupPosition)+","+listChild.get(groupPosition).get(childPosition));
                mainViewModel.setTitle(city+","+location);
                dialog.dismiss();
            }
            return true;
        });
    }
    public void createExpandableListViewDialog(AQIViewModel aqiViewModel){
        create();
        expandableListView.setOnGroupClickListener((parent, v, groupPosition, id) -> false);
        expandableListView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            if(listGroup.get(groupPosition)!=null){
                city = listGroup.get(groupPosition);
                location = listChild.get(groupPosition).get(childPosition);
                aqiViewModel.setTitle(city+","+location);

                dialog.dismiss();
            }
            return true;
        });
    }
    public void createExpandableListViewDialog() {
        create();
        expandableListView.setOnGroupClickListener((parent, v, groupPosition, id) -> false);
        expandableListView.setOnChildClickListener((ExpandableListView.OnChildClickListener) (parent, v, groupPosition, childPosition, id) -> {
            if(listGroup.get(groupPosition)!=null){
                city = listGroup.get(groupPosition);
                location = listChild.get(groupPosition).get(childPosition);
                String title = city+","+location;
                listener.startListener(title);
                dialog.dismiss();
            }
            return true;

        });

    }

    public interface testClickListener{
        void startListener(String title);
    }


}
