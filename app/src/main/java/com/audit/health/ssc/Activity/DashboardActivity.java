package com.audit.health.ssc.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.audit.health.ssc.R;
import com.audit.health.ssc.adpater.StudentAsslistAdapter;
import com.audit.health.ssc.database.BatchTable;
import com.audit.health.ssc.database.LoginTable;
import com.audit.health.ssc.database.MyDatabase;
import com.audit.health.ssc.database.QuestionListTable;
import com.audit.health.ssc.utils.ConnectionChecker;
import com.audit.health.ssc.utils.Constant;
import com.audit.health.ssc.webservices.ApiClient;
import com.audit.health.ssc.webservices.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity {
    public static final String AUDITOR_ID = "auditorID";
    ListView listView;
    TextView no_record;
    List<BatchTable> userList = new ArrayList<>();
    private MyDatabase db;
    private String batchid;
    private List<BatchTable> loginResponse;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        db = MyDatabase.dataBase(DashboardActivity.this);


        init();


    }

    private void init() {
        listView = (ListView) findViewById(R.id.assessment_list);
        no_record = (TextView) findViewById(R.id.noRecord);


        userList = db.batchDao().getAll();
        List<LoginTable> userLit = db.loginDAO().getAll();
        if (userList.size() > 0) {
            RefreshStudentAssessment(userList);
        } else {
            if (ConnectionChecker.isConnectingToInternet(DashboardActivity.this)) {


                getServerRequest(userLit.get(0).getAssessorId());
            } else {
                Toast.makeText(DashboardActivity.this, Constant.CHECK_NETWORK_MESSAGE, Toast.LENGTH_LONG).show();

            }
        }
    }

    private void RefreshStudentAssessment(List<BatchTable> batchTables) {
        StudentAsslistAdapter pendingdapter = new StudentAsslistAdapter(DashboardActivity.this, batchTables, R.layout.audit_attempt_view);
        listView.setAdapter(pendingdapter);
    }


    private void getServerRequest(String assessorId) {

        progressDialog = new ProgressDialog(DashboardActivity.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

//        UtilityClass.showProgress(this, "please wait...", "");

        Call<List<BatchTable>> call1 = ApiClient.getClient().create(ApiInterface.class).batchUser("batch/assessor_list/" +
                assessorId);

        call1.enqueue(new Callback<List<BatchTable>>() {
            @Override
            public void onResponse(Call<List<BatchTable>> call, Response<List<BatchTable>> response) {
                loginResponse = response.body();
                MyDatabase db = MyDatabase.dataBase(DashboardActivity.this);

                Log.e("res", loginResponse.toString());
//                List<BatchTable> batchTables = new ArrayList<>();

//                BatchTable loginTable = new BatchTable();
//                try {
//
//                    for (int count = 0; count < batchTables.size(); count++) {
//                        loginTable.setBatch_end_date(loginResponse.get(count).getBatch_end_date());
//                        loginTable.setBatch_id(loginResponse.get(count).getBatch_id());
//                        loginTable.setBatch_name(loginResponse.get(count).getBatch_name());
//                        loginTable.setBatch_start_date(loginResponse.get(count).getBatch_start_date());
//                        loginTable.setQualification_package(loginResponse.get(count).getQualification_package());
//                        batchTables.add(loginTable);
//                    }


                db.batchDao().insertAll(loginResponse);
                RefreshStudentAssessment(loginResponse);
                progressDialog.dismiss();

                // call question list api

                if (db.questionListDao().getAll().size() > 0) {

                } else {
                    getServerRequest();

                }

//                } catch (Exception e) {
//                    e.printStackTrace();
//                }

            }

            @Override
            public void onFailure(Call<List<BatchTable>> call, Throwable t) {
                progressDialog.dismiss();

                Log.e("res", t.getMessage());

            }
        });
    }


    private void logout() {
        new AlertDialog.Builder(DashboardActivity.this)
                .setTitle("Logout!!")
                .setMessage("Do you want to Logout from this app?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent logout = new Intent(DashboardActivity.this, DashboardActivity.class);
                        startActivity(logout);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            finishAffinity();
                        }
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }


    private void getServerRequest() {

        Call<List<QuestionListTable>> call1 = ApiClient.getClient().create(ApiInterface.class).questionUser("question/question_list");

        call1.enqueue(new Callback<List<QuestionListTable>>() {
            @Override
            public void onResponse(Call<List<QuestionListTable>> call, Response<List<QuestionListTable>> response) {
                Log.e("res", response.toString());


                List<QuestionListTable> listQues = response.body();


//                for (int c=0; c<listQues.size();c++){
//                    QuestionListTable qt= new QuestionListTable();
//                    qt.setBatchId(loginResponse.get(2));
//                    qt.setParent_qid();
//                    qt.setQuantity();
//                    qt.setType();
//                    qt.setStatus();
//                    qt.setUserId();
//
//                }

                db.questionListDao().insertAll(response.body());


            }

            @Override
            public void onFailure(Call<List<QuestionListTable>> call, Throwable t) {

                Log.e("res", t.getMessage());

            }
        });
    }


}
