package com.audit.health.ssc.adpater;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import com.audit.health.ssc.R;
import com.audit.health.ssc.database.MyDatabase;
import com.audit.health.ssc.database.QuestionListTable;
import com.audit.health.ssc.database.UploadTable;


public class QuestionAsslistAdapter extends BaseAdapter {
    List<QuestionListTable> list;
    Context adapterContext;
    private int resource;
    private LayoutInflater inflater;
    private MyDatabase db;
    private String filepath="";


    public QuestionAsslistAdapter(Context context, List<QuestionListTable> pendigList, int fragment_pending_item) {
        this.list = pendigList;
        this.adapterContext = context;
        this.resource = fragment_pending_item;
        this.db=MyDatabase.dataBase(adapterContext);


        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return createViewFromResource(position, convertView, parent);
    }

    private View createViewFromResource(final int position, View convertView, ViewGroup parent) {
        View v;
        if (convertView == null) {
            v = inflater.inflate(resource, parent, false);
        } else {
            v = convertView;
        }

        TextView tvQues = v.findViewById(R.id.txt_ques);
        ImageView imageView1 = v.findViewById(R.id.imageView1);
        ImageView imageView2 = v.findViewById(R.id.imageView2);
        ImageView imageView3 = v.findViewById(R.id.imageView3);
        ImageView imageView4 = v.findViewById(R.id.imageView4);
        ImageView imageView5 = v.findViewById(R.id.imageView5);




//

        try {


            // Get the dimensions of the View maine check kiya tha pr kch dikha nhi aisa
            int targetW = imageView1.getWidth();
            int targetH = imageView1.getHeight();

            // Get the dimensions of the bitmap
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inJustDecodeBounds = true;

            List<UploadTable> listImage = db.uploadDao().getAllImage(position);


            Log.e("list",listImage.toString());

            int c;
            for (c = 0; c < listImage.size(); c++) {
                filepath = listImage.get(c).getImageUrl();

                BitmapFactory.decodeFile(filepath, bmOptions);
                int photoW = bmOptions.outWidth;
                int photoH = bmOptions.outHeight;

                // Determine how much to scale down the image
                int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

                // Decode the image file into a Bitmap sized to fill the View
                bmOptions.inJustDecodeBounds = false;
                bmOptions.inSampleSize = scaleFactor;

                Bitmap bitmap = BitmapFactory.decodeFile(filepath, bmOptions);
                Bitmap scaledbitmap = getResizedBitmap(bitmap, 320);
                FileOutputStream out = null;
                try {
                    out = new FileOutputStream(filepath);
                } catch (FileNotFoundException e) {
                    Log.e("TAG", "ERROR writing to image file!", e);
                }
                scaledbitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                BitmapDrawable ob = new BitmapDrawable(adapterContext.getResources(), bitmap);
                if (c == 0) {
                    imageView1.setBackgroundDrawable(ob);
                }

                if (c == 1) {
                    imageView2.setBackgroundDrawable(ob);
                }

                if (c == 2) {
                    imageView3.setBackgroundDrawable(ob);

                }
                if (c == 3) {
                    imageView4.setBackgroundDrawable(ob);

                }
                if (c == 4) {
                    imageView5.setBackgroundDrawable(ob);

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        tvQues.setText(position + 1 + ") " + list.get(position).getQuestion());


        return v;
    }







    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 0) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }
}