package com.example.weather_itw.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.weather_itw.R;

import java.util.List;

public class MenuExpandableListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> listGroup;
    private List<List<String>> listChild;

    public MenuExpandableListAdapter(Context context, List<String> listGroup,
                                     List<List<String>> listChild) {
        super();
        this.context = context;
        this.listGroup = listGroup;
        this.listChild = listChild;
    }

    @Override
    public int getGroupCount() {
        return listGroup.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listChild.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listGroup.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listChild.get(groupPosition).get(childPosition);

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
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        convertView = LinearLayout.inflate(context, R.layout.group_item, null);
        TextView text_group = (TextView) convertView.findViewById(R.id.group_text);
        text_group.setText(listGroup.get(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        convertView = LinearLayout.inflate(context, R.layout.child_item, null);
        TextView text_child = (TextView) convertView.findViewById(R.id.text);
        text_child.setText(listChild.get(groupPosition).get(childPosition));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
