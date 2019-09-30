package com.audit.health.ssc.adpater;


import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import com.audit.health.ssc.R;
import com.audit.health.ssc.database.MyDatabase;
import com.audit.health.ssc.database.QuestionListTable;
import com.audit.health.ssc.database.UploadTable;


public class ProofAdapter extends RecyclerView.Adapter<ProofAdapter.FeedbackViewHolder> {
    List<QuestionListTable> answerlist;
    String uid;
    Context adapterContext;
    private ItemClickListner itemClickListner;

    public ProofAdapter(Context context, List<QuestionListTable> quesList) {
        this.answerlist = quesList;
        this.adapterContext = context;

    }

    @NonNull
    @Override
    public FeedbackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(adapterContext).inflate(R.layout.proof_item_layout, parent, false);
        return new FeedbackViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedbackViewHolder holder, final int position) {
            holder.tv_ques.setText(position + 1 + ") " + answerlist.get(position).getQuestion());

            int countImage = Integer.parseInt(answerlist.get(position).getQuantity());

            int count = MyDatabase.dataBase(adapterContext).uploadDao().getAllImage(position).size();

            int show=countImage-count;


            List<UploadTable> listIma = MyDatabase.dataBase(adapterContext).uploadDao().getAllImage(position);


            for (int c = 0; c < listIma.size(); c++) {
                String filepath = listIma.get(c).getImageUrl();
                if (answerlist.get(position).getType().equals("1")) {
                    holder.tv_ques_no.setText(show + " " + " image remaining");

                    if (c == 0) {
                        holder.imageView1.setImageURI(Uri.parse(filepath));
                    }
                    if (c == 1) {
                        holder.imageView2.setImageURI(Uri.parse(filepath));
                    }
                    if (c == 2) {
                        holder.imageView3.setImageURI(Uri.parse(filepath));

                    }
                    if (c == 3) {
                        holder.imageView4.setImageURI(Uri.parse(filepath));
                    }
                    if (c == 4) {
                        holder.imageView5.setImageURI(Uri.parse(filepath));

                    }
                } else {
                    holder.tv_ques_no.setText(show + " " + " video remaining");

                    if (c == 0) {
                        holder.imageView1.setBackgroundResource(R.drawable.video_icon);
                    }
                    if (c == 1) {
                        holder.imageView2.setBackgroundResource(R.drawable.video_icon);
                    }
                    if (c == 2) {
                        holder.imageView3.setBackgroundResource(R.drawable.video_icon);

                    }
                    if (c == 3) {
                        holder.imageView4.setBackgroundResource(R.drawable.video_icon);
                    }
                    if (c == 4) {
                        holder.imageView5.setBackgroundResource(R.drawable.video_icon);

                    }

                }
            }



        }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    @Override
    public int getItemCount() {
        return answerlist.size();
    }

    public void setItemClickListner(ItemClickListner itemClickListner) {
        this.itemClickListner = itemClickListner;
    }

    public interface ItemClickListner {

        void itemClickPos(int pos);
    }

    public class FeedbackViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_ques,tv_ques_no;
        ImageView imageView1, imageView2, imageView3, imageView4, imageView5;
        LinearLayout linearLayout;

        public FeedbackViewHolder(View itemView) {
            super(itemView);
            tv_ques = itemView.findViewById(R.id.txt_ques);
            tv_ques_no = itemView.findViewById(R.id.txt_ques_no);
            imageView1 = itemView.findViewById(R.id.imageView1);
            imageView2 = itemView.findViewById(R.id.imageView2);
            imageView3 = itemView.findViewById(R.id.imageView3);
            imageView4 = itemView.findViewById(R.id.imageView4);
            imageView5 = itemView.findViewById(R.id.imageView5);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemClickListner.itemClickPos(getAdapterPosition());
        }
    }


}
