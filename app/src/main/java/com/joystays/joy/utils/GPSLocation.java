package com.joystays.joy.utils;


public class GPSLocation {
//    private static final String TAG = GPSLocation.class.getSimpleName();
//
//    private static final int UPDATE_INTERVAL = 5000; // 5 seconds
//
//    FusedLocationProviderClient locationProviderClient;
//    LocationRequest locationRequest;
//    LocationCallback locationCallback;
//    public static Location currentLocation;
//
//    private int LOCATION_PERMISSION = 100;
//
//    private final Context mContext;
//
//
//
//
//
//    public GPSLocation(Context context) {
//        this.mContext = context;
//        startGettingLocation();
//    }
//
//
//
//
//    private void startGettingLocation() {
//
//
//        locationProviderClient = LocationServices.getFusedLocationProviderClient(this);
//        locationRequest = LocationRequest.create();
//        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//        locationRequest.setInterval(UPDATE_INTERVAL);
//        locationCallback = new LocationCallback() {
//            @Override
//            public void onLocationAvailability(LocationAvailability locationAvailability) {
//                super.onLocationAvailability(locationAvailability);
//                if (locationAvailability.isLocationAvailable()) {
//                    Log.i(TAG, "Location is available");
//                } else {
//                    Log.i(TAG, "Location is unavailable");
//                }
//            }
//
//            @Override
//            public void onLocationResult(LocationResult locationResult) {
//                super.onLocationResult(locationResult);
//                Log.i(TAG, "Location result is available");
//            }
//        };
//
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
//                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//            locationProviderClient.requestLocationUpdates(locationRequest, locationCallback, GPSLocation.this.getMainLooper());
//            locationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
//                @Override
//                public void onSuccess(Location location) {
//                    currentLocation = location;
//                    Toast.makeText(GPSLocation.this, currentLocation.getLatitude() + "Long" + currentLocation.getLongitude(), Toast.LENGTH_SHORT).show();
//                }
//            });
//
//            locationProviderClient.getLastLocation().addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    Log.i(TAG, "Exception while getting the location: " + e.getMessage());
//                }
//            });
//
//
//        } else {
////            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
////                Toast.makeText(GPSLocation.this, "Permission needed",Toast.LENGTH_LONG).show();
////            } else {
////                ActivityCompat.requestPermissions(this,
////                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
////                        LOCATION_PERMISSION);
////            }
//        }
//    }
//
//
//    private void stopLocationRequests() {
//        locationProviderClient.removeLocationUpdates(locationCallback);
//    }
//
//
//
//
//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }
}
