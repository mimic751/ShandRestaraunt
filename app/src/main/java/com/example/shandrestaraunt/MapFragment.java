package com.example.shandrestaraunt;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragment extends Fragment implements OnMapReadyCallback {
// this does the initial setup for the map
    private GoogleMap mMap;
    private LatLng location = new LatLng(44.854168,  -93.242226); // Replace with the actual latitude and longitude of the address

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
//this part sets up the map
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
// this sets up the address and the button for directions
        TextView addressTextView = view.findViewById(R.id.addressTextView);
        addressTextView.setText("60 E Broadway, Bloomington, MN 55425");
//this sets up the button for directions
        Button directionsButton = view.findViewById(R.id.directionsButton);
        directionsButton.setOnClickListener(v -> openGoogleMaps());

        return view;
    }
//this part sets up the map
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Set map type to normal street view
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        // Add a marker at the address and move the camera
        mMap.addMarker(new MarkerOptions().position(location).title("60 E Broadway, Bloomington, MN 55425"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
    }
//this part opens google maps
    private void openGoogleMaps() {
        Uri gmmIntentUri = Uri.parse("google.navigation:q=60+E+Broadway,+Bloomington,+MN+55425");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(mapIntent);
        }
    }
}
