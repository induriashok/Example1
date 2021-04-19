package com.joystays.joy.pojos;

public class UserProfilePojo {

    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : {"id":"1","name":"John Doe","email_id":"john@gmail.com","mobile":"9999999999","password":"e10adc3949ba59abbe56e057f20f883e","profile_pic":null,"languages":"Telugu,Hindi,English","dob":null,"age":"0","state":null,"guardian_name":"","jon_details":null,"purpose":null,"permanent_address":null,"aadhar_card":null,"aadhar_card_number":"0","bed_confirmed":"no","present_booking_id":"1","token":"dfsdf4df46f4df4d634df4df1d6f4df1d6f4d3f1d6f4d6","ios_token":"","status":"1","delete_status":"1","created_on":"2019-10-16 12:09:56","modified_on":"2019-10-16 12:27:09"}
     */

    private boolean status;
    private String message;
    private ResponseBean response;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseBean getResponse() {
        return response;
    }

    public void setResponse(ResponseBean response) {
        this.response = response;
    }

    public static class ResponseBean {
        /**
         * id : 1
         * name : John Doe
         * email_id : john@gmail.com
         * mobile : 9999999999
         * password : e10adc3949ba59abbe56e057f20f883e
         * profile_pic : null
         * languages : Telugu,Hindi,English
         * dob : null
         * age : 0
         * state : null
         * guardian_name :
         * jon_details : null
         * purpose : null
         * permanent_address : null
         * aadhar_card : null
         * aadhar_card_number : 0
         * bed_confirmed : no
         * present_booking_id : 1
         * token : dfsdf4df46f4df4d634df4df1d6f4df1d6f4d3f1d6f4d6
         * ios_token :
         * status : 1
         * delete_status : 1
         * created_on : 2019-10-16 12:09:56
         * modified_on : 2019-10-16 12:27:09
         */

        private String id;
        private String name;
        private String email_id;
        private String mobile;
        private String password;
        private String profile_pic;
        private String languages;
        private String dob;
        private String age;
        private String state;
        private String guardian_name;
        private String job_details;
        private String purpose;
        private String permanent_address;
        private String aadhar_card;
        private String aadhar_card_number;
        private String bed_confirmed;
        private String present_booking_id;
        private String token;
        private String ios_token;
        private String status;
        private String delete_status;
        private String created_on;
        private String modified_on;
        private String alternate_number;

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

        public String getEmail_id() {
            return email_id;
        }

        public void setEmail_id(String email_id) {
            this.email_id = email_id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getProfile_pic() {
            return profile_pic;
        }

        public void setProfile_pic(String profile_pic) {
            this.profile_pic = profile_pic;
        }

        public String getLanguages() {
            return languages;
        }

        public void setLanguages(String languages) {
            this.languages = languages;
        }

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getGuardian_name() {
            return guardian_name;
        }

        public void setGuardian_name(String guardian_name) {
            this.guardian_name = guardian_name;
        }

        public String getJob_details() {
            return job_details;
        }

        public void setJob_details(String job_details) {
            this.job_details = job_details;
        }

        public String getPurpose() {
            return purpose;
        }

        public void setPurpose(String purpose) {
            this.purpose = purpose;
        }

        public String getPermanent_address() {
            return permanent_address;
        }

        public void setPermanent_address(String permanent_address) {
            this.permanent_address = permanent_address;
        }

        public String getAadhar_card() {
            return aadhar_card;
        }

        public void setAadhar_card(String aadhar_card) {
            this.aadhar_card = aadhar_card;
        }

        public String getAadhar_card_number() {
            return aadhar_card_number;
        }

        public void setAadhar_card_number(String aadhar_card_number) {
            this.aadhar_card_number = aadhar_card_number;
        }

        public String getBed_confirmed() {
            return bed_confirmed;
        }

        public void setBed_confirmed(String bed_confirmed) {
            this.bed_confirmed = bed_confirmed;
        }

        public String getPresent_booking_id() {
            return present_booking_id;
        }

        public void setPresent_booking_id(String present_booking_id) {
            this.present_booking_id = present_booking_id;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getIos_token() {
            return ios_token;
        }

        public void setIos_token(String ios_token) {
            this.ios_token = ios_token;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDelete_status() {
            return delete_status;
        }

        public void setDelete_status(String delete_status) {
            this.delete_status = delete_status;
        }

        public String getCreated_on() {
            return created_on;
        }

        public void setCreated_on(String created_on) {
            this.created_on = created_on;
        }

        public String getModified_on() {
            return modified_on;
        }

        public void setModified_on(String modified_on) {
            this.modified_on = modified_on;
        }

        public String getAlternate_number() {
            return alternate_number;
        }

        public void setAlternate_number(String alternate_number) {
            this.alternate_number = alternate_number;
        }
    }
}
