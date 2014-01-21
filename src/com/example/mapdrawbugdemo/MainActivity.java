package com.example.mapdrawbugdemo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends Activity {

	private GoogleMap map;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	protected void onResume(){
		drawSomeCircles();
		super.onResume();
	}

	private void drawSomeCircles() {
		clearMap();
		if(map != null){
			CircleOptions[] circleOptionsList = getCircleOptionsList();
			for (CircleOptions circleOptions : circleOptionsList) {
				map.addCircle(circleOptions);
			}			
		}
	}
	
	private void clearMap(){
		if(map == null){
			map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		}
		
		if(map != null){
			map.clear();
		} else {
			Log.wtf("MapDrawBugDemo", "For some reason, map is still null.");
		}
	}
	
	private CircleOptions[] getCircleOptionsList(){
		CircleOptions[] coList = {
			getCircleOptions(38, -90, 900000, Color.BLUE),
			getCircleOptions(-38, 90, 900000, Color.RED),
			getCircleOptions(-38, -90, 900000, Color.YELLOW),
			getCircleOptions(38, 90, 900000, Color.GREEN),
			getCircleOptions(-21, 45, 900000, Color.BLUE),
			getCircleOptions(21, -45, 900000, Color.RED),
			getCircleOptions(21, 45, 900000, Color.YELLOW),
			getCircleOptions(-21, -45, 900000, Color.GREEN)
		};
		
		return coList;
	}
	
	private CircleOptions getCircleOptions(double lat, double lng, double radius, int color){
		return getCircleOptions(new LatLng(lat, lng), radius, color);
	}
	
	private CircleOptions getCircleOptions(LatLng center, double radius, int color){
		CircleOptions co = new CircleOptions();
		co.center(center);
		co.radius(radius);
		co.fillColor(color);
		co.strokeColor(Color.BLACK);
		co.strokeWidth(6);
		return co;
	}
}
