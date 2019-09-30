package com.audit.health.ssc.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.audit.health.ssc.R;
import com.audit.health.ssc.adpater.ProofAdapter;
import com.audit.health.ssc.camera_package.CameraActivity;
import com.audit.health.ssc.camera_package.VideoRecordActivity;
import com.audit.health.ssc.database.BatchTable;
import com.audit.health.ssc.database.LoginTable;
import com.audit.health.ssc.database.MyDatabase;
import com.audit.health.ssc.database.QuestionListTable;
import com.audit.health.ssc.database.UploadTable;
import com.audit.health.ssc.upload.MultipleFileUpload;
import com.audit.health.ssc.utils.ConnectionChecker;
import com.audit.health.ssc.utils.Constant;
import com.audit.health.ssc.utils.Utils;

public class ProofActivity extends AppCompatActivity implements ProofAdapter.ItemClickListner {
    Button submit;
    MyDatabase db;
    RecyclerView answerRecycler;
    Dialog dil;
    Button prev;
    Button btnSubmit;
    TextView txt_question;
    TextView txt_count;
    EditText edtRemark;
    EditText rtime;
    ImageView imageView, addImage, image1, image2, image3, image4, image5, image6, image7, image8, image9, image10;
    Switch simpleSwitch;
    int total = 0;
    int totalImage = 0;
    private ProofAdapter proofAdapter;
    private Dialog dialog;
    private int count = 0;
    private String filepath;
    private String filepath1 = "";
    private String filepath2 = "";
    private String filepath3 = "";
    private String filepath4 = "";
    private String filepath5 = "";
    private List<UploadTable> uplodlist = new ArrayList<>();
    private List<QuestionListTable> quesList = new ArrayList<>();
    private String filename = "";
    private int index = 0;
    private List<BatchTable> batchList;
    private String batchId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proof);
        db = MyDatabase.dataBase(this);
        hideKeyboard();

        findViewById(R.id.backImage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        batchId= getIntent().getStringExtra("batchId");


        uplodlist.clear();
        uplodlist = db.uploadDao().getAll();
        batchList = db.batchDao().getAll();
        quesList.clear();

        quesList = db.questionListDao().getAll();


        answerRecycler = (RecyclerView) findViewById(R.id.proof_data);
        answerRecycler.setHasFixedSize(true);

        answerRecycler.setLayoutManager(new LinearLayoutManager(this));


        callAdapterMethod();


        findViewById(R.id.login_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                total = 0;
                for (int c = 0; c < quesList.size(); c++) {
                    total = total + Integer.parseInt(quesList.get(c).getQuantity());


                }
                totalImage = 0;
                for (int c1 = 0; c1 < uplodlist.size(); c1++) {
                    totalImage = totalImage + db.uploadDao().getAllImage(c1).size();
                }


                Log.e("total", total + "  " + totalImage);


                if (ConnectionChecker.isConnectingToInternet(ProofActivity.this)) {
                    new MultipleFileUpload(ProofActivity.this,batchId);
                } else {
                    Toast.makeText(ProofActivity.this, Constant.CHECK_NETWORK_MESSAGE, Toast.LENGTH_LONG).show();

                }

            }
        });


    }

    private void callAdapterMethod() {

        if (quesList != null && quesList.size() > 0) {

            proofAdapter = new ProofAdapter(ProofActivity.this, quesList);
            proofAdapter.setItemClickListner(this);
            answerRecycler.setAdapter(proofAdapter);

        }

    }

    @Override
    public void itemClickPos(int pos) {
        ShowMFormData("", pos);


    }


    @Override
    public void onBackPressed() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(ProofActivity.this);


        // Setting Dialog Message
        alertDialog.setMessage("Please carefully Submit all question");

        // on pressing cancel button
        alertDialog.setNegativeButton("Okay", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }

    public void ShowMFormData(final String batchid, final int position) {
        dialog = new Dialog(ProofActivity.this);
        //hiding default title bar of dialog
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_mform_proof);
        dialog.getWindow().getAttributes().width = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.setCanceledOnTouchOutside(true);
        prev = (Button) dialog.findViewById(R.id.task_pre);
        btnSubmit = (Button) dialog.findViewById(R.id.task_next);
        imageView = (ImageView) dialog.findViewById(R.id.imageView);
        addImage = (ImageView) dialog.findViewById(R.id.addImage);

        image1 = (ImageView) dialog.findViewById(R.id.imageView1);
        image2 = (ImageView) dialog.findViewById(R.id.imageView2);
        image3 = (ImageView) dialog.findViewById(R.id.imageView3);
        image4 = (ImageView) dialog.findViewById(R.id.imageView4);
        image5 = (ImageView) dialog.findViewById(R.id.imageView5);


        txt_question = (TextView) dialog.findViewById(R.id.text_question);
        rtime = (EditText) dialog.getWindow().findViewById(R.id.rtime);
        edtRemark = (EditText) dialog.getWindow().findViewById(R.id.comment_text);
        simpleSwitch = (Switch) dialog.getWindow().findViewById(R.id.simpleSwitch);
        simpleSwitch.setTextOn("yes"); // displayed text of the Switch whenever it is in checked or on state
        simpleSwitch.setTextOff("no");

        dialog.show(); //to show dialog box


        index = position;

        txt_question.setText(position + 1 + ") " + quesList.get(position).getQuestion());

//
//        simpleSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                mstatus = isChecked ? "Y" : "N";
//
//            }
//        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ProofActivity.this, CameraActivity.class);
                intent.putExtra("suid", "eviede");
                intent.putExtra("folder", "candidate");
                intent.putExtra("setCode", "3");

                startActivityForResult(intent, 3);


            }
        });




        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = 0;
                dialog.dismiss();

                callAdapterMethod();


            }
        });
        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /////////////////////////////////////////////////////////////
                int countImage = Integer.parseInt(quesList.get(position).getQuantity());
                count = db.uploadDao().getAllImage(position).size();
                if (quesList.get(position).getType().equals("1")) {


                    if (count < countImage) {
                        Intent intent = new Intent(ProofActivity.this, CameraActivity.class);
                        intent.putExtra("folderName", "AuditImages");
                        intent.putExtra("setCode", "3");
                        startActivityForResult(intent, 3);
                    } else {
                        Toast.makeText(ProofActivity.this, "Only " + countImage + " image is capture", Toast.LENGTH_SHORT).show();
                    }
                } else if (quesList.get(position).getType().equals("2")) {
                    if (count < countImage) {
                        Intent intent = new Intent(ProofActivity.this, VideoRecordActivity.class);
                        intent.putExtra("folderName", "Video");
                        intent.putExtra("setCode", "2");
                        startActivityForResult(intent, 3);
                    } else {
                        Toast.makeText(ProofActivity.this, "Only " + countImage + " video is recording", Toast.LENGTH_SHORT).show();

                    }


                }


            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
try {

    count++;


    String message = data.getStringExtra("MESSAGE");
    filepath = message;
    filename = data.getStringExtra("FIle_Name");

    List<LoginTable> listLogin = db.loginDAO().getAll();

    UploadTable uploadTable = new UploadTable();
    uploadTable.setImageUrl(filepath);
    uploadTable.setPosition(index);
    uploadTable.setAssessor_id(listLogin.get(0).getAssessorId());
    uploadTable.setBatch_id(batchId);
    uploadTable.setQuestion_id(quesList.get(index).getIdX());
    uploadTable.setParent_question_id(quesList.get(index).getParent_qid());
    db.uploadDao().insertAll(uploadTable);

    setPic(filepath);

    Log.e("types", "3");
}
catch (Exception e)
{
    e.printStackTrace();
}

    }

    private void setPic(String filepath2) {
        try {


            if(quesList.get(index).getType().equals("2")) {

                Log.e("cunt", count + "" + "---");
                if (count == 1) {
                    image1.setVisibility(View.VISIBLE);
                    image1.setImageResource(R.drawable.video_icon);
                    imageAnimation(image1);
                    filepath1 = this.filepath;
                }

                if (count == 2) {
                    image2.setVisibility(View.VISIBLE);
                    image2.setImageResource(R.drawable.video_icon);
                    imageAnimation(image2);
                    filepath2 = this.filepath;

                }
                if (count == 3) {
                    image3.setVisibility(View.VISIBLE);
                    image3.setImageResource(R.drawable.video_icon);
                    imageAnimation(image3);
                    filepath3 = this.filepath;

                }
                if (count == 4) {
                    image4.setVisibility(View.VISIBLE);
                    image4.setImageResource(R.drawable.video_icon);
                    imageAnimation(image4);
                    filepath4 = this.filepath;

                }
                if (count == 5) {
                    image5.setVisibility(View.VISIBLE);
                    image5.setImageResource(R.drawable.video_icon);
                    imageAnimation(image5);
                    filepath4 = this.filepath;

                }

            }

            // Get the dimensions of the View maine check kiya tha pr kch dikha nhi aisa
            int targetW = image1.getWidth();
            int targetH = image1.getHeight();

            // Get the dimensions of the bitmap
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(filepath2, bmOptions);
            int photoW = bmOptions.outWidth;
            int photoH = bmOptions.outHeight;

            // Determine how much to scale down the image
            int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

            // Decode the image file into a Bitmap sized to fill the View
            bmOptions.inJustDecodeBounds = false;
            bmOptions.inSampleSize = scaleFactor;

            Bitmap bitmap = BitmapFactory.decodeFile(filepath2, bmOptions);
            Bitmap scaledbitmap = getResizedBitmap(bitmap, 320);
            FileOutputStream out = null;
            try {
                out = new FileOutputStream(filepath2);
            } catch (FileNotFoundException e) {
                Log.e("TAG", "ERROR writing to image file!", e);
            }
            scaledbitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            BitmapDrawable ob = new BitmapDrawable(getResources(), bitmap);


            Log.e("type", quesList.get(index).getType());
            if (quesList.get(index).getType().equals("1")) {

                if (count == 1) {
                    image1.setVisibility(View.VISIBLE);
                    image1.setBackgroundDrawable(ob);
                    imageAnimation(image1);
                    filepath1 = this.filepath;
                }

                if (count == 2) {
                    image2.setVisibility(View.VISIBLE);
                    image2.setBackgroundDrawable(ob);
                    imageAnimation(image2);
                    filepath2 = this.filepath;

                }
                if (count == 3) {
                    image3.setVisibility(View.VISIBLE);
                    image3.setBackgroundDrawable(ob);
                    imageAnimation(image3);
                    filepath3 = this.filepath;

                }
                if (count == 4) {
                    image4.setVisibility(View.VISIBLE);
                    image4.setBackgroundDrawable(ob);
                    imageAnimation(image4);
                    filepath4 = this.filepath;

                }
                if (count == 5) {
                    image5.setVisibility(View.VISIBLE);
                    image5.setBackgroundDrawable(ob);
                    imageAnimation(image5);
                    filepath4 = this.filepath;

                }
            }
        } catch (Exception e) {
        }
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

    public void imageAnimation(ImageView iv) {

        Animation animation = AnimationUtils.loadAnimation(ProofActivity.this, R.anim.right_to_left);
        iv.setAnimation(animation);

    }


    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = ProofActivity.this.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(ProofActivity.this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
