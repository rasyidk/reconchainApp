package com.example.reconchainapp.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reconchainapp.R;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.Projection;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class pickLocationActivity extends AppCompatActivity {
    MapView map = null;
    IMapController mapController;
    String slat,slong,saddress = "";
    TextView tv_address;
    Button bt_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_location);

        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));

        getSupportActionBar().hide();

        map = (MapView) findViewById(R.id.map);
        tv_address = findViewById(R.id.pl_tv_address);
        bt_confirm = findViewById(R.id.pl_bt_confirm);

        tv_address.setVisibility(View.GONE);



        mapController = map.getController();
        final GeoPoint myPoint1 = new GeoPoint(-6.3035467, 106.869351);
        mapController.setCenter(myPoint1);

        mapController.zoomTo(15);
        map.getController().animateTo(myPoint1);

        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);


        Intent intent = getIntent();
        String addr = intent.getStringExtra("addr");
        String username = intent.getStringExtra("username");
        String email = intent.getStringExtra("email");
        String password = intent.getStringExtra("password");
        String confirmPassword = intent.getStringExtra("confirmPassword");
        String role = intent.getStringExtra("role");
        String company = intent.getStringExtra("company");
        String name = intent.getStringExtra("name");

            Overlay touchOverlay = new Overlay(this){
            ItemizedIconOverlay<OverlayItem> anotherItemizedIconOverlay = null;

            @Override
            public boolean onSingleTapConfirmed(final MotionEvent e, final MapView mapView) {

                final Drawable marker = getApplicationContext().getResources().getDrawable(R.drawable.ic_map);
                Projection proj = mapView.getProjection();
                GeoPoint loc = (GeoPoint) proj.fromPixels((int)e.getX(), (int)e.getY());
                String longitude = Double.toString(((double)loc.getLongitudeE6())/1000000);
                String latitude = Double.toString(((double)loc.getLatitudeE6())/1000000);
                System.out.println("- Latitude = " + latitude + ", Longitude = " + longitude );
                slat = latitude;
                slong = longitude;

                getdataaddress();

                Log.d("Lat", latitude + "xxx" +longitude);
                ArrayList<OverlayItem> overlayArray = new ArrayList<OverlayItem>();
                OverlayItem mapItem = new OverlayItem("", "", new GeoPoint((((double)loc.getLatitudeE6())/1000000), (((double)loc.getLongitudeE6())/1000000)));
                mapItem.setMarker(marker);
                overlayArray.add(mapItem);
                if(anotherItemizedIconOverlay==null){
                    anotherItemizedIconOverlay = new ItemizedIconOverlay<OverlayItem>(getApplicationContext(), overlayArray,null);
                    mapView.getOverlays().add(anotherItemizedIconOverlay);
                    mapView.invalidate();
                }else{
                    mapView.getOverlays().remove(anotherItemizedIconOverlay);
                    mapView.invalidate();
                    anotherItemizedIconOverlay = new ItemizedIconOverlay<OverlayItem>(getApplicationContext(), overlayArray,null);
                    mapView.getOverlays().add(anotherItemizedIconOverlay);
                }
                //      dlgThread();
                return true;
            }
        };

        map.getOverlays().add(touchOverlay);
        bt_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (saddress.equals("")){
                    Toast.makeText(getApplicationContext(), "Please pick your location!", Toast.LENGTH_SHORT).show();
                }else {
                    Intent i =  new Intent(pickLocationActivity.this, signupActivity.class);

                    i.putExtra("name", name);
                    i.putExtra("username", username);
                    i.putExtra("email", email);
                    i.putExtra("username", username);
                    i.putExtra("password", password);
                    i.putExtra("confirmPassword", confirmPassword);
                    i.putExtra("addr", saddress);
                    i.putExtra("role", role);
                    i.putExtra("company", company);
                    startActivity(i);
                    finish();
                }
            }
        });
    }
    private void getdataaddress() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://nominatim.openstreetmap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        String urlx = "search.php?q="+ slat +","+slong+"&polygon_geojson=1&format=json";
        Log.d("urlx", urlx);
        pickLocationInterface request = retrofit.create(pickLocationInterface.class);
        Call<List<pickLocationList>> call = request.getContacts(urlx);
        call.enqueue(new Callback<List<pickLocationList>>() {
            @Override
            public void onResponse(Call<List<pickLocationList>> call, Response<List<pickLocationList>> response) {

                List<pickLocationList> myheroList = response.body();
                String[] oneHeroes = new String[myheroList.size()];

                for (int i = 0; i < myheroList.size(); i++) {
                    oneHeroes[i] = myheroList.get(i).getPlace_id();
                }
                tv_address.setVisibility(View.VISIBLE);
                //Toast.makeText(pickLocationActivity.this,myheroList.get(0).getDisplay_name().toString(),Toast.LENGTH_SHORT).show();
                tv_address.setText(myheroList.get(0).getDisplay_name().toString());
                saddress = myheroList.get(0).getDisplay_name().toString();
            }

            @Override
            public void onFailure(Call<List<pickLocationList>> call, Throwable t) {
                Log.e("Error",t.getMessage());
            }
        });

    }
    public void onResume(){
        super.onResume();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
        map.onResume(); //needed for compass, my location overlays, v6.0.0 and up
    }

    public void onPause(){
        super.onPause();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().save(this, prefs);
        map.onPause();  //needed for compass, my location overlays, v6.0.0 and up
    }

}