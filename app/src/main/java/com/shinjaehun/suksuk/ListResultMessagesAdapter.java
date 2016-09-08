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

public class ListResultMessagesAdapter extends BaseAdapter {

    //FieldTrip에서 사용하던 BaseAdapter와 거의 동일하게 구현했다.
    //물론 최적화된 건지는 의문

    private static final String LOG_TAG = ListResultMessagesAdapter.class.getSimpleName();

    private List<String> resultMessages;
    private LayoutInflater inflater;
    private Context context;

    public ListResultMessagesAdapter(Context c, List<String> resultMessages) {
        this.context = c;
        this.resultMessages = resultMessages;
        this.inflater = LayoutInflater.from(context);
    }

    public void add(String message) {
        resultMessages.add(message);
    }

    @Override
    public int getCount() {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().size() : 0 ;
    }

    @Override
    public String getItem(int position) {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().get(position) : null ;
    }

    public List<String> getItems() {
        return resultMessages;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder holder;

        if (v == null) {
            v = inflater.inflate(R.layout.list_achievement, parent, false);
            holder = new ViewHolder();
            holder.nameTV = (TextView)v.findViewById(R.id.text_item_name);
//            holder.descriptionTV = (TextView)v.findViewById(R.id.text_item_desc);
//            holder.numberTV = (TextView)v.findViewById(R.id.text_item_number);
            v.setTag(holder);

        } else {
            holder = (ViewHolder)v.getTag();
        }

        String currentItem = getItem(position);

        if (currentItem != null) {
            holder.nameTV.setText(currentItem);
//            switch (currentItem) {
//                case "today_first":
//
//            }

//            holder.descriptionTV.setText(currentItem.getDescription());
//            holder.numberTV.setText(String.valueOf(currentItem.getNumber()));
        }

        return v;
    }

//    public void setItems(List<Achievement> achievements) {
//        this.achievements = achievements;
//    }
//    //지금은 DB의 raw data만 이용하지만, 언젠가는 쓸모 있을지도 모르는 법!

    class ViewHolder {
        TextView nameTV;
//        TextView descriptionTV;
//        TextView numberTV;
    }
}

