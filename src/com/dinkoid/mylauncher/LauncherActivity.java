package com.dinkoid.mylauncher;

import java.util.List;

import android.R.drawable;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;

public class LauncherActivity extends Activity {

	DrawerAdapter adapter;
	GridView drawerGrid;
	
	class Pac {
		Drawable icon;
		String name;
		String label;
	}
	
	Pac[] pacs;
	PackageManager pm;
	private RelativeLayout homeView;
	private SlidingDrawer slidingDrawer;
	static boolean appLaunchable = true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);
		drawerGrid = (GridView) findViewById(R.id.content);
		slidingDrawer = (SlidingDrawer)findViewById(R.id.drawer);
		homeView = (RelativeLayout)findViewById(R.id.home_view);
		
		pm = getPackageManager();
		setPacs();
		
		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_PACKAGE_ADDED);
		filter.addAction(Intent.ACTION_PACKAGE_REMOVED);
		filter.addAction(Intent.ACTION_PACKAGE_CHANGED);
		filter.addDataScheme("package");
		registerReceiver(new PacReceiver(), filter);
		
	}
	
	public void setPacs() {
		final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
		mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		List<ResolveInfo> pacsList = pm.queryIntentActivities(mainIntent, 0);
		pacs= new Pac[pacsList.size()];
		for (int i = 0; i < pacs.length; i++) {
			pacs[i] = new Pac();
			pacs[i].icon = pacsList.get(i).loadIcon(pm);
			pacs[i].name = pacsList.get(i).activityInfo.packageName;
			pacs[i].label = pacsList.get(i).loadLabel(pm).toString();
		}
		new	SortApps().exchangeSort(pacs);
		adapter = new DrawerAdapter(this, pacs);
		drawerGrid.setAdapter(adapter);
		drawerGrid.setOnItemClickListener(new DrawerClickListener(this, pacs, pm));
		drawerGrid.setOnItemLongClickListener(new DrawerLongClickListener(this, slidingDrawer, homeView));
		
	}
	
	public class PacReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			setPacs();
		}
		
	}

}
