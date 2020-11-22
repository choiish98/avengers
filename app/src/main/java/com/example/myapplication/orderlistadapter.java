package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.List;

public class orderlistadapter extends BaseAdapter {

    private Context context;
    private List<order> orderList;
    private Activity parentActivity;
    private List<order> saveList;

    public orderlistadapter(Context context, List<order> orderList, Activity parentActivity, List<order> saveList){
        this.context = context;
        this.orderList = orderList;
        this.parentActivity = parentActivity;
        this.saveList = saveList;
    }

    @Override
    public int getCount() {
        return orderList.size();
    }

    @Override
    public Object getItem(int i) {
        return orderList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View convertView, final ViewGroup parent) {
        View v = View.inflate(context, R.layout.order, null);
        final TextView announcemnet_title = v.findViewById(R.id.announcement_title);
        TextView announcemnet_tag = v.findViewById(R.id.announcement_tag);
        TextView announcemnet_recruitment = v.findViewById(R.id.announcement_recruitment);
        TextView announcemnet_contents = v.findViewById(R.id.announcement_contents);

        announcemnet_title.setText(orderList.get(i).getAnnouncemnet_title());
        announcemnet_tag.setText(orderList.get(i).getAnnouncemnet_tag());
        announcemnet_recruitment.setText(orderList.get(i).getAnnouncemnet_recruitment());
        announcemnet_contents.setText(orderList.get(i).getAnnouncemnent_contents());

        v.setTag(orderList.get(i).getAnnouncemnet_title());

        Button deleteButton = (Button) v.findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                orderList.remove(i);
                                notifyDataSetChanged();
                            }
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                deleteRequest deleteRequest = new deleteRequest(announcemnet_title.getText().toString(), responseListener);
                RequestQueue queue = Volley.newRequestQueue(parentActivity);
                queue.add(deleteRequest);
            }
        });

        return v;
    }
}
