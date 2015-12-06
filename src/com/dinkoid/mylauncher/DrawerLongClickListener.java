package com.dinkoid.mylauncher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.SlidingDrawer;
import android.widget.TextView;

public class DrawerLongClickListener implements OnItemLongClickListener{

	private Context c;
	private SlidingDrawer slidingDrawer;
	private RelativeLayout homeView;

	public DrawerLongClickListener(Context c, SlidingDrawer slidingDrawer, RelativeLayout homeView) {
		this.c=c;
		this.slidingDrawer=slidingDrawer;
		this.homeView=homeView;
	}
	
	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
		LauncherActivity.appLaunchable = false;
		LayoutParams lp = new LayoutParams(view.getWidth(), view.getHeight());
		lp.leftMargin = (int)view.getX();
		lp.rightMargin = (int)view.getY();
		LayoutInflater layoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		LinearLayout ll = (LinearLayout) layoutInflater.inflate(R.layout.drawer_item, null);
		((ImageView)ll.findViewById(R.id.icon_image)).setImageDrawable(((ImageView)view.findViewById(R.id.icon_image)).getDrawable());
		((TextView)ll.findViewById(R.id.icon_text)).setText(((TextView)view.findViewById(R.id.icon_text)).getText());
		homeView.addView(ll,lp);
		slidingDrawer.animateClose();
		slidingDrawer.bringToFront();
		return false;
	}

}
