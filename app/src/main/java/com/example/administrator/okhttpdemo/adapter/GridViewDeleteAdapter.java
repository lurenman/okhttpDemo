package com.example.administrator.okhttpdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.administrator.okhttpdemo.R;

import java.util.ArrayList;
import java.util.HashMap;

public class GridViewDeleteAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<HashMap<String, Object>> mList;
    // 这样就不会出错了
    ArrayList<ViewHolder> holderList = new ArrayList<>();

//	public int gridCount = 0;

    public GridViewDeleteAdapter(Context mContext, ArrayList<HashMap<String, Object>> mList) {
        super();
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        if (mList == null) {
            return 0;
        } else {
            return this.mList.size();
        }
    }

    @Override
    public Object getItem(int position) {
        if (mList == null) {
            return null;
        } else {
            return this.mList.get(position);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if (convertView == null) {
            holder = new ViewHolder();

            convertView = LayoutInflater.from(this.mContext).inflate(R.layout.gridview_item_image, parent, false);

            holder.iv_img = (ImageView) convertView.findViewById(R.id.iv_img);

            holder.tv_imgurl = (TextView) convertView.findViewById(R.id.tv_imgurl);

            holderList.add(holder);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final HashMap<String, Object> m = this.mList.get(position);


		for (String k : m.keySet()) {
			switch (k) {
				case "add":
                    Glide.with(mContext).load(m.get("add")).dontAnimate().into(holder.iv_img);
                    holder.tv_imgurl.setText("add");
					break;
				case "imageUrl":
                    holder.tv_imgurl.setText("imageUrl");
                    Glide.with(mContext).load(m.get("imageUrl")).centerCrop().placeholder(R.mipmap
                            .icon_defaultimg)//占位图
                            .error(R.mipmap.icon_errorimg)//设置错误图
                            .crossFade()//动画效果
                            .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存整张图片
                            .into(holder.iv_img);
					break;
			}
		}
        return convertView;
    }
    private class ViewHolder {
        ImageView iv_img;
        TextView tv_imgurl;
        String uri = "";
    }
}
