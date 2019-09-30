package com.audit.health.ssc.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class BatchTable {


    /**
     * batch_id : 20
     * batch_name : 1902TS00F62JHSS/Q5102-00034B2D
     * batch_start_date : 2019-07-13 00:00:00
     * batch_end_date : 2019-09-03 00:00:00
     * qualification_package : Home Health Aide
     */

    private String batch_id;
    private String batch_name;
    private String batch_start_date;
    private String batch_end_date;
    private String qualification_package;
    private String status = "0";
    @PrimaryKey(autoGenerate = true)
    private int id;

    public BatchTable() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BatchTable{" +
                "batch_id='" + batch_id + '\'' +
                ", batch_name='" + batch_name + '\'' +
                ", batch_start_date='" + batch_start_date + '\'' +
                ", batch_end_date='" + batch_end_date + '\'' +
                ", qualification_package='" + qualification_package + '\'' +
                ", status='" + status + '\'' +
                ", id=" + id +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBatch_id() {
        return batch_id;
    }

    public void setBatch_id(String batch_id) {
        this.batch_id = batch_id;
    }

    public String getBatch_name() {
        return batch_name;
    }

    public void setBatch_name(String batch_name) {
        this.batch_name = batch_name;
    }

    public String getBatch_start_date() {
        return batch_start_date;
    }

    public void setBatch_start_date(String batch_start_date) {
        this.batch_start_date = batch_start_date;
    }

    public String getBatch_end_date() {
        return batch_end_date;
    }

    public void setBatch_end_date(String batch_end_date) {
        this.batch_end_date = batch_end_date;
    }

    public String getQualification_package() {
        return qualification_package;
    }

    public void setQualification_package(String qualification_package) {
        this.qualification_package = qualification_package;
    }
}
