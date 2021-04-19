package com.joystays.joy.pojos;

import java.util.List;

public class BedsPojo {


    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : [{"id":"3","owner_id":"1","hostel_id":"1","room_id":"2","bed_name":"Bed-2","details":"Window Corner Bed","delete_status":"1","created_on":"2019-10-22 01:03:05","modified_on":null,"name":"","languages":"","days":"","days_left":"","status":"available"},{"id":"2","owner_id":"1","hostel_id":"1","room_id":"2","bed_name":"Bed-1","details":"Door Corner Bed","delete_status":"1","created_on":"2019-10-22 01:06:00","modified_on":null,"name":"Smith Ray","languages":"Hindi,English","days":"","days_left":"","status":"available"},{"id":"2","owner_id":"1","hostel_id":"1","room_id":"2","bed_name":"Bed-1","details":"Door Corner Bed","delete_status":"1","created_on":"2019-10-22 01:06:00","modified_on":null,"name":"ram","languages":"Telugu,Hindi,English","days":"","days_left":"","status":"available"},{"id":"2","owner_id":"1","hostel_id":"1","room_id":"2","bed_name":"Bed-1","details":"Door Corner Bed","delete_status":"1","created_on":"2019-10-22 01:06:00","modified_on":null,"name":"khan","languages":"","days":"","days_left":"","status":"available"},{"id":"2","owner_id":"1","hostel_id":"1","room_id":"2","bed_name":"Bed-1","details":"Door Corner Bed","delete_status":"1","created_on":"2019-10-22 01:06:00","modified_on":null,"name":"john Lee","languages":"","days":"","days_left":"","status":"available"},{"id":"2","owner_id":"1","hostel_id":"1","room_id":"2","bed_name":"Bed-1","details":"Door Corner Bed","delete_status":"1","created_on":"2019-10-22 01:06:00","modified_on":null,"name":"seena","languages":"","days":"","days_left":"","status":"available"},{"id":"2","owner_id":"1","hostel_id":"1","room_id":"2","bed_name":"Bed-1","details":"Door Corner Bed","delete_status":"1","created_on":"2019-10-22 01:06:00","modified_on":null,"name":"amith","languages":"","days":"","days_left":"","status":"available"},{"id":"2","owner_id":"1","hostel_id":"1","room_id":"2","bed_name":"Bed-1","details":"Door Corner Bed","delete_status":"1","created_on":"2019-10-22 01:06:00","modified_on":null,"name":"Vani","languages":"","days":"","days_left":"","status":"available"},{"id":"2","owner_id":"1","hostel_id":"1","room_id":"2","bed_name":"Bed-1","details":"Door Corner Bed","delete_status":"1","created_on":"2019-10-22 01:06:00","modified_on":null,"name":"Sree","languages":"","days":"","days_left":"","status":"available"},{"id":"2","owner_id":"1","hostel_id":"1","room_id":"2","bed_name":"Bed-1","details":"Door Corner Bed","delete_status":"1","created_on":"2019-10-22 01:06:00","modified_on":null,"name":"veena","languages":"","days":"","days_left":"","status":"available"},{"id":"2","owner_id":"1","hostel_id":"1","room_id":"2","bed_name":"Bed-1","details":"Door Corner Bed","delete_status":"1","created_on":"2019-10-22 01:06:00","modified_on":null,"name":"Srikanth","languages":"","days":"","days_left":"","status":"available"},{"id":"2","owner_id":"1","hostel_id":"1","room_id":"2","bed_name":"Bed-1","details":"Door Corner Bed","delete_status":"1","created_on":"2019-10-22 01:06:00","modified_on":null,"name":"Dilip","languages":"","days":"","days_left":"","status":"available"},{"id":"2","owner_id":"1","hostel_id":"1","room_id":"2","bed_name":"Bed-1","details":"Door Corner Bed","delete_status":"1","created_on":"2019-10-22 01:06:00","modified_on":null,"name":"9908318159","languages":"","days":"","days_left":"","status":"available"},{"id":"2","owner_id":"1","hostel_id":"1","room_id":"2","bed_name":"Bed-1","details":"Door Corner Bed","delete_status":"1","created_on":"2019-10-22 01:06:00","modified_on":null,"name":"Smith Ray","languages":"Hindi,English","days":"","days_left":"","status":"available"},{"id":"2","owner_id":"1","hostel_id":"1","room_id":"2","bed_name":"Bed-1","details":"Door Corner Bed","delete_status":"1","created_on":"2019-10-22 01:06:00","modified_on":null,"name":"Smith Ray","languages":"Hindi,English","days":"","days_left":"","status":"available"},{"id":"2","owner_id":"1","hostel_id":"1","room_id":"2","bed_name":"Bed-1","details":"Door Corner Bed","delete_status":"1","created_on":"2019-10-22 01:06:00","modified_on":null,"name":"Smith Ray","languages":"Hindi,English","days":"","days_left":"","status":"available"}]
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
         * id : 3
         * owner_id : 1
         * hostel_id : 1
         * room_id : 2
         * bed_name : Bed-2
         * details : Window Corner Bed
         * delete_status : 1
         * created_on : 2019-10-22 01:03:05
         * modified_on : null
         * name : 
         * languages : 
         * days : 
         * days_left : 
         * status : available
         */

        private String id;
        private String owner_id;
        private String hostel_id;
        private String room_id;
        private String bed_name;
        private String details;
        private String delete_status;
        private String created_on;
        private String modified_on;
        private String name;
        private String languages;
        private String days;
        private String days_left;
        private String status;
        private String user_id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getRoom_id() {
            return room_id;
        }

        public void setRoom_id(String room_id) {
            this.room_id = room_id;
        }

        public String getBed_name() {
            return bed_name;
        }

        public void setBed_name(String bed_name) {
            this.bed_name = bed_name;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLanguages() {
            return languages;
        }

        public void setLanguages(String languages) {
            this.languages = languages;
        }

        public String getDays() {
            return days;
        }

        public void setDays(String days) {
            this.days = days;
        }

        public String getDays_left() {
            return days_left;
        }

        public void setDays_left(String days_left) {
            this.days_left = days_left;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }
    }
}
