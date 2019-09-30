package com.audit.health.ssc.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.List;

import com.audit.health.ssc.R;
import com.audit.health.ssc.database.BatchTable;
import com.audit.health.ssc.database.MyDatabase;

public class ThankYouActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_aadhaar_card);

        String batchId = getIntent().getStringExtra("batchId");

        List<BatchTable> batchTable = MyDatabase.dataBase(this).batchDao().getAll();
        MyDatabase.dataBase(this).uploadDao().deleteUpload(batchId);

        MyDatabase.dataBase(this).batchDao().updateStatus(batchId, "1");


    }

    public void showHome(View view) {

        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
        finish();


    }
}
