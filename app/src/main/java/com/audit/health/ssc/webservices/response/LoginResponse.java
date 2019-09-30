package com.audit.health.ssc.webservices.response;

public class LoginResponse {


    /**
     * username : praveen
     * industry : Life Sciences Sector Skill Development Council
     */

    private String username;
    private String industry;

    public String getCenter_id() {
        return center_id;
    }

    public void setCenter_id(String center_id) {
        this.center_id = center_id;
    }

    private String center_id;

    /**
     * email : nilesh@glocalthinkers.com
     * password : nileshk01
     * status : true
     * id : 1
     * name : nilesh kuma
     * phone : 8689036600
     * address : india haryana
     * type : Auditor
     * auditor_type : 1
     * audit_center : GLOCAL THINKERS PVT LTD
     * company : GLOCAL THINKERS PVT LTD
     * audit_name : My Audit
     * start_date : 2018-09-28 00:00:00
     * end_date : 2018-09-30 00:00:00
     * audit_id : 4
     * user_status : 1
     */



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String email;
    private String password;
    private String status;
    private String id;
    private String name;
    private String phone;
    private String address;
    private String type;
    private String auditor_type;
    private String audit_center;
    private String company;
    private String audit_name;
    private String start_date;
    private String end_date;
    private String audit_id;
    private String ssc_id;
    private String user_status;
    private String message;
    private String qp_id;

    public String getQp_id() {
        return qp_id;
    }

    public void setQp_id(String qp_id) {
        this.qp_id = qp_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSsc_id() {
        return ssc_id;
    }

    public void setSsc_id(String ssc_id) {
        this.ssc_id = ssc_id;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAuditor_type() {
        return auditor_type;
    }

    public void setAuditor_type(String auditor_type) {
        this.auditor_type = auditor_type;
    }

    public String getAudit_center() {
        return audit_center;
    }

    public void setAudit_center(String audit_center) {
        this.audit_center = audit_center;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAudit_name() {
        return audit_name;
    }

    public void setAudit_name(String audit_name) {
        this.audit_name = audit_name;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getAudit_id() {
        return audit_id;
    }

    public void setAudit_id(String audit_id) {
        this.audit_id = audit_id;
    }

    public String getUser_status() {
        return user_status;
    }

    public void setUser_status(String user_status) {
        this.user_status = user_status;
    }

    public LoginResponse(){

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }
}
