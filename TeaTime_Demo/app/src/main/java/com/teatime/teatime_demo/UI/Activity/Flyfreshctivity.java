package com.teatime.teatime_demo.UI.Activity;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.teatime.library_teatime.View.flyrefresh.FlyRefreshLayout;
import com.teatime.teatime_demo.R;

import java.util.ArrayList;

public class Flyfreshctivity extends AppCompatActivity implements FlyRefreshLayout.OnPullRefreshListener {

    private FlyRefreshLayout mFlylayout;
    private ListView listview;
    private MyAdapter myAdapter;
    private ArrayList<String> res=new ArrayList<String>() {};
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mFlylayout = (FlyRefreshLayout) findViewById(R.id.fly_layout);
        listview= (ListView) findViewById(R.id.lv_show);
        myAdapter=new MyAdapter(this);
        res.add("长得");
        res.add("长得");
        res.add("长得");
        res.add("长得");
        listview.setAdapter(myAdapter);
        mFlylayout.setOnPullRefreshListener(this);
        mFlylayout.setBackgroundResource(R.mipmap.ic_launcher);
        View actionButton = mFlylayout.getHeaderActionButton();
        if (actionButton != null) {
            actionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mFlylayout.startRefresh();
                }
            });
        }

        myAdapter.notifyDataSetChanged();

    }

    /**
     * 用于刷新回调，添加耗时操作
     * @param view
     */
    @Override
    public void onRefresh(FlyRefreshLayout view) {
        res.add("ssds");
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mFlylayout.onRefreshFinish();
                myAdapter.notifyDataSetChanged();
            }
        }, 2000);
    }

    /**
     * 刷新结束回调
     * @param view
     */
    @Override
    public void onRefreshAnimationEnd(FlyRefreshLayout view) {

    }


    class MyAdapter extends BaseAdapter{

    private  Context context;
       public MyAdapter(Context context){
           this.context=context;

       }

        @Override
        public int getCount() {
            return res.size();
        }

        @Override
        public Object getItem(int position) {
            return res.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder=null;
            if (convertView==null){
                viewHolder=new ViewHolder();
                convertView=LayoutInflater.from(context).inflate(R.layout.view_list_item,null);
                viewHolder.textView= (TextView) convertView.findViewById(R.id.textView);
                convertView.setTag(viewHolder);
            }else {
                viewHolder= (ViewHolder) convertView.getTag();
            }
            viewHolder.textView.setText(res.get(position));
            return convertView;
        }
    }

    public static class ViewHolder{
        public TextView textView;


    }





}

