package com.joystays.joy.pojos;

import java.util.List;

public class RoomDetailsPojo {

    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : [{"id":"1","owner_id":"1","hostel_id":"1","floor":"0","share":"1","room_name":"G01","ac":"yes","created_on":"2019-10-22 03:05:02","modified_on":null,"beds":"1"},{"id":"2","owner_id":"1","hostel_id":"1","floor":"0","share":"2","room_name":"G02","ac":"no","created_on":"2019-10-22 02:02:00","modified_on":null,"beds":"2"},{"id":"3","owner_id":"1","hostel_id":"1","floor":"0","share":"1","room_name":"G03","ac":"yes","created_on":"2019-10-22 03:05:02","modified_on":null,"beds":"1"},{"id":"4","owner_id":"1","hostel_id":"1","floor":"0","share":"3","room_name":"G04","ac":"no","created_on":"2019-10-22 02:02:00","modified_on":null,"beds":"3"}]
     * floor_layout : [{"id":"1","owner_id":"1","hostel_id":"1","floor":"0","image":"storage/img1.jpg","created_on":"2019-10-21 02:01:04"}]
     */

    private boolean status;
    private String message;
    private List<ResponseBean> response;
    private List<FloorLayoutBean> floor_layout;

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

    public List<FloorLayoutBean> getFloor_layout() {
        return floor_layout;
    }

    public void setFloor_layout(List<FloorLayoutBean> floor_layout) {
        this.floor_layout = floor_layout;
    }

    public static class ResponseBean {

        /**
         * id : 1
         * owner_id : 1
         * hostel_id : 1
         * floor : 0
         * share : 1
         * room_name : G01
         * ac : yes
         * created_on : 2019-10-22 03:05:02
         * modified_on : null
         * beds : 1
         */

        private String id;
        private String owner_id;
        private String hostel_id;
        private String floor;
        private String share;
        private String room_name;
        private String ac;
        private String created_on;
        private Object modified_on;
        private int beds;
        private int available_beds;

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

        public String getFloor() {
            return floor;
        }

        public void setFloor(String floor) {
            this.floor = floor;
        }

        public String getShare() {
            return share;
        }

        public void setShare(String share) {
            this.share = share;
        }

        public String getRoom_name() {
            return room_name;
        }

        public void setRoom_name(String room_name) {
            this.room_name = room_name;
        }

        public String getAc() {
            return ac;
        }

        public void setAc(String ac) {
            this.ac = ac;
        }

        public String getCreated_on() {
            return created_on;
        }

        public void setCreated_on(String created_on) {
            this.created_on = created_on;
        }

        public Object getModified_on() {
            return modified_on;
        }

        public void setModified_on(Object modified_on) {
            this.modified_on = modified_on;
        }

        public int getBeds() {
            return beds;
        }

        public void setBeds(int beds) {
            this.beds = beds;
        }

        public int getAvailable_beds() {
            return available_beds;
        }

        public void setAvailable_beds(int available_beds) {
            this.available_beds = available_beds;
        }
    }

    public static class FloorLayoutBean {
        /**
         * id : 1
         * owner_id : 1
         * hostel_id : 1
         * floor : 0
         * image : storage/img1.jpg
         * created_on : 2019-10-21 02:01:04
         */

        private String id;
        private String owner_id;
        private String hostel_id;
        private String floor;
        private String image;
        private String created_on;

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

        public String getFloor() {
            return floor;
        }

        public void setFloor(String floor) {
            this.floor = floor;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getCreated_on() {
            return created_on;
        }

        public void setCreated_on(String created_on) {
            this.created_on = created_on;
        }
    }
}
