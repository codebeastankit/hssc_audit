package com.audit.health.ssc.webservices.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UploadEquipment {

    /**
     * equipment_id : 21
     * jobrole_id : 1
     * unit_type :
     * equipment_available_center : YES
     * equipment_description : Unit = 3 D Model
     * self_declaration :
     * inspection_evaluation :
     * remarks :
     */

    @SerializedName("equipment_id")
    @Expose
    private String equipment_id;
    @SerializedName("jobrole_id")
    @Expose
    private String jobrole_id;
    @SerializedName("unit_type")
    @Expose
    private String unit_type;
    @SerializedName("equipment_available_center")
    @Expose
    private String equipment_available_center;
    @SerializedName("equipment_description")
    @Expose
    private String equipment_description;
    @SerializedName("self_declaration")
    @Expose
    private String self_declaration;
    @SerializedName("inspection_evaluation")
    @Expose
    private String inspection_evaluation;
    @SerializedName("remarks")
    @Expose
    private String remarks;
    @SerializedName("auditor_id")
    @Expose
    private String auditor_id;
    @SerializedName("industry_id")
    @Expose
    private String industry_id;

    public String getAudit_id() {
        return audit_id;
    }

    public void setAudit_id(String audit_id) {
        this.audit_id = audit_id;
    }

    @SerializedName("audit_id")
    @Expose
    private String audit_id;
    @SerializedName("audit_center")
    @Expose
    private String audit_center;

    public String getAuditor_id() {
        return auditor_id;
    }

    public void setAuditor_id(String auditor_id) {
        this.auditor_id = auditor_id;
    }

    public String getIndustry_id() {
        return industry_id;
    }

    public void setIndustry_id(String industry_id) {
        this.industry_id = industry_id;
    }

    public String getAudit_center() {
        return audit_center;
    }

    public void setAudit_center(String audit_center) {
        this.audit_center = audit_center;
    }

    public String getEquipment_id() {
        return equipment_id;
    }

    public void setEquipment_id(String equipment_id) {
        this.equipment_id = equipment_id;
    }

    public String getJobrole_id() {
        return jobrole_id;
    }

    public void setJobrole_id(String jobrole_id) {
        this.jobrole_id = jobrole_id;
    }

    public String getUnit_type() {
        return unit_type;
    }

    public void setUnit_type(String unit_type) {
        this.unit_type = unit_type;
    }

    public String getEquipment_available_center() {
        return equipment_available_center;
    }

    public void setEquipment_available_center(String equipment_available_center) {
        this.equipment_available_center = equipment_available_center;
    }

    public String getEquipment_description() {
        return equipment_description;
    }

    public void setEquipment_description(String equipment_description) {
        this.equipment_description = equipment_description;
    }

    public String getSelf_declaration() {
        return self_declaration;
    }

    public void setSelf_declaration(String self_declaration) {
        this.self_declaration = self_declaration;
    }

    public String getInspection_evaluation() {
        return inspection_evaluation;
    }

    public void setInspection_evaluation(String inspection_evaluation) {
        this.inspection_evaluation = inspection_evaluation;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
