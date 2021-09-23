package com.HInfo.HopeInfo.ui.userUpload;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.anstrontechnologies.corehelper.AnstronCoreHelper;
import com.HInfo.HopeInfo.Form_Submitted;
import com.HInfo.HopeInfo.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.iceteck.silicompressorr.FileUtils;
import com.iceteck.silicompressorr.SiliCompressor;

import java.io.File;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;

public class dataByUser extends Fragment {

    Spinner spinner;
    EditText firstName,lastName,friend,locality,landmark,city,state,pincode,phone1,phone2,writeMessage;
    ImageView upload_image;
    Button submit,upload;
    Button btnbrowse, btnupload;
    Uri FilePathUri;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    private ProgressDialog progressDialog;

    private AnstronCoreHelper coreHelper;
    int Image_Request_Code = 7;

    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    boolean isAllFieldChecked = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_data_by_user, container, false);

        spinner = root.findViewById(R.id.dropdown_custom_icon_menu);
        String[] options = {"Favipiravir","Fabiflu","Tocilizumab","Remdesivir","Oxygen Cylinder","Oxygen Regulator","Oximeter","Oxygen Concentrator","Oxygen Beds","ICU Beds","Ventilator","Plasma","Ambulance","Oral Pills and Syrup"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_expandable_list_item_1,options);
        spinner.setAdapter(adapter);

        ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        firstName = root.findViewById(R.id.user_first_name);
        lastName = root.findViewById(R.id.user_last_name);
        friend = root.findViewById(R.id.spinner_detail);
        locality = root.findViewById(R.id.locality);
        landmark = root.findViewById(R.id.Landmark);
        city = root.findViewById(R.id.city_spinner);
        state = root.findViewById(R.id.State);
        pincode = root.findViewById(R.id.Pincode);
        phone1 = root.findViewById(R.id.Phone1);
        phone2 = root.findViewById(R.id.Phone2);
        writeMessage = root.findViewById(R.id.Info_text);
        upload_image = root.findViewById(R.id.upload_image);

        upload = root.findViewById(R.id.Upload_foto);
        submit = root.findViewById(R.id.submit_user_form);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        progressDialog.setCanceledOnTouchOutside(false);

        storageReference = FirebaseStorage.getInstance().getReference("PostImages");
        databaseReference = FirebaseDatabase.getInstance().getReference("Posts");
        coreHelper = new AnstronCoreHelper(getActivity());

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED ){
                    openGallery();
                }
                else {
                    ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // store the returned value of the dedicated function which checks
                // whether the entered data is valid or if any fields are left blank.
                isAllFieldChecked = CheckAllFields();

                if (isAllFieldChecked) {
//                    compressAndUpload();
                    UploadImage();
                }

            }
        });

        return root;
    }

    private boolean CheckAllFields() {
        if (firstName.length() == 0) {
            firstName.setError("First name is required");
            return false;
        }

        if (lastName.length() == 0) {
            lastName.setError("Last name is required");
            return false;
        }

        if (friend.length() == 0) {
            friend.setError("Reference is required");
            return false;
        }

        if (locality.length() == 0) {
            locality.setError("Locality is required");
            return false;
        }
        if (landmark.length() == 0) {
            landmark.setError("Landmark is required");
            return false;
        }
        if (city.length() == 0) {
            city.setError("City is required");
            return false;
        }
        if (state.length() == 0) {
            state.setError("State is required");
            return false;
        }
        if (pincode.length() < 6) {
            pincode.setError("Pincode is required");
            return false;
        }
        if (phone1.length() == 0 || phone1.length() < 10) {
            phone1.setError("Atleast 1 phone number");
            return false;
        }
        if (writeMessage.length() == 0) {
            writeMessage.setError("message  is required");
            return false;
        }

        // after all validation return true.
        return true;
    }
    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image"), 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1 && data != null && data.getData() != null){
            imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);
                upload_image.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getActivity().getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }

    private void UploadImage() {


        if (imageUri != null) {

            progressDialog.show();
            progressDialog.setContentView(R.layout.progress_dialog);


            final File file = new File(SiliCompressor.with(getActivity()).compress(FileUtils.getPath(getActivity(), imageUri), new File(getActivity().getCacheDir(), "temp")));
            Uri uri = Uri.fromFile(file);


            StorageReference storageReference2 = storageReference.child(System.currentTimeMillis() + "." + GetFileExtension(imageUri));

            storageReference2.putFile(uri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Task<Uri> uritask = taskSnapshot.getStorage().getDownloadUrl();
                            while (!uritask.isSuccessful());

                            String downloaduri = uritask.getResult().toString();

                            boolean value = true;
                            String first = firstName.getText().toString().trim();
                            String last = lastName.getText().toString().trim();
                            String frie = friend.getText().toString().trim();
                            String loca = locality.getText().toString().trim();
                            String land = landmark.getText().toString().trim();
                            String cit = city.getText().toString().trim();
                            String stat = state.getText().toString().trim();
                            String pin = pincode.getText().toString().trim();
                            String ph1 = phone1.getText().toString().trim();
                            String ph2 = phone2.getText().toString().trim();
                            String spinValue = spinner.getSelectedItem().toString();
                            String mess = writeMessage.getText().toString().trim();
                            String  currentTime = String.valueOf(System.currentTimeMillis());


                            @SuppressWarnings("VisibleForTests")
//                            uploadinfo imageUploadInfo = new uploadinfo(first,last,frie,loca,land,cit,spinValue,stat,pin,ph1,ph2,mess,currentTime, downloaduri);
                                    uploadinfo imageUploadInfo = new uploadinfo(spinValue,first, last, frie, loca, land, cit, stat, pin, ph1, ph2, mess, currentTime, downloaduri);
                            String ImageUploadId = databaseReference.push().getKey();
                            databaseReference.child(spinValue).child(cit).child(ImageUploadId).setValue(imageUploadInfo).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                    progressDialog.dismiss();
                                    Intent i = new Intent(getActivity(), Form_Submitted.class);
                                    startActivity(i);

                                    Toast.makeText(getActivity(), "Image and Form Submitted Successfully ", Toast.LENGTH_LONG).show();

                                    firstName.setText("");
                                    lastName.setText("");
                                    friend.setText("");
                                    locality.setText("");
                                    landmark.setText("");
                                    city.setText("");
                                    state.setText("");
                                    pincode.setText("");
                                    phone1.setText("");
                                    phone2.setText("");
                                    writeMessage.setText("");
                                }
                            });



                        }
                        //after success
                    });
        }
        else {

            progressDialog.show();
            progressDialog.setContentView(R.layout.progress_dialog);

            boolean value = true;
            String first = firstName.getText().toString().trim();
            String last = lastName.getText().toString().trim();
            String frie = friend.getText().toString().trim();
            String loca = locality.getText().toString().trim();
            String land = landmark.getText().toString().trim();
            String cit = city.getText().toString().trim();
            String stat = state.getText().toString().trim();
            String pin = pincode.getText().toString().trim();
            String ph1 = phone1.getText().toString().trim();
            String ph2 = phone2.getText().toString().trim();
            String spinValue = spinner.getSelectedItem().toString();
            String mess = writeMessage.getText().toString().trim();
            String  currentTime = String.valueOf(System.currentTimeMillis());

            @SuppressWarnings("VisibleForTests")
            uploadinfo imageUploadInfo = new uploadinfo(spinValue,first, last, frie, loca, land, cit, stat, pin, ph1, ph2, mess, currentTime, "noImage");
            String ImageUploadId = databaseReference.push().getKey();
            databaseReference.child(spinValue).child(cit).child(ImageUploadId).setValue(imageUploadInfo).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {

                    Intent i = new Intent(getActivity(), Form_Submitted.class);
                    startActivity(i);

                    progressDialog.dismiss();
                    Toast.makeText(getActivity(), "Form Submitted", Toast.LENGTH_LONG).show();

                    firstName.setText("");
                    lastName.setText("");
                    friend.setText("");
                    locality.setText("");
                    landmark.setText("");
                    city.setText("");
                    state.setText("");
                    pincode.setText("");
                    phone1.setText("");
                    phone2.setText("");
                    writeMessage.setText("");
                }
            });
        }
    }


}