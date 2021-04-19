package com.joystays.joy.pojos;

import java.util.List;

public class HostelPricesPojo {

    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : [{"share_alpha":"Single","id":"1","owner_id":"1","hostel_id":"1","share":"1","owner":"8500","advance":"2500","joy_price":"8200","room_type":"ac","package_type":"daily","created_on":"2019-10-22 03:03:02"},{"share_alpha":"Two","id":"2","owner_id":"1","hostel_id":"1","share":"2","owner":"7500","advance":"2000","joy_price":"7200","room_type":"ac","package_type":"daily","created_on":"2019-10-22 02:03:02"},{"share_alpha":"Three","id":"3","owner_id":"1","hostel_id":"1","share":"3","owner":"6500","advance":"2500","joy_price":"6200","room_type":"ac","package_type":"daily","created_on":"2019-10-22 03:03:02"},{"share_alpha":"Four","id":"4","owner_id":"1","hostel_id":"1","share":"4","owner":"5500","advance":"2000","joy_price":"5200","room_type":"ac","package_type":"daily","created_on":"2019-10-22 02:03:02"}]
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
         * share_alpha : Single
         * id : 1
         * owner_id : 1
         * hostel_id : 1
         * share : 1
         * owner : 8500
         * advance : 2500
         * joy_price : 8200
         * room_type : ac
         * package_type : daily
         * created_on : 2019-10-22 03:03:02
         */

        private String share_alpha;
        private String id;
        private String owner_id;
        private String hostel_id;
        private String share;
        private String owner;
        private String advance;
        private String joy_price;
        private String room_type;
        private String package_type;
        private String created_on;

        public String getShare_alpha() {
            return share_alpha;
        }

        public void setShare_alpha(String share_alpha) {
            this.share_alpha = share_alpha;
        }

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

        public String getShare() {
            return share;
        }

        public void setShare(String share) {
            this.share = share;
        }

        public String getOwner() {
            return owner;
        }

        public void setOwner(String owner) {
            this.owner = owner;
        }

        public String getAdvance() {
            return advance;
        }

        public void setAdvance(String advance) {
            this.advance = advance;
        }

        public String getJoy_price() {
            return joy_price;
        }

        public void setJoy_price(String joy_price) {
            this.joy_price = joy_price;
        }

        public String getRoom_type() {
            return room_type;
        }

        public void setRoom_type(String room_type) {
            this.room_type = room_type;
        }

        public String getPackage_type() {
            return package_type;
        }

        public void setPackage_type(String package_type) {
            this.package_type = package_type;
        }

        public String getCreated_on() {
            return created_on;
        }

        public void setCreated_on(String created_on) {
            this.created_on = created_on;
        }
    }
}
