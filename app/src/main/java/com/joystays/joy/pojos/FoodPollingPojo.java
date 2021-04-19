package com.joystays.joy.pojos;

import java.util.List;

public class FoodPollingPojo {

    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : {"id":"9","owner_id":"2","supervisor_id":"2","hostel_id":"2","foodtime":"breakfast","items":["Curd","Rice"],"created_on":"2020-02-03 11:24:56"}
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
         * id : 9
         * owner_id : 2
         * supervisor_id : 2
         * hostel_id : 2
         * foodtime : breakfast
         * items : ["Curd","Rice"]
         * created_on : 2020-02-03 11:24:56
         */

        private String id;
        private String owner_id;
        private String supervisor_id;
        private String hostel_id;
        private String foodtime;
        private String created_on;
        private List<String> items;

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

        public String getSupervisor_id() {
            return supervisor_id;
        }

        public void setSupervisor_id(String supervisor_id) {
            this.supervisor_id = supervisor_id;
        }

        public String getHostel_id() {
            return hostel_id;
        }

        public void setHostel_id(String hostel_id) {
            this.hostel_id = hostel_id;
        }

        public String getFoodtime() {
            return foodtime;
        }

        public void setFoodtime(String foodtime) {
            this.foodtime = foodtime;
        }

        public String getCreated_on() {
            return created_on;
        }

        public void setCreated_on(String created_on) {
            this.created_on = created_on;
        }

        public List<String> getItems() {
            return items;
        }

        public void setItems(List<String> items) {
            this.items = items;
        }
    }

}
