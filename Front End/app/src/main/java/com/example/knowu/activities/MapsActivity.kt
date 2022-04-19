    package com.example.knowu.activities

    import android.Manifest
    import android.annotation.SuppressLint
    import android.content.Context
    import android.content.pm.PackageManager
    import android.location.Location
    import android.os.Bundle
    import android.widget.Toast
    import androidx.appcompat.app.AppCompatActivity
    import androidx.core.app.ActivityCompat
    import androidx.core.content.ContextCompat
    import com.example.knowu.R
    import com.example.knowu.utils.PermissionUtils.isPermissionGranted
    import com.example.knowu.utils.PermissionUtils.requestPermission
    import com.example.knowu.databinding.ActivityMapsBinding
    import com.google.android.gms.maps.model.LatLng

    import com.google.android.gms.maps.model.MarkerOptions
import android.location.LocationManager
    import android.view.View
    import com.google.android.gms.maps.*
    import com.google.android.gms.maps.model.Marker


    class MapsActivity : AppCompatActivity(), GoogleMap.OnMyLocationButtonClickListener,
    GoogleMap.OnMyLocationClickListener, OnMapReadyCallback,
    ActivityCompat.OnRequestPermissionsResultCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    private var permissionDenied = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        googleMap.setOnMyLocationButtonClickListener(this)
        googleMap.setOnMyLocationClickListener(this)
        enableMyLocation()
        mMap.animateCamera( CameraUpdateFactory.zoomTo( 25.0f ) );
        val zoomInit: CameraUpdate = CameraUpdateFactory.newLatLng(
            LatLng(
                -23.54893132131,
                -46.63883123123
            )
        )
        mMap.moveCamera(zoomInit);
        val localOne = LatLng(-23.55794, -46.6620413)
        val localTwo = LatLng(-23.5582426, -46.6625486)
        mMap.addMarker(MarkerOptions().position(localOne).title("Marker in Sydney"))
        mMap.addMarker(MarkerOptions().position(localTwo).title("Marker in Sydney"))

        // mMap.setOnMarkerClickListener { marker -> Toast.makeText(baseContext, "algo" + marker.title, Toast.LENGTH_SHORT).show() }
    }

    /**
     * Enables the My Location layer if the fine location permission has been granted.
     */
    @SuppressLint("MissingPermission")
    private fun enableMyLocation() {
        if (!::mMap.isInitialized) return
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            mMap.isMyLocationEnabled = true
        } else {
            // Permission to access the location is missing. Show rationale and request permission
            requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                Manifest.permission.ACCESS_FINE_LOCATION, true
            )
        }
    }


    override fun onMyLocationButtonClick(): Boolean {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show()
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false
    }

    override fun onMyLocationClick(location: Location) {
        Toast.makeText(this, "Current location:\n$location", Toast.LENGTH_LONG).show()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            return
        }
        if (isPermissionGranted(permissions, grantResults, Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Enable the my location layer if the permission has been granted.
            enableMyLocation()
        } else {
            // Permission was denied. Display an error message
            // Display the missing permission error dialog when the fragments resume.
            permissionDenied = true
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        if (permissionDenied) {
            // Permission was not granted, display error dialog.
            permissionDenied = false
        }
    }

    /**
     * Displays a dialog with error message explaining that the location permission is missing.
     */

    companion object {
        /**
         * Request code for location permission request.
         *
         * @see .onRequestPermissionsResult
         */
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    }
    fun onMarkerClick() {}
