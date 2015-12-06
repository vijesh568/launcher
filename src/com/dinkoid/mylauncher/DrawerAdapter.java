package com.dinkoid.mylauncher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DrawerAdapter extends BaseAdapter {

	Context c;
	LauncherActivity.Pac[] pacs;
	public DrawerAdapter(Context c, LauncherActivity.Pac[] pacs) {
		this.c = c;
		this.pacs = pacs;
	}
	
	@Override
	public int getCount() {
		return pacs.length;
	}

	@Override
	public Object getItem(int position) {
		return pacs[position];
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}
	
	static class ViewHolder {
		TextView textView;
		ImageView imageView;
	}

	@Override
	public View getView(int pos, View convertView, ViewGroup parent) {
		
		ViewHolder viewHolder;
		LayoutInflater layoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (null == convertView) {
			convertView = layoutInflater.inflate(R.layout.drawer_item, null);
			viewHolder = new ViewHolder();
			viewHolder.imageView = (ImageView) convertView.findViewById(R.id.icon_image);
			viewHolder.textView = (TextView) convertView.findViewById(R.id.icon_text);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.textView.setText(pacs[pos].label);
		viewHolder.imageView.setImageDrawable(pacs[pos].icon);
		
		return convertView;
	}

}
