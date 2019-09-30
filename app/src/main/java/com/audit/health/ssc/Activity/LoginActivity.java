package com.audit.health.ssc.Activity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.ArrayList;
import java.util.List;

import com.audit.health.ssc.BuildConfig;
import com.audit.health.ssc.R;
import com.audit.health.ssc.database.BatchTable;
import com.audit.health.ssc.database.LoginTable;
import com.audit.health.ssc.database.MyDatabase;
import com.audit.health.ssc.database.QuestionListTable;
import com.audit.health.ssc.utils.ConnectionChecker;
import com.audit.health.ssc.utils.Constant;
import com.audit.health.ssc.utils.UtilityClass;
import com.audit.health.ssc.webservices.ApiClient;
import com.audit.health.ssc.webservices.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    String enteredUsername = "";
    String enteredPassword = "";
    EditText user, pass;
    CheckBox chkShowPass;
    private MyDatabase db;
    private boolean isCheck = false;
    private String versionName;
    private String userName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        chkShowPass = findViewById(R.id.check_showPass);
        versionName = BuildConfig.VERSION_NAME;

        if (Build.VERSION.SDK_INT >= 23) {
            Dexter.initialize(this);
            checkPermissions();
        }

//

//        user.setText("ST191911");
//        pass.setText("sf0yps");
        user.setText("ST261922");
        pass.setText("12345");

        findViewById(R.id.login_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enteredUsername = user.getText().toString().trim().toUpperCase();
                enteredPassword = pass.getText().toString().trim().toLowerCase();

                db = MyDatabase.dataBase(LoginActivity.this);

                List<LoginTable> loginTables = db.loginDAO().getAuditorUsername(enteredUsername);

                if (loginTables.size() > 0)
                    userName = loginTables.get(0).getUsername();

                if (enteredPassword.isEmpty() || enteredUsername.isEmpty()) {
                    isCheck = true;
                } else {
                    Toast.makeText(LoginActivity.this, "please enter username and password", Toast.LENGTH_SHORT).show();
                }


                if (loginTables.size()>0) {

                    if (loginTables.get(0).getUsername().equals(enteredUsername)) {
                        Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid USERNAME & PASSWORD", Toast.LENGTH_LONG).show();
                    }
                } else{ if (ConnectionChecker.isConnectingToInternet(LoginActivity.this)) {
                        getServerRequest(user.getText().toString().trim(), pass.getText().toString().trim());
                    }
                    else {
                        Toast.makeText(LoginActivity.this, Constant.CHECK_NETWORK_MESSAGE, Toast.LENGTH_LONG).show();

                    }

                }
            }
        });
        chkShowPass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (!isChecked) {
                    pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

    }

    private void getServerRequest(String userName, String password) {

        UtilityClass.showProgress(this, "please wait...", "");

        Call<LoginTable> call1 = ApiClient.getClient().create(ApiInterface.class).loginUser("login/assessor/" +
                userName + "/" + password);

        call1.enqueue(new Callback<LoginTable>() {
            @Override
            public void onResponse(Call<LoginTable> call, Response<LoginTable> response) {

                final LoginTable loginResponse = response.body();
                MyDatabase db = MyDatabase.dataBase(LoginActivity.this);

                Log.e("res", response.toString());

                List<LoginTable> loginTableList = new ArrayList<>();

                loginTableList.clear();

                LoginTable loginTable = new LoginTable();
                try {
                    loginTable.setAssessorId(loginResponse.getAssessorId());
                    loginTable.setName(loginResponse.getName());
                    loginTable.setUsername(loginResponse.getUsername());
                    loginTable.setSu(loginResponse.getSu());
                    loginTable.setEmail(loginResponse.getEmail());
                    loginTable.setExam_type(loginResponse.getExam_type());

                    loginTableList.add(loginTable);

                    db.loginDAO().insertAll(loginTableList);

                    UtilityClass.logger("check", db.loginDAO().getAll() + "");

                    UtilityClass.dismissProgress(LoginActivity.this);
                    Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                    startActivity(intent);
                }
                catch (Exception e) {
                    UtilityClass.dismissProgress(LoginActivity.this);

                }

            }

            @Override
            public void onFailure(Call<LoginTable> call, Throwable t) {
                UtilityClass.dismissProgress(LoginActivity.this);

            }
        });
    }

    private void checkPermissions() {
        Dexter.checkPermissions(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {
                if (report.areAllPermissionsGranted()) {

                }
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                token.continuePermissionRequest();

            }
        }, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO, Manifest.permission.ACCESS_FINE_LOCATION);
    }


}
