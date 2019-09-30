package com.audit.health.ssc.webservices.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UploadJobrole {

    /**
     * jobrole_id : 1
     * lab_size : 300
     * lab_plus_classroom : 1
     * additional_training : NO
     * required_additional_training : NO
     * additional_training_area_size : 0
     * it_lab : YES
     */
    @SerializedName("jobrole_id")
    @Expose
    private String jobrole_id;
    @SerializedName("lab_size")
    @Expose
    private String lab_size;
    @SerializedName("lab_plus_classroom")
    @Expose
    private String lab_plus_classroom;
    @SerializedName("additional_training")
    @Expose
    private String additional_training;

    @SerializedName("required_additional_training")
    @Expose
    private String required_additional_training;
    @SerializedName("additional_training_area_size")
    @Expose
    private String additional_training_area_size;
    @SerializedName("it_lab")
    @Expose
    private String it_lab;

    public String getJobrole_id() {
        return jobrole_id;
    }

    public void setJobrole_id(String jobrole_id) {
        this.jobrole_id = jobrole_id;
    }

    public String getLab_size() {
        return lab_size;
    }

    public void setLab_size(String lab_size) {
        this.lab_size = lab_size;
    }

    public String getLab_plus_classroom() {
        return lab_plus_classroom;
    }

    public void setLab_plus_classroom(String lab_plus_classroom) {
        this.lab_plus_classroom = lab_plus_classroom;
    }

    public String getAdditional_training(String additional_training) {
        return this.additional_training;
    }

    public void setAdditional_training(String additional_training) {
        this.additional_training = additional_training;
    }

    public String getRequired_additional_training(String required_additional_training) {
        return this.required_additional_training;
    }

    public void setRequired_additional_training(String required_additional_training) {
        this.required_additional_training = required_additional_training;
    }

    public String getAdditional_training_area_size() {
        return additional_training_area_size;
    }

    public void setAdditional_training_area_size(String additional_training_area_size) {
        this.additional_training_area_size = additional_training_area_size;
    }

    public String getIt_lab() {
        return it_lab;
    }

    public void setIt_lab(String it_lab) {
        this.it_lab = it_lab;
    }
}
