package com.joystays.joy.pojos;

import java.util.List;

public class FloorsPojo {


    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : [{"floor":"0","floorname":"Ground Floor"},{"floor":"1","floorname":"First Floor"},{"floor":"2","floorname":"Third Floor"},{"floor":"3","floorname":"Third Floor"},{"floor":"4","floorname":"Second Floor"}]
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
         * floor : 0
         * floorname : Ground Floor
         */

        private String floor;
        private String floorname;

        public String getFloor() {
            return floor;
        }

        public void setFloor(String floor) {
            this.floor = floor;
        }

        public String getFloorname() {
            return floorname;
        }

        public void setFloorname(String floorname) {
            this.floorname = floorname;
        }
    }
}
