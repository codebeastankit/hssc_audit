package com.audit.health.ssc.webservices.response;

import java.util.List;

public class Questionare {

    private List<QuestionsBean> questions;

    public List<QuestionsBean> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionsBean> questions) {
        this.questions = questions;
    }

    public static class QuestionsBean {
        /**
         * id : 1
         * audit_type : My Audit
         * Industry : 4
         * AuditCategory : 1
         * Company : 1
         * question : Biometric Attendance Equipment
         * category : MCQ
         * OptionA : True
         * OptionB : False
         * Answer : True
         * created_at : 2018-09-27 07:41:13
         * industry_name : Assessment
         * audit_cagtegory : Account
         * company_name : GLOCAL THINKERS PVT LTD
         */

        private String id;
        private String audit_type;
        private String Industry;
        private String AuditCategory;
        private String Company;
        private String question;
        private String category;
        private String OptionA;
        private String OptionB;
        private String OptionC;
        private String OptionD;
        private String OptionE;
        private String OptionF;
        private String Answer;
        private String created_at;
        private String industry_name;
        private String audit_cagtegory;
        private String company_name;
        private String question_category;

        public String getQuestion_category() {
            return question_category;
        }

        public void setQuestion_category(String question_category) {
            this.question_category = question_category;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAudit_type() {
            return audit_type;
        }

        public void setAudit_type(String audit_type) {
            this.audit_type = audit_type;
        }

        public String getIndustry() {
            return Industry;
        }

        public void setIndustry(String Industry) {
            this.Industry = Industry;
        }

        public String getAuditCategory() {
            return AuditCategory;
        }

        public void setAuditCategory(String AuditCategory) {
            this.AuditCategory = AuditCategory;
        }

        public String getCompany() {
            return Company;
        }

        public void setCompany(String Company) {
            this.Company = Company;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getOptionA() {
            return OptionA;
        }

        public void setOptionA(String OptionA) {
            this.OptionA = OptionA;
        }

        public String getOptionB() {
            return OptionB;
        }

        public void setOptionB(String OptionB) {
            this.OptionB = OptionB;
        }

        public String getOptionC() {
            return OptionC;
        }

        public void setOptionC(String optionC) {
            OptionC = optionC;
        }

        public String getOptionD() {
            return OptionD;
        }

        public void setOptionD(String optionD) {
            OptionD = optionD;
        }

        public String getOptionE() {
            return OptionE;
        }

        public void setOptionE(String optionE) {
            OptionE = optionE;
        }

        public String getOptionF() {
            return OptionF;
        }

        public void setOptionF(String optionF) {
            OptionF = optionF;
        }

        public String getAnswer() {
            return Answer;
        }

        public void setAnswer(String Answer) {
            this.Answer = Answer;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getIndustry_name() {
            return industry_name;
        }

        public void setIndustry_name(String industry_name) {
            this.industry_name = industry_name;
        }

        public String getAudit_cagtegory() {
            return audit_cagtegory;
        }

        public void setAudit_cagtegory(String audit_cagtegory) {
            this.audit_cagtegory = audit_cagtegory;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }
    }
}
