package comp404.BookIst;
import java.io.IOException;
import java.util.List;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import comp404.BookIst.R;


public class MapOverlay extends MapActivity implements LocationListener {
    
	 private static final String TAG = "LocationActivity";
	 Geocoder geocoder;
	 TextView locationNext;
	 MapView map;
	 MapController mapController;
	 LocationManager locationManager;
	
	
	/** Called when the activity is first created. */
   @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.main);
       
       locationNext = (TextView)this.findViewById(R.id.lblLocationInfo);
       map = (MapView)this.findViewById(R.id.mapview);
       map.setBuiltInZoomControls(true);
       
       mapController = map.getController(); //<4>
       mapController.setZoom(16);
       
       locationManager = (LocationManager)this.getSystemService(LOCATION_SERVICE); //<2>
       
       geocoder = new Geocoder(this); 
       
       Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER); 
       if (location != null) {
         Log.d(TAG, location.toString());
         this.onLocationChanged(location); 
       }
   }
   @Override
   protected boolean isRouteDisplayed() {
     return false;
   }
   
   @Override
   protected void onResume() {
     super.onResume();
     
	locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10, this); 
   }

   @Override
   protected void onPause() {
     super.onPause();
     locationManager.removeUpdates(this); 
   }
   
   @Override
   public void onLocationChanged(Location location) { 
     Log.d(TAG, "onLocationChanged with location " + location.toString());
     String text = String.format("Lat:\t %f\nLong:\t %f\nAlt:\t %f\nBearing:\t %f", location.getLatitude(), 
                   location.getLongitude(), location.getAltitude(), location.getBearing());
     this.locationNext.setText(text);
     
     try {
       List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 10); //<10>
       for (Address address : addresses) {
         this.locationNext.append("\n" + address.getAddressLine(0));
       }
       
       int latitude = (int)(location.getLatitude() * 1000000);
       int longitude = (int)(location.getLongitude() * 1000000);

       GeoPoint point = new GeoPoint(latitude,longitude);
       mapController.animateTo(point); //<11>
       
     } catch (IOException e) {
       Log.e("LocateMe", "Could not get Geocoder data", e);
     }
   }
	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
}
