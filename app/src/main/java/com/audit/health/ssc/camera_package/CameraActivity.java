package com.audit.health.ssc.camera_package;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.audit.health.ssc.BuildConfig;
import com.audit.health.ssc.database.MyDatabase;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

import static android.os.Build.VERSION_CODES.M;


@RuntimePermissions
public class CameraActivity extends AppCompatActivity {
    MyDatabase db;
    private File file;
    private String mCurrentPhotoPath;
    private int REQUEST_TAKE_PHOTO = 1234;
    private Uri imageUri;
    private String suid;
    private String auditorId;
    private String auditId;
    private int numberCode;
    private String folderName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        suid = getIntent().getStringExtra("q_id");
        auditorId = getIntent().getStringExtra("auditorId");
        auditId = getIntent().getStringExtra("auditId");
        folderName = getIntent().getStringExtra("folderName");
        numberCode = Integer.parseInt(getIntent().getStringExtra("setCode"));
        CameraActivityPermissionsDispatcher.startCameraWithCheck(CameraActivity.this);

        db = MyDatabase.dataBase(this);
    }


    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void startCamera() {

        try {
            dispatchTakePictureIntent();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @SuppressLint("NoCorrespondingNeedsPermission")
    @OnShowRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void showRationaleForCamera(final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setMessage("Access to External Storage is required")
                .setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        request.proceed();
                    }
                })
                .setNegativeButton("Deny", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        request.cancel();
                    }
                })
                .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        try {
            if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
                imageUri = Uri.parse(mCurrentPhotoPath);
                file = new File(imageUri.getPath());


                Intent intent = new Intent();
                intent.putExtra("MESSAGE", file.getAbsolutePath());
                intent.putExtra("FIle_Name", file.getName());

//                db.imageDao().updateImagePath(file.getAbsolutePath(),suid);



                if (numberCode == 3) {
                    setResult(3, intent);
                } else {
                    setResult(2, intent);
                }
                finish();
            }else {
                finish();
            }

        } catch (Exception e) {
            finish();
            return;
        }

        finish();

    }

    @SuppressLint("NeedOnRequestPermissionsResult")
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        CameraActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    private File createImageFile() throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName;

        imageFileName = "qid_"+ timeStamp + "_";

        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                getApplicationContext().getExternalFilesDir(folderName));

        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }

    private void dispatchTakePictureIntent() throws IOException {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            Uri photoURI = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                return;
            }
            if (photoFile != null) {

                if (Build.VERSION.SDK_INT > M) {
                    photoURI = FileProvider.getUriForFile(CameraActivity.this, BuildConfig.APPLICATION_ID + ".provider",
                            photoFile);
                } else {
                    photoURI = Uri.fromFile(photoFile);

                }
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                if (numberCode == 2)
                    takePictureIntent.putExtra("android.intent.extras.CAMERA_FACING", 1);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }
}
