package com.joystays.joy.pojos;

public class UserBookingDetailsPojo {

    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : {"id":"1","name":"mounika","email_id":"mounika.r@iprismtech.com","mobile":"9642747874","password":"e10adc3949ba59abbe56e057f20f883e","refferal_code":"nbn70cis","reffered_by_user_id":"0","profile_pic":"storage/profile_pics/5e328dd685393.png","dob":"1996-08-13","age":"23","guardian_name":"raja","job_details":"btech","purpose":"job","permanent_address":"3_171/1","aadhar_card":"storage/profile_pics/5e328dd691b08.png","aadhar_card_number":"123456789012","bed_confirmed":"no","present_booking_id":"1","user_type":"new","status":"pending","delete_status":"1","created_on":"2020-01-31 12:14:38","booking_id":"200909","user_id":"1","owner_id":"2","hostel_id":"2","floor":"0","room_id":"91","bed_id":"83","share":"1","package_type":"monthly","check_in":"2020-02-01 00:00:00","check_out":"2020-03-01 00:00:00","amount":"130500","payment_mode":"online","payment_status":"paid","current_month_status":"pending","last_payment_date":"2020-02-01 00:00:00","next_due_date":"2020-03-01 00:00:00","otp":"561395","bed_name":"Bed:01","room_name":"Room1","hostel_name":"Srikanth girls hostels","owner_details":{"id":"2","name":"srikanth","hostel_name":"Srikanth girls hostel","mobile":"8886032595","password":"e10adc3949ba59abbe56e057f20f883e","email_id":"srikanth@gmail.com","website":"www.srikanthhostels.com","address":"Madhapur, Hyderabad","description":"A luxury working women and ladies hostel","profile_pic":"storage/profile_pics/5e32b808c1a50.jpeg","status":"1","delete_status":"1","created_on":"2020-01-30 16:33:36"}}
     * joy_rupees : {"id":"1","user_id":"1","amount":"1500","amount_left":"1000","amount_per_transaction":"500","created_on":"2019-12-10 00:00:00"}
     */

    private boolean status;
    private String message;
    private ResponseBean response;
    private JoyRupeesBean joy_rupees;

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

    public static class ResponseBean {
        /**
         * id : 1
         * name : mounika
         * email_id : mounika.r@iprismtech.com
         * mobile : 9642747874
         * password : e10adc3949ba59abbe56e057f20f883e
         * refferal_code : nbn70cis
         * reffered_by_user_id : 0
         * profile_pic : storage/profile_pics/5e328dd685393.png
         * dob : 1996-08-13
         * age : 23
         * guardian_name : raja
         * job_details : btech
         * purpose : job
         * permanent_address : 3_171/1
         * aadhar_card : storage/profile_pics/5e328dd691b08.png
         * aadhar_card_number : 123456789012
         * bed_confirmed : no
         * present_booking_id : 1
         * user_type : new
         * status : pending
         * delete_status : 1
         * created_on : 2020-01-31 12:14:38
         * booking_id : 200909
         * user_id : 1
         * owner_id : 2
         * hostel_id : 2
         * floor : 0
         * room_id : 91
         * bed_id : 83
         * share : 1
         * package_type : monthly
         * check_in : 2020-02-01 00:00:00
         * check_out : 2020-03-01 00:00:00
         * amount : 130500
         * payment_mode : online
         * payment_status : paid
         * current_month_status : pending
         * last_payment_date : 2020-02-01 00:00:00
         * next_due_date : 2020-03-01 00:00:00
         * otp : 561395
         * bed_name : Bed:01
         * room_name : Room1
         * hostel_name : Srikanth girls hostels
         * owner_details : {"id":"2","name":"srikanth","hostel_name":"Srikanth girls hostel","mobile":"8886032595","password":"e10adc3949ba59abbe56e057f20f883e","email_id":"srikanth@gmail.com","website":"www.srikanthhostels.com","address":"Madhapur, Hyderabad","description":"A luxury working women and ladies hostel","profile_pic":"storage/profile_pics/5e32b808c1a50.jpeg","status":"1","delete_status":"1","created_on":"2020-01-30 16:33:36"}
         */

        private String id;
        private String name;
        private String email_id;
        private String mobile;
        private String password;
        private String refferal_code;
        private String reffered_by_user_id;
        private String profile_pic;
        private String dob;
        private String age;
        private String guardian_name;
        private String job_details;
        private String purpose;
        private String permanent_address;
        private String aadhar_card;
        private String aadhar_card_number;
        private String bed_confirmed;
        private String present_booking_id;
        private String user_type;
        private String status;
        private String delete_status;
        private String created_on;
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
        private String payment_mode;
        private String payment_status;
        private String current_month_status;
        private String last_payment_date;
        private String next_due_date;
        private String otp;
        private String bed_name;
        private String room_name;
        private String hostel_name;
        private String lat;
        private String lng;
        private String vacate_date;
        private OwnerDetailsBean owner_details;

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

        public String getRefferal_code() {
            return refferal_code;
        }

        public void setRefferal_code(String refferal_code) {
            this.refferal_code = refferal_code;
        }

        public String getReffered_by_user_id() {
            return reffered_by_user_id;
        }

        public void setReffered_by_user_id(String reffered_by_user_id) {
            this.reffered_by_user_id = reffered_by_user_id;
        }

        public String getProfile_pic() {
            return profile_pic;
        }

        public void setProfile_pic(String profile_pic) {
            this.profile_pic = profile_pic;
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

        public String getBed_name() {
            return bed_name;
        }

        public void setBed_name(String bed_name) {
            this.bed_name = bed_name;
        }

        public String getRoom_name() {
            return room_name;
        }

        public void setRoom_name(String room_name) {
            this.room_name = room_name;
        }

        public String getHostel_name() {
            return hostel_name;
        }

        public void setHostel_name(String hostel_name) {
            this.hostel_name = hostel_name;
        }

        public OwnerDetailsBean getOwner_details() {
            return owner_details;
        }

        public void setOwner_details(OwnerDetailsBean owner_details) {
            this.owner_details = owner_details;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getVacate_date() {
            return vacate_date;
        }

        public void setVacate_date(String vacate_date) {
            this.vacate_date = vacate_date;
        }

        public static class OwnerDetailsBean {
            /**
             * id : 2
             * name : srikanth
             * hostel_name : Srikanth girls hostel
             * mobile : 8886032595
             * password : e10adc3949ba59abbe56e057f20f883e
             * email_id : srikanth@gmail.com
             * website : www.srikanthhostels.com
             * address : Madhapur, Hyderabad
             * description : A luxury working women and ladies hostel
             * profile_pic : storage/profile_pics/5e32b808c1a50.jpeg
             * status : 1
             * delete_status : 1
             * created_on : 2020-01-30 16:33:36
             */

            private String id;
            private String name;
            private String hostel_name;
            private String mobile;
            private String password;
            private String email_id;
            private String website;
            private String address;
            private String description;
            private String profile_pic;
            private String status;
            private String delete_status;
            private String created_on;
            private String languages;

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

            public String getHostel_name() {
                return hostel_name;
            }

            public void setHostel_name(String hostel_name) {
                this.hostel_name = hostel_name;
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

            public String getEmail_id() {
                return email_id;
            }

            public void setEmail_id(String email_id) {
                this.email_id = email_id;
            }

            public String getWebsite() {
                return website;
            }

            public void setWebsite(String website) {
                this.website = website;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getProfile_pic() {
                return profile_pic;
            }

            public void setProfile_pic(String profile_pic) {
                this.profile_pic = profile_pic;
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

            public String getLanguages() {
                return languages;
            }

            public void setLanguages(String languages) {
                this.languages = languages;
            }
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

}
