package com.audit.health.ssc.webservices.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UploadRequest {


    /**
     * audit_id : 1
     * auditor_id : 4
     * category : 4
     * question_id : 1
     * answer : my answer
     *   {
     *
     *             "master_category":"1",
     *             "vtp_type":"non_nsdc",
     *
     *
     *     		"score":"1"
     *     	"remarks":"ABC"
     *     	"latitude":"2.254"
     *     	"longitude":"3.354"
     *     	"answer_time":"Time"
     *
     *
     *         }
     */

    @SerializedName("master_category")
    @Expose
    private String master_category;

    @SerializedName("industry_id")
    @Expose
    private String industry_id;

    public String getIndustry_id() {
        return industry_id;
    }

    public void setIndustry_id(String industry_id) {
        this.industry_id = industry_id;
    }

    @SerializedName("remarks")
    @Expose
    private String remarks;

    @SerializedName("latitude")
    @Expose
    private String latitude;

    @SerializedName("longitude")
    @Expose
    private String longitude;

    @SerializedName("answer_time")
    @Expose
    private String answer_time;

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAnswer_time() {
        return answer_time;
    }

    public void setAnswer_time(String answer_time) {
        this.answer_time = answer_time;
    }

    @SerializedName("vtp_type")
    @Expose
    private String vtp_type;

    public String getMaster_category() {
        return master_category;
    }

    public void setMaster_category(String master_category) {
        this.master_category = master_category;
    }

    public String getVtp_type() {
        return vtp_type;
    }

    public void setVtp_type(String vtp_type) {
        this.vtp_type = vtp_type;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @SerializedName("score")
    @Expose
    private String score;

    @SerializedName("audit_id")
    @Expose
    private String audit_id;
    @SerializedName("auditor_id")
    @Expose

    private String auditor_id;

    @SerializedName("category")
    @Expose

    private String category;

    @SerializedName("question_id")
    @Expose

    private String question_id;

    @SerializedName("answer")
    @Expose

    private String answer;

    @SerializedName("max_score")
    @Expose

    private String max_score;

    public String getMax_score() {
        return max_score;
    }

    public void setMax_score(String max_score) {
        this.max_score = max_score;
    }

    public String getAudit_id() {
        return audit_id;
    }

    public void setAudit_id(String audit_id) {
        this.audit_id = audit_id;
    }

    public String getAuditor_id() {
        return auditor_id;
    }

    public void setAuditor_id(String auditor_id) {
        this.auditor_id = auditor_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}
