package com.joystays.joy.activities;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.MediaStore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AlertDialog;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.joystays.joy.R;
import com.joystays.joy.base.BaseAbstractActivity;
import com.joystays.joy.mvp.contract.activity.ProfileActContract;
import com.joystays.joy.mvp.presenter.activity.ProfileActImpl;
import com.joystays.joy.network.APIResponseCallback;
import com.joystays.joy.network.NetworkConstants;
import com.joystays.joy.pojos.LoginPojo;
import com.joystays.joy.pojos.UserProfilePojo;
import com.joystays.joy.sharepref.UserSession;
import com.joystays.joy.utils.Util;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends BaseAbstractActivity<ProfileActImpl> implements ProfileActContract.IView, View.OnClickListener, APIResponseCallback {
    private ImageView back_arrow, iv_profile;
    private LinearLayout linear_ok, ll_aadhar;
    private String hostel_id, user_id, token, password;
    private UserSession userSession;
    private EditText second_number,et_name, et_parent_name, et_mobile, et_email, et_job_details, et_purpose, et_address, et_aadhar_no, et_language;
    private CheckBox cb_terms;
    private UserProfilePojo userProfilePojo;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private TextView tv_dob, tv_adhar;
    private int selectedyear;


    private int mYear, int_year;
    private int mMonth;
    private int mDay;
    public static final int MY_REQUEST_CODE = 4;
    final int CAMERA_CAPTURE = 1;
    final int PICK_IMAGE = 2;
    private String img_param = "";
    Bitmap profile_bit, bp;
    private String base64profile = "", base64aadhar = "", aadhar_pc = "";
    private ContentValues contentValue;
    Uri imageUri;
    int scaled,orientation,height,width,x,y;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.WHITE);
        }
        presenter.start();
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        userSession = new UserSession(ProfileActivity.this);
        user_id = userSession.getUserDetails().get("id");
        token = userSession.getUserDetails().get("token");
        password = userSession.getUserDetails().get("password");

        linear_ok = findViewById(R.id.linear_ok);
        ll_aadhar = findViewById(R.id.ll_aadhar);
        back_arrow = findViewById(R.id.back_arrow);
        iv_profile = findViewById(R.id.iv_profile);
        et_name = findViewById(R.id.et_name);
        et_parent_name = findViewById(R.id.et_parent_name);
        et_mobile = findViewById(R.id.et_mobile);
        et_language = findViewById(R.id.et_language);

        et_email = findViewById(R.id.et_email);
        et_job_details = findViewById(R.id.et_job_details);
        et_purpose = findViewById(R.id.et_purpose);
        et_address = findViewById(R.id.et_address);
        et_aadhar_no = findViewById(R.id.et_aadhar_no);
        cb_terms = findViewById(R.id.cb_terms);
        tv_dob = findViewById(R.id.tv_dob);
        tv_adhar = findViewById(R.id.tv_adhar);
        second_number = findViewById(R.id.second_number);
        iv_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img_param = "profile";
                permissions();
            }
        });
        ll_aadhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img_param = "aadhar";
                permissions();
            }
        });

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        tv_dob.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(ProfileActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                tv_dob.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                                int_year = year;

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
            }
        });

        linear_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectedyear = mYear - int_year;

                // String firstLetter = mobile_num.substring(0, 1);

                if (et_name.getText().toString().isEmpty()) {
                    Toast.makeText(context, "Enter name", Toast.LENGTH_SHORT).show();
                } else if (et_parent_name.getText().toString().isEmpty()) {
                    Toast.makeText(context, "Enter guardian name", Toast.LENGTH_SHORT).show();
                } else if (second_number.getText().toString().isEmpty()) {
                    Toast.makeText(context, "Enter Alternate mobile number", Toast.LENGTH_SHORT).show();
                } else if (second_number.getText().toString().length() < 10) {
                    Toast.makeText(context, "Enter valid Alternate number", Toast.LENGTH_SHORT).show();
                } else if (tv_dob.getText().toString().isEmpty()) {
                    Toast.makeText(context, "Enter Date of birth", Toast.LENGTH_SHORT).show();
                } else if (!(selectedyear == 16 || selectedyear > 16)) {
                    Toast.makeText(getApplicationContext(), "Please enter Valid Date of Birth", Toast.LENGTH_SHORT).show();

                } else if (et_mobile.getText().toString().isEmpty()) {
                    Toast.makeText(context, "Enter mobile number", Toast.LENGTH_SHORT).show();
                } else if (et_mobile.getText().toString().length() < 10) {
                    Toast.makeText(context, "Enter valid number", Toast.LENGTH_SHORT).show();
                } else if (et_email.getText().toString().isEmpty()) {
                    Toast.makeText(context, "Enter Email Id", Toast.LENGTH_SHORT).show();
                } else if (!isValidEmail(et_email.getText().toString().trim())) {
                    Toast.makeText(context, "Enter Valid Email Id", Toast.LENGTH_SHORT).show();
                } else if (et_job_details.getText().toString().isEmpty()) {
                    Toast.makeText(context, "Enter job details", Toast.LENGTH_SHORT).show();

                } else if (et_language.getText().toString().isEmpty()) {
                    Toast.makeText(context, "Enter Languages", Toast.LENGTH_SHORT).show();

                } else if (et_purpose.getText().toString().isEmpty()) {
                    Toast.makeText(context, "Enter purpose of staying", Toast.LENGTH_SHORT).show();
                } else if (et_address.getText().toString().isEmpty()) {
                    Toast.makeText(context, "Enter permanent address", Toast.LENGTH_SHORT).show();
                } else if (et_aadhar_no.getText().toString().isEmpty()) {
                    Toast.makeText(context, "Enter aadhar number", Toast.LENGTH_SHORT).show();
               /* } else if (aadhar_pc.isEmpty() && base64aadhar.isEmpty()) {
                    Toast.makeText(context, "Upload Addhar Image", Toast.LENGTH_SHORT).show();*/
              /*  } else if (base64aadhar.isEmpty()) {
                    Toast.makeText(context, "Upload Addhar Image", Toast.LENGTH_SHORT).show();*/
                } else if (et_aadhar_no.getText().toString().length() < 12) {
                    Toast.makeText(context, "Enter valid aadhar number", Toast.LENGTH_SHORT).show();
                } else if (cb_terms.isChecked() == false) {
                    Toast.makeText(context, "Check Terms & Conditions", Toast.LENGTH_SHORT).show();
                } else {
                    callUpdateWs();
                }
            }
        });

        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        callUserProfile();

    }

    public void permissions() {


        selectImage();


    }

    private void selectImage() {

        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(context);
        pictureDialog.setTitle("Select");
        String[] pictureDialogItems = {
                "Gallery",
                "Camera"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                cameraIntent();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    private void cameraIntent() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                Log.d("hhhh", "Permissions not granted");
                // ask for permission
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
            }
        }
        try {
            //   FileName = System.currentTimeMillis() + ".jpg";
            //  Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            //    startActivityForResult(intent, CAMERA_DOC);
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            contentValue = new ContentValues();
            contentValue.put(MediaStore.Images.Media.TITLE, "New Picture");
            contentValue.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
            imageUri = getContentResolver().insert(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValue);

            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(intent, CAMERA_CAPTURE);
            //            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //            startActivityForResult(cameraIntent, CAMERA_DOC);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void choosePhotoFromGallary() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                Log.d("hhhh", "Permissions not granted");
                // ask for permission
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
            }
        }
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(Intent.createChooser(galleryIntent, "Select Picture"), PICK_IMAGE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_CANCELED) {
            return;
        } else if (requestCode == PICK_IMAGE) {
            if (data != null) {
                Uri choosenImage = data.getData();
                if (choosenImage != null) {


                    if (img_param.equalsIgnoreCase("profile")) {
                        bp = decodeUri(choosenImage, 200);
                        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                        bp.compress(Bitmap.CompressFormat.PNG, 40, bytes);
                        byte[] byte_arr = bytes.toByteArray();
                        base64profile = Base64.encodeToString(byte_arr, Base64.DEFAULT);
                        iv_profile.setImageBitmap(bp);
                    } else {

                        Toast.makeText(context, "Aadhar Image Uploaded", Toast.LENGTH_SHORT).show();
                        tv_adhar.setBackgroundResource(R.color.soap_color);
                        tv_adhar.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));


                        bp = decodeUri(choosenImage, 200);
                        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                        bp.compress(Bitmap.CompressFormat.PNG, 50, bytes);
                        byte[] byte_arr = bytes.toByteArray();
                        base64aadhar = Base64.encodeToString(byte_arr, Base64.DEFAULT);
                        //  Toast.makeText(context, "Image Captured", Toast.LENGTH_SHORT).show();

                    }

                }
            }
        } else if (requestCode == CAMERA_CAPTURE) {
            try {
                profile_bit = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }

            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            //profile_bit = (Bitmap) data.getExtras().get("data");
            new Async_BitmapWorkerTaskForProfile().execute();
            // Toast.makeText(context, "Image Captured", Toast.LENGTH_SHORT).show();
        }

    }

    class Async_BitmapWorkerTaskForProfile extends AsyncTask<Integer, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        // Compress and Decode image in background.
        @Override
        protected String doInBackground(Integer... params) {

            Bitmap profilebit = profile_bit;
            if (img_param.equalsIgnoreCase("profile")) {

                bp = decodeUri(imageUri, 200);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bp.compress(Bitmap.CompressFormat.PNG, 60, stream);
                byte[] byte_arr = stream.toByteArray();
                base64profile = Base64.encodeToString(byte_arr, Base64.DEFAULT);
                return base64profile;
            } else {

                bp = decodeUri(imageUri, 400);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bp.compress(Bitmap.CompressFormat.PNG, 60, stream);
                byte[] byte_arr = stream.toByteArray();
                base64aadhar = Base64.encodeToString(byte_arr, Base64.DEFAULT);
                return base64aadhar;


            }
        }

        // This method is run on the UI thread
        @Override
        protected void onPostExecute(String string) {
            try {

                if (img_param.equalsIgnoreCase("profile")) {

                    iv_profile.setImageBitmap(bp);


                } else {
                    Toast.makeText(context, "Aadhar Image Uploaded", Toast.LENGTH_SHORT).show();
                    tv_adhar.setBackgroundResource(R.color.soap_color);
                    tv_adhar.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected Bitmap decodeUri(Uri selectedImage, int REQUIRED_SIZE) {
        try {
            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(this.getContentResolver().openInputStream(selectedImage), null, o);
            // The new size we want to scale to
            // final int REQUIRED_SIZE =  size;
            // Find the correct scale value. It should be the power of 2.
            int width_tmp = o.outWidth, height_tmp = o.outHeight;
            int scale = 1;
            while (true) {
                if (width_tmp / 2 < REQUIRED_SIZE
                        || height_tmp / 2 < REQUIRED_SIZE) {
                    break;
                }
                width_tmp /= 2;
                height_tmp /= 2;
                scale *= 2;
            }
            // Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeStream(this.getContentResolver().openInputStream(selectedImage), null, o2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void callUpdateWs() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        // requestBody.put("user_id", user_id);
        requestBody.put("user_id", user_id);
        requestBody.put("name", et_name.getText().toString());
        requestBody.put("email_id", et_email.getText().toString());
        requestBody.put("mobile", et_mobile.getText().toString());
        requestBody.put("dob", tv_dob.getText().toString());
        requestBody.put("guardian_name", et_parent_name.getText().toString());
        requestBody.put("job_details", et_job_details.getText().toString());
        requestBody.put("purpose", et_purpose.getText().toString());
        requestBody.put("permanent_address", et_address.getText().toString());
        requestBody.put("aadhar_card_number", et_aadhar_no.getText().toString());
        requestBody.put("aadhar_card", base64aadhar);
        requestBody.put("profile_pic", base64profile);
        requestBody.put("alternate_number", second_number.getText().toString());
        requestBody.put("languages", et_language.getText().toString());
        presenter.updateProfile(context, this, requestBody);
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    private void callUserProfile() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("user_id", user_id);
        // requestBody.put("user_id", "1");
        presenter.profile(context, this, requestBody);
    }

    @Override
    protected View getView() {
        View view = null;
        view = getLayoutInflater().inflate(R.layout.activity_profile, null);
        return view;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void replaceRespectiveFragment(Fragment fragment, String[] data, String tag) {

    }

    @Override
    public void onSuccessResponse(int requestId, @NonNull String responseString, @Nullable Object object) {
        try {
            JSONObject jsonObject = new JSONObject(responseString);
            if (jsonObject.optString("status_code").equals("5000")) {
                Util.getInstance().openDialog(jsonObject.getString("message"), "Internet Status", false, context);
            } else if (NetworkConstants.RequestCode.USER_PROFILE == requestId) {
                if (jsonObject.optBoolean("status") == true) {
                    userProfilePojo = new Gson().fromJson(responseString, UserProfilePojo.class);
                    et_name.setText(userProfilePojo.getResponse().getName());
                    et_parent_name.setText(userProfilePojo.getResponse().getGuardian_name());
                    et_aadhar_no.setText(userProfilePojo.getResponse().getAadhar_card_number());
                    et_email.setText(userProfilePojo.getResponse().getEmail_id());
                    et_address.setText(userProfilePojo.getResponse().getPermanent_address());
                    et_job_details.setText(userProfilePojo.getResponse().getJob_details());
                    et_purpose.setText(userProfilePojo.getResponse().getPurpose());
                    et_mobile.setText(userProfilePojo.getResponse().getMobile());
                    et_language.setText(userProfilePojo.getResponse().getLanguages());
                    second_number.setText(userProfilePojo.getResponse().getAlternate_number());
                    tv_dob.setText(userProfilePojo.getResponse().getDob());
                    aadhar_pc = userProfilePojo.getResponse().getAadhar_card();
                    String str_date = userProfilePojo.getResponse().getDob();

                    if (str_date.isEmpty() || str_date.equalsIgnoreCase(null)) {


                    } else {
                        int_year = Integer.parseInt(str_date.substring(0, 4));

                    }

                    if (aadhar_pc.isEmpty()) {

                    } else {
                        tv_adhar.setBackgroundResource(R.color.soap_color);
                        tv_adhar.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));


                    }
                    Picasso.with(context).load(NetworkConstants.URL.Imagepath_URL + userProfilePojo.getResponse().getProfile_pic()).error(R.drawable.profile_png_image).into(iv_profile);
                }
            } else if (NetworkConstants.RequestCode.UPDATE_PROFILE == requestId) {
                if (jsonObject.optBoolean("status") == true) {
                    Toast.makeText(context, "Profile Updated successfully", Toast.LENGTH_SHORT).show();
                    LoginPojo loginPojo = new Gson().fromJson(responseString, LoginPojo.class);


                    userSession.StoreUserDetails(loginPojo.getResponse().getId(),
                            loginPojo.getResponse().getName(),
                            loginPojo.getResponse().getMobile(),
                            loginPojo.getResponse().getEmail_id(),
                            loginPojo.getResponse().getBed_confirmed(),
                            token, loginPojo.getResponse().getProfile_pic(), password);

                    System.out.println("getToken" + loginPojo.getResponse().getToken());


                    Intent i = new Intent(ProfileActivity.this, HomeActivity.class);
                    i.putExtra("to", "home");
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailureResponse(int requestId, @NonNull String errorString) {

    }

    @Override
    public void setPresenter() {
        presenter = new ProfileActImpl(this, this);
    }
}
