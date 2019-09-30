package com.audit.health.ssc.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class QuestionListTable {


    @PrimaryKey(autoGenerate = true)
    private int userId;

    private String batchId="";

    public QuestionListTable(){}

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    /**
     * id : 16
     * parent_qid : 3
     * question : 5 theory images of assessment?
     * type : 1
     * quantity : 5
     * status : 1
     */


    @SerializedName("id")
    private String idX;
    private String parent_qid;
    private String question;
    private String type;
    private String quantity;
    private String status;

    public String getIdX() {
        return idX;
    }

    public void setIdX(String idX) {
        this.idX = idX;
    }

    public String getParent_qid() {
        return parent_qid;
    }

    public void setParent_qid(String parent_qid) {
        this.parent_qid = parent_qid;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
