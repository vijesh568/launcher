package com.dinkoid.mylauncher;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class DrawerClickListener implements OnItemClickListener{

	Context c;
	LauncherActivity.Pac[] pacs;
	PackageManager pm;
	
	public DrawerClickListener(Context c, LauncherActivity.Pac[] pacs, PackageManager pm) {
		this.c =c;
		this.pm = pm;
		this.pacs = pacs;
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
		Intent intent = pm.getLaunchIntentForPackage(pacs[pos].name);
		c.startActivity(intent);
		
	}

}
