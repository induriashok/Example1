package com.joystays.joy.pojos;

import java.util.List;

public class ChatsPojo {


    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : [{"id":"1","name":"Mahesh","parent_name":"Mahesh babu","mobile":"8142032312","password":"4dda30dc32919d9bc9710b0ee6f847c7","doj":"2020-01-29","email_id":"maheshbabumerugu@gmail.com","education":"B.tech","address":"House no 401-20/32#","profile_pic":"storage/profile_pics/5e32a4c61d4ce.jpeg","aadhar_card":"storage/profile_pics/5e32a4c56c66c.jpeg","aadhar_number":"967675767949","owner_id":"1","hostel_id":"1","status":"0","delete_status":"1","created_on":null,"modified_on":null,"supervisor_id":"1","user_id":"28","message":"sfsdfs"},{"id":"2","name":"Mahesh","parent_name":"Mahesh babu","mobile":"8142032312","password":"4dda30dc32919d9bc9710b0ee6f847c7","doj":"2020-01-29","email_id":"maheshbabumerugu@gmail.com","education":"B.tech","address":"House no 401-20/32#","profile_pic":"storage/profile_pics/5e32a4c61d4ce.jpeg","aadhar_card":"storage/profile_pics/5e32a4c56c66c.jpeg","aadhar_number":"967675767949","owner_id":"1","hostel_id":"1","status":"0","delete_status":"1","created_on":null,"modified_on":null,"supervisor_id":"1","user_id":"28","message":"sfsdfs"}]
     */

    private boolean status;
    private String message;
    private List<ResponseBean> response;

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

    public List<ResponseBean> getResponse() {
        return response;
    }

    public void setResponse(List<ResponseBean> response) {
        this.response = response;
    }

    public static class ResponseBean {
        /**
         * id : 1
         * name : Mahesh
         * parent_name : Mahesh babu
         * mobile : 8142032312
         * password : 4dda30dc32919d9bc9710b0ee6f847c7
         * doj : 2020-01-29
         * email_id : maheshbabumerugu@gmail.com
         * education : B.tech
         * address : House no 401-20/32#
         * profile_pic : storage/profile_pics/5e32a4c61d4ce.jpeg
         * aadhar_card : storage/profile_pics/5e32a4c56c66c.jpeg
         * aadhar_number : 967675767949
         * owner_id : 1
         * hostel_id : 1
         * status : 0
         * delete_status : 1
         * created_on : null
         * modified_on : null
         * supervisor_id : 1
         * user_id : 28
         * message : sfsdfs
         */

        private String id;
        private String name;
        private String parent_name;
        private String mobile;
        private String password;
        private String doj;
        private String email_id;
        private String education;
        private String address;
        private String profile_pic;
        private String aadhar_card;
        private String aadhar_number;
        private String owner_id;
        private String hostel_id;
        private String status;
        private String delete_status;
        private Object created_on;
        private Object modified_on;
        private String supervisor_id;
        private String user_id;
        private String message;

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

        public String getParent_name() {
            return parent_name;
        }

        public void setParent_name(String parent_name) {
            this.parent_name = parent_name;
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

        public String getDoj() {
            return doj;
        }

        public void setDoj(String doj) {
            this.doj = doj;
        }

        public String getEmail_id() {
            return email_id;
        }

        public void setEmail_id(String email_id) {
            this.email_id = email_id;
        }

        public String getEducation() {
            return education;
        }

        public void setEducation(String education) {
            this.education = education;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getProfile_pic() {
            return profile_pic;
        }

        public void setProfile_pic(String profile_pic) {
            this.profile_pic = profile_pic;
        }

        public String getAadhar_card() {
            return aadhar_card;
        }

        public void setAadhar_card(String aadhar_card) {
            this.aadhar_card = aadhar_card;
        }

        public String getAadhar_number() {
            return aadhar_number;
        }

        public void setAadhar_number(String aadhar_number) {
            this.aadhar_number = aadhar_number;
        }

        public String getOwner_id() {
            return owner_id;
        }

        public void setOwner_id(String owner_id) {
            this.owner_id = owner_id;
        }

        public String getHostel_id() {
            return hostel_id;
        }

        public void setHostel_id(String hostel_id) {
            this.hostel_id = hostel_id;
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

        public Object getCreated_on() {
            return created_on;
        }

        public void setCreated_on(Object created_on) {
            this.created_on = created_on;
        }

        public Object getModified_on() {
            return modified_on;
        }

        public void setModified_on(Object modified_on) {
            this.modified_on = modified_on;
        }

        public String getSupervisor_id() {
            return supervisor_id;
        }

        public void setSupervisor_id(String supervisor_id) {
            this.supervisor_id = supervisor_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
