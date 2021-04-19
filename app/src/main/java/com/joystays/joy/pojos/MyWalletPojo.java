package com.joystays.joy.pojos;

import java.util.List;

public class MyWalletPojo {

    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : {"id":"1","booking_id":"725928","user_id":"1","owner_id":"1","hostel_id":"1","floor":"1","room_id":"1","bed_id":"1","share":"1","package_type":"daily","check_in":"2019-12-05 10:00:00","check_out":"2019-12-31 12:00:00","amount":"11700","status":"1","payment_mode":"cash","payment_status":"pending","current_month_status":"pending","last_payment_date":"2019-12-05 10:00:00","last_due_date":null,"next_due_date":"2020-01-01 00:00:00","otp":"138464","created_on":"2019-10-16 12:09:56","modified_on":"2020-01-11 17:02:22","payment_date":"05 Dec 2019","due_date":"01 Jan 2020","name":"Chandu","email_id":"chandhu@gmail.com","mobile":"9999999999","password":"e10adc3949ba59abbe56e057f20f883e","refferal_code":null,"reffered_by_user_id":"0","reffered_code":null,"profile_pic":"storage/profile_pics/user.png","languages":"Telugu,Hindi,English","dob":"1990-05-13","age":"29","state":null,"guardian_name":"Smith","job_details":"B.tech","purpose":"Software Job","permanent_address":"Hyderabad","aadhar_card":"storage/profile_pics/5de63fc46e4a3.png","aadhar_card_number":"789123456555","bed_confirmed":"yes","present_booking_id":"43","user_type":"new","token":"dfsdf4df46f4df4d634df4df1d6f4df1d6f4d3f1d6f4d6","ios_token":"","delete_status":"1","bed_name":"Bed-1","bed_details":"Window Corner Bed","room_name":"G01"}
     * joy_rupees : {"id":"1","user_id":"1","amount":"1500","amount_left":"1000","amount_per_transaction":"500","created_on":"2019-12-10 00:00:00"}
     * room_change_amounts : [{"id":"1","user_id":"1","owner_id":"1","hostel_id":"1","from_share":"0","to_share":"1","price":"200","amount_type":"add","transaction_status":"pending","created_on":"2020-01-13 00:00:00"}]
     */

    private boolean status;
    private String message;
    private String referral_amount;
    private String referral_amounts_used;

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

    private ResponseBean response;
    private JoyRupeesBean joy_rupees;
    private List<RoomChangeAmountsBean> room_change_amounts;

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

    public JoyRupeesBean getJoy_rupees() {
        return joy_rupees;
    }

    public void setJoy_rupees(JoyRupeesBean joy_rupees) {
        this.joy_rupees = joy_rupees;
    }

    public List<RoomChangeAmountsBean> getRoom_change_amounts() {
        return room_change_amounts;
    }

    public void setRoom_change_amounts(List<RoomChangeAmountsBean> room_change_amounts) {
        this.room_change_amounts = room_change_amounts;
    }

    public static class ResponseBean {
        /**
         * id : 1
         * booking_id : 725928
         * user_id : 1
         * owner_id : 1
         * hostel_id : 1
         * floor : 1
         * room_id : 1
         * bed_id : 1
         * share : 1
         * package_type : daily
         * check_in : 2019-12-05 10:00:00
         * check_out : 2019-12-31 12:00:00
         * amount : 11700
         * status : 1
         * payment_mode : cash
         * payment_status : pending
         * current_month_status : pending
         * last_payment_date : 2019-12-05 10:00:00
         * last_due_date : null
         * next_due_date : 2020-01-01 00:00:00
         * otp : 138464
         * created_on : 2019-10-16 12:09:56
         * modified_on : 2020-01-11 17:02:22
         * payment_date : 05 Dec 2019
         * due_date : 01 Jan 2020
         * name : Chandu
         * email_id : chandhu@gmail.com
         * mobile : 9999999999
         * password : e10adc3949ba59abbe56e057f20f883e
         * refferal_code : null
         * reffered_by_user_id : 0
         * reffered_code : null
         * profile_pic : storage/profile_pics/user.png
         * languages : Telugu,Hindi,English
         * dob : 1990-05-13
         * age : 29
         * state : null
         * guardian_name : Smith
         * job_details : B.tech
         * purpose : Software Job
         * permanent_address : Hyderabad
         * aadhar_card : storage/profile_pics/5de63fc46e4a3.png
         * aadhar_card_number : 789123456555
         * bed_confirmed : yes
         * present_booking_id : 43
         * user_type : new
         * token : dfsdf4df46f4df4d634df4df1d6f4df1d6f4d3f1d6f4d6
         * ios_token :
         * delete_status : 1
         * bed_name : Bed-1
         * bed_details : Window Corner Bed
         * room_name : G01
         */

        private String id;
        private String booking_id;
        private String user_id;
        private String owner_id;
        private String hostel_id;
        private String floor;
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
        private Object last_due_date;
        private String next_due_date;
        private String otp;
        private String created_on;
        private String modified_on;
        private String payment_date;
        private String due_date;
        private String name;
        private String email_id;
        private String mobile;
        private String password;
        private Object refferal_code;
        private String reffered_by_user_id;
        private Object reffered_code;
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
        private String user_type;
        private String token;
        private String ios_token;
        private String delete_status;
        private String bed_name;
        private String bed_details;
        private String room_name;
        private String ac;
        private String UBID;
        private String advance_amount;
        private String booking_status;
        private String refundable_amount;
        private String next_month_amount;

        public String getRefundable_amount() {
            return refundable_amount;
        }

        public void setRefundable_amount(String refundable_amount) {
            this.refundable_amount = refundable_amount;
        }

        public String getUBID() {
            return UBID;
        }

        public void setUBID(String UBID) {
            this.UBID = UBID;
        }

        public String getAc() {
            return ac;
        }

        public void setAc(String ac) {
            this.ac = ac;
        }

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

        public String getFloor() {
            return floor;
        }

        public void setFloor(String floor) {
            this.floor = floor;
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

        public Object getLast_due_date() {
            return last_due_date;
        }

        public void setLast_due_date(Object last_due_date) {
            this.last_due_date = last_due_date;
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

        public String getModified_on() {
            return modified_on;
        }

        public void setModified_on(String modified_on) {
            this.modified_on = modified_on;
        }

        public String getPayment_date() {
            return payment_date;
        }

        public void setPayment_date(String payment_date) {
            this.payment_date = payment_date;
        }

        public String getDue_date() {
            return due_date;
        }

        public void setDue_date(String due_date) {
            this.due_date = due_date;
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

        public Object getRefferal_code() {
            return refferal_code;
        }

        public void setRefferal_code(Object refferal_code) {
            this.refferal_code = refferal_code;
        }

        public String getReffered_by_user_id() {
            return reffered_by_user_id;
        }

        public void setReffered_by_user_id(String reffered_by_user_id) {
            this.reffered_by_user_id = reffered_by_user_id;
        }

        public Object getReffered_code() {
            return reffered_code;
        }

        public void setReffered_code(Object reffered_code) {
            this.reffered_code = reffered_code;
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

        public String getUser_type() {
            return user_type;
        }

        public void setUser_type(String user_type) {
            this.user_type = user_type;
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

        public String getDelete_status() {
            return delete_status;
        }

        public void setDelete_status(String delete_status) {
            this.delete_status = delete_status;
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

        public String getRoom_name() {
            return room_name;
        }

        public void setRoom_name(String room_name) {
            this.room_name = room_name;
        }

        public String getAdvance_amount() {
            return advance_amount;
        }

        public void setAdvance_amount(String advance_amount) {
            this.advance_amount = advance_amount;
        }

        public String getBooking_status() {
            return booking_status;
        }

        public void setBooking_status(String booking_status) {
            this.booking_status = booking_status;
        }

        public String getNext_month_amount() {
            return next_month_amount;
        }

        public void setNext_month_amount(String next_month_amount) {
            this.next_month_amount = next_month_amount;
        }
    }

    public static class JoyRupeesBean {
        /**
         * id : 1
         * user_id : 1
         * amount : 1500
         * amount_left : 1000
         * amount_per_transaction : 500
         * created_on : 2019-12-10 00:00:00
         */

        private String id;
        private String user_id;
        private String amount;
        private String amount_left;
        private String amount_per_transaction;
        private String created_on;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getAmount_left() {
            return amount_left;
        }

        public void setAmount_left(String amount_left) {
            this.amount_left = amount_left;
        }

        public String getAmount_per_transaction() {
            return amount_per_transaction;
        }

        public void setAmount_per_transaction(String amount_per_transaction) {
            this.amount_per_transaction = amount_per_transaction;
        }

        public String getCreated_on() {
            return created_on;
        }

        public void setCreated_on(String created_on) {
            this.created_on = created_on;
        }
    }

    public static class RoomChangeAmountsBean {
        /**
         * id : 1
         * user_id : 1
         * owner_id : 1
         * hostel_id : 1
         * from_share : 0
         * to_share : 1
         * price : 200
         * amount_type : add
         * transaction_status : pending
         * created_on : 2020-01-13 00:00:00
         */

        private String id;
        private String user_id;
        private String owner_id;
        private String hostel_id;
        private String from_share;
        private String to_share;
        private String price;
        private String amount_type;
        private String transaction_status;
        private String created_on;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getFrom_share() {
            return from_share;
        }

        public void setFrom_share(String from_share) {
            this.from_share = from_share;
        }

        public String getTo_share() {
            return to_share;
        }

        public void setTo_share(String to_share) {
            this.to_share = to_share;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getAmount_type() {
            return amount_type;
        }

        public void setAmount_type(String amount_type) {
            this.amount_type = amount_type;
        }

        public String getTransaction_status() {
            return transaction_status;
        }

        public void setTransaction_status(String transaction_status) {
            this.transaction_status = transaction_status;
        }

        public String getCreated_on() {
            return created_on;
        }

        public void setCreated_on(String created_on) {
            this.created_on = created_on;
        }
    }
}
