package com.example.weather_itw.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weather_itw.R;
import com.example.weather_itw.model.expandable.ExpandChild;
import com.example.weather_itw.model.expandable.ExpandParent;

import java.util.ArrayList;

public class WeekExpandableListAdapter extends BaseExpandableListAdapter {
    private Context mContext = null;
    private ArrayList<ExpandParent> exParent;
    private GroupHolder groupHolder;
    public WeekExpandableListAdapter(Context context, ArrayList<ExpandParent> exParent) {
        this.mContext = context;
        this.exParent = exParent;
    }

    @Override
    public int getGroupCount() {
        return exParent.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        ArrayList<ExpandChild> chList = exParent.get(groupPosition).getExpandChildArrayList();
        return chList.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return exParent.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<ExpandChild> chList = exParent.get(groupPosition).getExpandChildArrayList();
        return chList.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        groupHolder = null;
        ExpandParent parentgroup = (ExpandParent) getGroup(groupPosition);
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.expendlist_group, null);
            groupHolder = new GroupHolder();
            groupHolder.text_date = (TextView) convertView.findViewById(R.id.text_date);
            groupHolder.img_wea = (ImageView) convertView.findViewById(R.id.img_wea);
            groupHolder.max_temp = (TextView) convertView.findViewById(R.id.max_temp);
            groupHolder.min_temp = (TextView) convertView.findViewById(R.id.min_temp);
            convertView.setTag(groupHolder);
        } else {
            groupHolder = (GroupHolder) convertView.getTag();
        }
        groupHolder.text_date.setText(parentgroup.getWea_date());
        groupHolder.max_temp.setText(parentgroup.getMax_temp());
        groupHolder.min_temp.setText(parentgroup.getMin_temp());
        groupHolder.img_wea.setImageResource(parentgroup.getWea_img());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ItemHolder itemHolder = null;
        ExpandChild child = (ExpandChild) getChild(groupPosition,childPosition);

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.expendlist_item, null);
            itemHolder = new ItemHolder();
            itemHolder.wea_wspeed = (TextView)convertView.findViewById(R.id.wea_wspeed);
            itemHolder.wea_wdirection = (TextView)convertView.findViewById(R.id.wea_wdirection);
            itemHolder.wea_pop = (TextView)convertView.findViewById(R.id.wea_pop);
            itemHolder.wea_humi = (TextView)convertView.findViewById(R.id.wea_humi);
            convertView.setTag(itemHolder);

        } else {
            itemHolder = (ItemHolder) convertView.getTag();
        }
        itemHolder.wea_pop.setText(child.getPop());
        itemHolder.wea_humi.setText(child.getHumi());
        itemHolder.wea_wspeed.setText(child.getWspeed());
        itemHolder.wea_wdirection.setText(child.getWdirection());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class GroupHolder {
        public TextView text_date,max_temp,min_temp;
        public ImageView img_wea;
    }
    class ItemHolder {
        public TextView wea_pop,wea_humi,wea_wspeed,wea_wdirection;
    }

}
