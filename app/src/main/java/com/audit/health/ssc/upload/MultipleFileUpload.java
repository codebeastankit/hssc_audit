package com.audit.health.ssc.upload;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.audit.health.ssc.Activity.ThankYouActivity;
import com.audit.health.ssc.database.MyDatabase;
import com.audit.health.ssc.database.QuestionListTable;
import com.audit.health.ssc.database.UploadTable;

import static com.audit.health.ssc.utils.Constant.serverurl;

public class MultipleFileUpload {
    Context context;
    //    String auditorId, audit_id, qid;
    List<UploadTable> listUpload;
    private List<QuestionListTable> listQues;
    private ArrayList<File> listFile = new ArrayList<>();
    private int check = 0;
    private ProgressDialog progressDialog;
    private String batchId;

    public MultipleFileUpload(Context adapterContext, String batchI) {
        this.context = adapterContext;
        this.batchId = batchI;


        listUpload = MyDatabase.dataBase(context).uploadDao().getAll();
        if (listUpload.size() > 0) {

        } else {
            Toast.makeText(adapterContext, "Please carefully attempt all questions", Toast.LENGTH_SHORT).show();
            return;
        }


        listQues = MyDatabase.dataBase(context).questionListDao().getAll();


        for (int count = 0; count < listQues.size(); count++) {
            List<UploadTable> listImage = MyDatabase.dataBase(context).uploadDao().getAllImage(count);
            listFile.clear();
            for (int c = 0; c < listImage.size(); c++) {
                listFile.add(new File(listImage.get(c).getImageUrl()));
            }
            uploadFileServer(listQues.get(count).getIdX(), listQues.get(count).getParent_qid());
        }

        Intent intent = new Intent(context, ThankYouActivity.class);
        intent.putExtra("batchId",batchId);
        context.startActivity(intent);
        ((Activity) context).finish();


    }

    private void uploadFileServer(String quesID, String parentID) {
        check++;

//        progressDialog = new ProgressDialog(context);
//        progressDialog.setMessage("please wait");
//        progressDialog.show();

        AndroidNetworking.upload(serverurl + "question/upload")
                .addMultipartFileList("evidence_file[]", listFile)
                .addMultipartParameter("assessor_id", listUpload.get(0).getAssessor_id())
                .addMultipartParameter("question_id", quesID)
                .addMultipartParameter("parent_question_id", parentID)
                .addMultipartParameter("batch_id", batchId)
                .setPriority(Priority.HIGH)
                .build()
                .setUploadProgressListener(new UploadProgressListener() {
                    @Override
                    public void onProgress(long bytesUploaded, long totalBytes) {
                        // do anything with progress
                    }
                })
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("res", response.toString());
//                        progressDialog.dismiss();


                    }

                    @Override
                    public void onError(ANError error) {
                        Log.e("res", error.getResponse().toString());
//                        progressDialog.dismiss();
                    }
                });
    }

}
