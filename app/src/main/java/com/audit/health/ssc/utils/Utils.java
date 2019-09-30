package com.audit.health.ssc.utils;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.media.ExifInterface;
import android.media.MediaScannerConnection;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class Utils {

    private static File imageFile;
    private static String imagePath;

    /* type is used for set minimum and maximum  date.
     *  3- Minimum Date
     *  1- Maximum Date   */
    public static void setDatePicker(Context context, final TextView textView, String type) {
        int mYear, mMonth, mDay;
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        //launch datepicker modal
        DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        c.set(Calendar.YEAR, year);
                        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        c.set(Calendar.MONTH, monthOfYear);
                        String format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(c.getTime());

                        textView.setText(format);
                    }
                }, mYear, mMonth, mDay);

        if (type.equals("1")) {
//            datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
            datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
        } else if (type.equals("3")) {
//            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
            datePickerDialog.getDatePicker().setMinDate(new Date().getTime());
        }
        datePickerDialog.show();
    }

    public static void setTimePicker(final Context context, final TextView txt, long timeMili, final boolean bool,
                                     final boolean datecheck) {

        final int chour, cminute;
        final Calendar c = Calendar.getInstance();
        c.setTimeInMillis(timeMili);
        chour = c.get(Calendar.HOUR);
        if (bool) {
            cminute = c.get(Calendar.MINUTE);
        } else {
            cminute = c.get(Calendar.MINUTE) + 10;
        }


        final TimePickerDialog timePicker1 = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {

                Log.e("Time", hour + " : " + minute + " : ");

                if (datecheck) {
                    if (chour < hour) {
                        c.set(Calendar.HOUR, hour);
                        c.set(Calendar.MINUTE, minute);
                        String format = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(c.getTime());
                        txt.setText(format);
                    } else if (chour == hour && cminute < minute) {
                        c.set(Calendar.HOUR, hour);
                        c.set(Calendar.MINUTE, minute);
                        String format = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(c.getTime());
                        txt.setText(format);
                    } else {
                        Toast.makeText(context, "Please Select Valid Time", Toast.LENGTH_SHORT).show();
                    }
                } else {

                    if (bool) {
                        c.set(Calendar.HOUR, hour);
                        c.set(Calendar.MINUTE, minute);
                        String format = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(c.getTime());
                        txt.setText(format);
                    } else {
                        if (chour < hour) {
                            c.set(Calendar.HOUR, hour);
                            c.set(Calendar.MINUTE, minute);
                            String format = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(c.getTime());
                            txt.setText(format);
                        } else if (chour == hour && cminute < minute) {
                            c.set(Calendar.HOUR, hour);
                            c.set(Calendar.MINUTE, minute);
                            String format = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(c.getTime());
                            txt.setText(format);
                        } else {
                            Toast.makeText(context, "Please Select Valid Time", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        }, chour, cminute, true);

        timePicker1.show();

    }

    // Get Device Id..
    @SuppressLint("HardwareIds")
    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);

    }

    public static int Orientation(String filePath) {
        int angle = 0;
        try {
            ExifInterface exif = new ExifInterface(filePath);
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

            if (orientation == ExifInterface.ORIENTATION_ROTATE_90) {
                angle = 90;
            } else if (orientation == ExifInterface.ORIENTATION_ROTATE_180) {
                angle = 180;
            } else if (orientation == ExifInterface.ORIENTATION_ROTATE_270) {
                angle = 270;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return angle;
    }

    public static Bitmap changeOrientationBitmap(String picPath, int x, int y) {
        Matrix mat = new Matrix();
        mat.postRotate(Utils.Orientation(picPath));
        Bitmap bmp = null;
        bmp = getScaledBitmap(picPath, x, y);
        Bitmap correctBmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), mat, true);
        Bitmap circleBitmap = Bitmap.createBitmap(correctBmp.getWidth(), correctBmp.getHeight(), Bitmap.Config.ARGB_8888);
        BitmapShader shader = new BitmapShader(correctBmp, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        Paint paint = new Paint();
        paint.setShader(shader);
        Canvas c1 = new Canvas(circleBitmap);
        c1.drawCircle(correctBmp.getWidth() / 2, correctBmp.getHeight() / 2, correctBmp.getWidth() / 2, paint);
        return circleBitmap;
    }


    public static Bitmap getScaledBitmap(String picturePath, int width, int height) {
        BitmapFactory.Options sizeOptions = new BitmapFactory.Options();
        sizeOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(picturePath, sizeOptions);
        int inSampleSize = calculateInSampleSize(sizeOptions, width, height);
        sizeOptions.inJustDecodeBounds = false;
        sizeOptions.inSampleSize = inSampleSize;
        return BitmapFactory.decodeFile(picturePath, sizeOptions);
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            // Calculate ratios of height and width to requested height and
            // width
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            // Choose the smallest ratio as inSampleSize value, this will
            // guarantee
            // a final image with both dimensions larger than or equal to the
            // requested height and width.
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }

        return inSampleSize;
    }


    public static String saveImage(Bitmap save_bitmap, Context act,
                                   String rootDir) {
        if (isStorageAvailable(act)) {

            String root = Environment.getExternalStorageDirectory().toString();
            File rootFile = new File(root, rootDir);
            if (rootFile.mkdirs()) {
//                rootFile.mkdirs();
                File nomediaFile = new File(rootFile, ".nomedia");
                try {
                    if (nomediaFile.createNewFile()) {
//                        nomediaFile.createNewFile();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            String fname = generateUniqueName("pic") + ".jpg";
            imageFile = new File(rootFile, fname);

            FileOutputStream f = null;
            try {
                f = new FileOutputStream(imageFile);
                save_bitmap.compress(Bitmap.CompressFormat.PNG, 90, f);
                f.flush();
                f.close();
                imagePath = imageFile.getAbsolutePath();
//                galleryAddPic(imageFile, act);

            } catch (IOException e) {
                e.printStackTrace();
                return "";
            }
            return imagePath;
        }
        return "";
    }

    public static void galleryAddPic(File file, final Context context) {
        MediaScannerConnection.scanFile(context, new String[]{file.toString()}, (String[]) null,
                new MediaScannerConnection.OnScanCompletedListener() {
                    public void onScanCompleted(String path, final Uri uri) {
                        imagePath = path;

                    }
                });
    }

    @SuppressLint("SimpleDateFormat")
    public static String generateUniqueName(String filename) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                .format(new Date());
        filename = filename + timeStamp;
        return filename;
    }

    public static boolean isStorageAvailable(Context con) {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            Toast.makeText(con, "sd card is not writable", Toast.LENGTH_SHORT)
                    .show();
        } else {
            Toast.makeText(con, "SD is not available..!!", Toast.LENGTH_SHORT)
                    .show();
        }
        return false;
    }


    public static boolean IsInternetConnection(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                return true;
            }
        }
//        Toast.makeText(context, R.string.error_network, Toast.LENGTH_SHORT).show();
        return false;
    }
}
