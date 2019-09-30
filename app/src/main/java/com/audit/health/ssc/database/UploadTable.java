package com.audit.health.ssc.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class UploadTable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private int position;
    private String assessor_id="";
    private String question_id="";
    private String parent_question_id="";
    private String batch_id="";
    private String status="";
    private String imageUrl = "";

    private String imageur1;
    private String imageur2;
    private String imageur3;
    private String imageur4;
    private String imageur5;

public UploadTable(){}

    @Override
    public String toString() {
        return "UploadTable{" +
                "id=" + id +
                ", position=" + position +
                ", assessor_id='" + assessor_id + '\'' +
                ", question_id='" + question_id + '\'' +
                ", parent_question_id='" + parent_question_id + '\'' +
                ", batch_id='" + batch_id + '\'' +
                ", status='" + status + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", imageur1='" + imageur1 + '\'' +
                ", imageur2='" + imageur2 + '\'' +
                ", imageur3='" + imageur3 + '\'' +
                ", imageur4='" + imageur4 + '\'' +
                ", imageur5='" + imageur5 + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getAssessor_id() {
        return assessor_id;
    }

    public void setAssessor_id(String assessor_id) {
        this.assessor_id = assessor_id;
    }

    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public String getParent_question_id() {
        return parent_question_id;
    }

    public void setParent_question_id(String parent_question_id) {
        this.parent_question_id = parent_question_id;
    }

    public String getBatch_id() {
        return batch_id;
    }

    public void setBatch_id(String batch_id) {
        this.batch_id = batch_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageur1() {
        return imageur1;
    }

    public void setImageur1(String imageur1) {
        this.imageur1 = imageur1;
    }

    public String getImageur2() {
        return imageur2;
    }

    public void setImageur2(String imageur2) {
        this.imageur2 = imageur2;
    }

    public String getImageur3() {
        return imageur3;
    }

    public void setImageur3(String imageur3) {
        this.imageur3 = imageur3;
    }

    public String getImageur4() {
        return imageur4;
    }

    public void setImageur4(String imageur4) {
        this.imageur4 = imageur4;
    }

    public String getImageur5() {
        return imageur5;
    }

    public void setImageur5(String imageur5) {
        this.imageur5 = imageur5;
    }
}
