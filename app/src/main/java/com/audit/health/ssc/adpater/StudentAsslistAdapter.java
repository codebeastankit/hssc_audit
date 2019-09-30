package com.audit.health.ssc.adpater;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import com.audit.health.ssc.Activity.ProofActivity;
import com.audit.health.ssc.R;
import com.audit.health.ssc.database.BatchTable;
import com.audit.health.ssc.database.MyDatabase;


public class StudentAsslistAdapter extends BaseAdapter {
    List<BatchTable> list;
    Context adapterContext;
    private int resource;
    private LayoutInflater inflater;
    private List<BatchTable> batchList;

    public StudentAsslistAdapter(Context context, List<BatchTable> pendigList, int fragment_pending_item) {
        this.list = pendigList;
        this.adapterContext = context;
        this.resource = fragment_pending_item;
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

        TextView batchName = v.findViewById(R.id.batch_id);
        TextView startDate = v.findViewById(R.id.batch_start_date);
        TextView endDate = v.findViewById(R.id.batchEnddate);
        TextView batchQp = v.findViewById(R.id.batchQp);

        batchQp.setText(list.get(position).getQualification_package());
        final Button btn_attempt = v.findViewById(R.id.btn_attempt);

        batchName.setText(list.get(position).getBatch_name());

        try {
            String[] starD = list.get(position).getBatch_start_date().split(" ", 2);
            String[] endD = list.get(position).getBatch_end_date().split(" ", 2);
            startDate.setText(starD[0] + "");
            endDate.setText(endD[0] + "");
        } catch (Exception e) {
            e.printStackTrace();
        }

        batchList = MyDatabase.dataBase(adapterContext).batchDao().getAll();
        if (batchList.get(position).getStatus().equals("1")) {
            btn_attempt.setText("Attempted");
        } else {
            btn_attempt.setText("Attempt");

        }

        btn_attempt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (btn_attempt.getText().toString().equals("Attempted")) {
                    Toast.makeText(adapterContext, "Already Attempted", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(adapterContext, ProofActivity.class);
                    intent.putExtra("batchId",batchList.get(position).getBatch_id());
                    adapterContext.startActivity(intent);
                }


            }
        });

        return v;
    }
}