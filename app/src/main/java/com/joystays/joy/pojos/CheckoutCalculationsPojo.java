package com.joystays.joy.pojos;

public class CheckoutCalculationsPojo {

    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : {"id":"1","owner_id":"1","hostel_id":"1","share":"1","owner":"500","advance":"2500","refundable_amount":"2000","joy_price":"450","room_type":"ac","package_type":"daily","created_on":"2019-10-22 03:03:02","days":26,"day_price":"450","final_price":11700,"text":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."}
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
         * owner_id : 1
         * hostel_id : 1
         * share : 1
         * owner : 500
         * advance : 2500
         * refundable_amount : 2000
         * joy_price : 450
         * room_type : ac
         * package_type : daily
         * created_on : 2019-10-22 03:03:02
         * days : 26
         * day_price : 450
         * final_price : 11700
         * text : Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
         */

        private String id;
        private String owner_id;
        private String hostel_id;
        private String share;
        private String owner;
        private String advance;
        private String refundable_amount;
        private String joy_price;
        private String room_type;
        private String package_type;
        private String created_on;
        private int days;
        private String day_price;
        private int final_price;
        private String text;
        private String wallet_amount;
        private String referral_amount;
        private String referral_amounts_used;
        private String days_amount;

        public String getWallet_amount() {
            return wallet_amount;
        }

        public void setWallet_amount(String wallet_amount) {
            this.wallet_amount = wallet_amount;
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

        public String getRefundable_amount() {
            return refundable_amount;
        }

        public void setRefundable_amount(String refundable_amount) {
            this.refundable_amount = refundable_amount;
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

        public int getDays() {
            return days;
        }

        public void setDays(int days) {
            this.days = days;
        }

        public String getDay_price() {
            return day_price;
        }

        public void setDay_price(String day_price) {
            this.day_price = day_price;
        }

        public int getFinal_price() {
            return final_price;
        }

        public void setFinal_price(int final_price) {
            this.final_price = final_price;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getReferral_amount() {
            return referral_amount;
        }

        public void setReferral_amount(String referral_amount) {
            this.referral_amount = referral_amount;
        }

        public String getReferral_amounts_used() {
            return referral_amounts_used;
        }

        public void setReferral_amounts_used(String referral_amounts_used) {
            this.referral_amounts_used = referral_amounts_used;
        }

        public String getDays_amount() {
            return days_amount;
        }

        public void setDays_amount(String days_amount) {
            this.days_amount = days_amount;
        }
    }
}
