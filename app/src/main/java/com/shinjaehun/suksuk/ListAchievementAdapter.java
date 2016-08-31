package com.shinjaehun.suksuk;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by shinjaehun on 2016-08-01.
 */
public class ListAchievementAdapter extends BaseAdapter {

    //FieldTrip에서 사용하던 BaseAdapter와 거의 동일하게 구현했다.
    //물론 최적화된 건지는 의문

    private static final String LOG_TAG = ListAchievementAdapter.class.getSimpleName();

    private List<Achievement> achievements;
    private LayoutInflater inflater;
    private Context context;

    public ListAchievementAdapter(Context c, List<Achievement> achievements) {
        this.context = c;
        this.achievements = achievements;
        this.inflater = LayoutInflater.from(context);
    }

    public void add(Achievement a) {
        achievements.add(a);
    }

    @Override
    public int getCount() {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().size() : 0 ;
    }

    @Override
    public Achievement getItem(int position) {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().get(position) : null ;
    }

    public List<Achievement> getItems() {
        return achievements;
    }

    @Override
    public long getItemId(int position) {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().get(position).getId() : position ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder holder;

        if (v == null) {
            v = inflater.inflate(R.layout.list_achievement, parent, false);
            holder = new ViewHolder();
            holder.nameTV = (TextView)v.findViewById(R.id.text_item_name);
            holder.descriptionTV = (TextView)v.findViewById(R.id.text_item_desc);
            holder.numberTV = (TextView)v.findViewById(R.id.text_item_number);
            v.setTag(holder);

        } else {
            holder = (ViewHolder)v.getTag();
        }

        Achievement currentItem = getItem(position);

        if (currentItem != null) {
            holder.nameTV.setText(currentItem.getName());
            holder.descriptionTV.setText(currentItem.getDescription());
            holder.numberTV.setText(String.valueOf(currentItem.getNumber()));
        }

        return v;
    }

    public void setItems(List<Achievement> achievements) {
        this.achievements = achievements;
    }
    //지금은 DB의 raw data만 이용하지만, 언젠가는 쓸모 있을지도 모르는 법!

    class ViewHolder {
        TextView nameTV;
        TextView descriptionTV;
        TextView numberTV;
    }
}
