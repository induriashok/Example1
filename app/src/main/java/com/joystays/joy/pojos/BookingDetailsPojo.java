package com.joystays.joy.pojos;

import java.util.List;

public class BookingDetailsPojo {

    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : [{"id":"7","booking_id":"174930","user_id":"1","owner_id":"1","hostel_id":"1","floor_id":"1","room_id":"1","bed_id":"1","share":"1","package_type":"daily","check_in":"2019-12-05 10:00:00","check_out":"2019-12-31 12:00:00","amount":"11700","status":"pending","payment_mode":"cash","payment_status":"pending","current_month_status":"pending","last_payment_date":"2019-12-05 10:00:00","next_due_date":"2020-01-01 00:00:00","otp":"725900","created_on":"2019-12-05 15:46:05","modified_on":null,"name":"John Doe","email_id":"john@gmail.com","mobile":"9999999999","profile_pic":"storage/profile_pics/5de63fc46e431.png","owner_name":"John Doe","owner_mobile":"9999999999","owner_website":"www.princehostels.com","owner_address":"Beside that, Below this, Opposite that","owner_description":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.","hostel_name":"Prince Hostels Madhapur","hostel_address":"Beside that, Below this, Opposite that","hostel_image":"storage/profile_pics/5da5bc7022576.png","hostel_ac":"yes","hostel_non_ac":"yes","hostels_recommended":"yes","hostel_type":"boys","hostel_lat":"17.4486","hostel_lng":"78.3908","hostel_ratings":"4.5","room_name":"G01","rooms_ac":"yes","bed_name":"Bed-1","bed_details":"Window Corner Bed"}]
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
         * id : 7
         * booking_id : 174930
         * user_id : 1
         * owner_id : 1
         * hostel_id : 1
         * floor_id : 1
         * room_id : 1
         * bed_id : 1
         * share : 1
         * package_type : daily
         * check_in : 2019-12-05 10:00:00
         * check_out : 2019-12-31 12:00:00
         * amount : 11700
         * status : pending
         * payment_mode : cash
         * payment_status : pending
         * current_month_status : pending
         * last_payment_date : 2019-12-05 10:00:00
         * next_due_date : 2020-01-01 00:00:00
         * otp : 725900
         * created_on : 2019-12-05 15:46:05
         * modified_on : null
         * name : John Doe
         * email_id : john@gmail.com
         * mobile : 9999999999
         * profile_pic : storage/profile_pics/5de63fc46e431.png
         * owner_name : John Doe
         * owner_mobile : 9999999999
         * owner_website : www.princehostels.com
         * owner_address : Beside that, Below this, Opposite that
         * owner_description : Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
         * hostel_name : Prince Hostels Madhapur
         * hostel_address : Beside that, Below this, Opposite that
         * hostel_image : storage/profile_pics/5da5bc7022576.png
         * hostel_ac : yes
         * hostel_non_ac : yes
         * hostels_recommended : yes
         * hostel_type : boys
         * hostel_lat : 17.4486
         * hostel_lng : 78.3908
         * hostel_ratings : 4.5
         * room_name : G01
         * rooms_ac : yes
         * bed_name : Bed-1
         * bed_details : Window Corner Bed
         */

        private String id;
        private String booking_id;
        private String user_id;
        private String owner_id;
        private String hostel_id;
        private String floor_id;
        private String room_id;
        private String bed_id;
        private String share;
        private String package_type;
        private String check_in;
        private String check_out;
        private String amount;
        private String status;
        private String payment_mode;
        private String payment_status;
        private String current_month_status;
        private String last_payment_date;
        private String next_due_date;
        private String otp;
        private String created_on;
        private Object modified_on;
        private String name;
        private String email_id;
        private String mobile;
        private String profile_pic;
        private String owner_name;
        private String owner_mobile;
        private String owner_website;
        private String owner_address;
        private String owner_description;
        private String hostel_name;
        private String hostel_address;
        private String hostel_image;
        private String hostel_ac;
        private String hostel_non_ac;
        private String hostels_recommended;
        private String hostel_type;
        private String hostel_lat;
        private String hostel_lng;
        private String hostel_ratings;
        private String room_name;
        private String rooms_ac;
        private String bed_name;
        private String bed_details;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBooking_id() {
            return booking_id;
        }

        public void setBooking_id(String booking_id) {
            this.booking_id = booking_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
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

        public String getFloor_id() {
            return floor_id;
        }

        public void setFloor_id(String floor_id) {
            this.floor_id = floor_id;
        }

        public String getRoom_id() {
            return room_id;
        }

        public void setRoom_id(String room_id) {
            this.room_id = room_id;
        }

        public String getBed_id() {
            return bed_id;
        }

        public void setBed_id(String bed_id) {
            this.bed_id = bed_id;
        }

        public String getShare() {
            return share;
        }

        public void setShare(String share) {
            this.share = share;
        }

        public String getPackage_type() {
            return package_type;
        }

        public void setPackage_type(String package_type) {
            this.package_type = package_type;
        }

        public String getCheck_in() {
            return check_in;
        }

        public void setCheck_in(String check_in) {
            this.check_in = check_in;
        }

        public String getCheck_out() {
            return check_out;
        }

        public void setCheck_out(String check_out) {
            this.check_out = check_out;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPayment_mode() {
            return payment_mode;
        }

        public void setPayment_mode(String payment_mode) {
            this.payment_mode = payment_mode;
        }

        public String getPayment_status() {
            return payment_status;
        }

        public void setPayment_status(String payment_status) {
            this.payment_status = payment_status;
        }

        public String getCurrent_month_status() {
            return current_month_status;
        }

        public void setCurrent_month_status(String current_month_status) {
            this.current_month_status = current_month_status;
        }

        public String getLast_payment_date() {
            return last_payment_date;
        }

        public void setLast_payment_date(String last_payment_date) {
            this.last_payment_date = last_payment_date;
        }

        public String getNext_due_date() {
            return next_due_date;
        }

        public void setNext_due_date(String next_due_date) {
            this.next_due_date = next_due_date;
        }

        public String getOtp() {
            return otp;
        }

        public void setOtp(String otp) {
            this.otp = otp;
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

        public String getProfile_pic() {
            return profile_pic;
        }

        public void setProfile_pic(String profile_pic) {
            this.profile_pic = profile_pic;
        }

        public String getOwner_name() {
            return owner_name;
        }

        public void setOwner_name(String owner_name) {
            this.owner_name = owner_name;
        }

        public String getOwner_mobile() {
            return owner_mobile;
        }

        public void setOwner_mobile(String owner_mobile) {
            this.owner_mobile = owner_mobile;
        }

        public String getOwner_website() {
            return owner_website;
        }

        public void setOwner_website(String owner_website) {
            this.owner_website = owner_website;
        }

        public String getOwner_address() {
            return owner_address;
        }

        public void setOwner_address(String owner_address) {
            this.owner_address = owner_address;
        }

        public String getOwner_description() {
            return owner_description;
        }

        public void setOwner_description(String owner_description) {
            this.owner_description = owner_description;
        }

        public String getHostel_name() {
            return hostel_name;
        }

        public void setHostel_name(String hostel_name) {
            this.hostel_name = hostel_name;
        }

        public String getHostel_address() {
            return hostel_address;
        }

        public void setHostel_address(String hostel_address) {
            this.hostel_address = hostel_address;
        }

        public String getHostel_image() {
            return hostel_image;
        }

        public void setHostel_image(String hostel_image) {
            this.hostel_image = hostel_image;
        }

        public String getHostel_ac() {
            return hostel_ac;
        }

        public void setHostel_ac(String hostel_ac) {
            this.hostel_ac = hostel_ac;
        }

        public String getHostel_non_ac() {
            return hostel_non_ac;
        }

        public void setHostel_non_ac(String hostel_non_ac) {
            this.hostel_non_ac = hostel_non_ac;
        }

        public String getHostels_recommended() {
            return hostels_recommended;
        }

        public void setHostels_recommended(String hostels_recommended) {
            this.hostels_recommended = hostels_recommended;
        }

        public String getHostel_type() {
            return hostel_type;
        }

        public void setHostel_type(String hostel_type) {
            this.hostel_type = hostel_type;
        }

        public String getHostel_lat() {
            return hostel_lat;
        }

        public void setHostel_lat(String hostel_lat) {
            this.hostel_lat = hostel_lat;
        }

        public String getHostel_lng() {
            return hostel_lng;
        }

        public void setHostel_lng(String hostel_lng) {
            this.hostel_lng = hostel_lng;
        }

        public String getHostel_ratings() {
            return hostel_ratings;
        }

        public void setHostel_ratings(String hostel_ratings) {
            this.hostel_ratings = hostel_ratings;
        }

        public String getRoom_name() {
            return room_name;
        }

        public void setRoom_name(String room_name) {
            this.room_name = room_name;
        }

        public String getRooms_ac() {
            return rooms_ac;
        }

        public void setRooms_ac(String rooms_ac) {
            this.rooms_ac = rooms_ac;
        }

        public String getBed_name() {
            return bed_name;
        }

        public void setBed_name(String bed_name) {
            this.bed_name = bed_name;
        }

        public String getBed_details() {
            return bed_details;
        }

        public void setBed_details(String bed_details) {
            this.bed_details = bed_details;
        }
    }
}
