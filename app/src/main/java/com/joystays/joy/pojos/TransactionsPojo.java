package com.joystays.joy.pojos;

import java.util.List;

public class TransactionsPojo {

    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : [{"id":"1","owner_id":"1","name":"Prince Hostels Madhapur","address":"Beside that, Below this, Opposite that","lat":"17.4486","lng":"78.3908","image":"storage/profile_pics/5da5bc7022576.png","ac":"yes","non_ac":"yes","recommended":"yes","hostel_type":"boys","terms_conditions":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.","delete_status":"1","created_on":"2019-10-22 03:00:00","modified_on":null,"BID":"568956","hostel_id":"1","user_id":"1","floor_id":"0","room_id":"1","bed_id":"1","share":"1","paid_amount":"7200","paid_date":"31 Oct 2019","payment_mode":"cash","package_type":"monthly","daily_basis":"450","monthly_basis":"5200","favourite":"yes","ratings_given":"yes","ratings_count":"1","ratings":"4.5"},{"id":"3","owner_id":"1","name":"Prince Hostels Madhapur","address":"Beside that, Below this, Opposite that","lat":"17.4486","lng":"78.3908","image":"storage/profile_pics/5da5bc7022576.png","ac":"yes","non_ac":"yes","recommended":"yes","hostel_type":"boys","terms_conditions":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.","delete_status":"1","created_on":"2019-12-05 15:04:50","modified_on":null,"BID":"184555","hostel_id":"1","user_id":"1","floor_id":"1","room_id":"1","bed_id":"1","share":"1","paid_amount":"11700","paid_date":"05 Dec 2019","payment_mode":"cash","package_type":"daily","daily_basis":"450","monthly_basis":"5200","favourite":"yes","ratings_given":"yes","ratings_count":"1","ratings":"4.5"},{"id":"4","owner_id":"1","name":"Prince Hostels Madhapur","address":"Beside that, Below this, Opposite that","lat":"17.4486","lng":"78.3908","image":"storage/profile_pics/5da5bc7022576.png","ac":"yes","non_ac":"yes","recommended":"yes","hostel_type":"boys","terms_conditions":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.","delete_status":"1","created_on":"2019-12-05 15:05:37","modified_on":null,"BID":"801991","hostel_id":"1","user_id":"1","floor_id":"1","room_id":"1","bed_id":"1","share":"1","paid_amount":"11700","paid_date":"05 Dec 2019","payment_mode":"cash","package_type":"daily","daily_basis":"450","monthly_basis":"5200","favourite":"yes","ratings_given":"yes","ratings_count":"1","ratings":"4.5"},{"id":"5","owner_id":"1","name":"Prince Hostels Madhapur","address":"Beside that, Below this, Opposite that","lat":"17.4486","lng":"78.3908","image":"storage/profile_pics/5da5bc7022576.png","ac":"yes","non_ac":"yes","recommended":"yes","hostel_type":"boys","terms_conditions":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.","delete_status":"1","created_on":"2019-12-05 15:46:05","modified_on":null,"BID":"174930","hostel_id":"1","user_id":"1","floor_id":"1","room_id":"1","bed_id":"1","share":"1","paid_amount":"11700","paid_date":"05 Dec 2019","payment_mode":"cash","package_type":"daily","daily_basis":"450","monthly_basis":"5200","favourite":"yes","ratings_given":"yes","ratings_count":"1","ratings":"4.5"}]
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
         * owner_id : 1
         * name : Prince Hostels Madhapur
         * address : Beside that, Below this, Opposite that
         * lat : 17.4486
         * lng : 78.3908
         * image : storage/profile_pics/5da5bc7022576.png
         * ac : yes
         * non_ac : yes
         * recommended : yes
         * hostel_type : boys
         * terms_conditions : Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
         * delete_status : 1
         * created_on : 2019-10-22 03:00:00
         * modified_on : null
         * BID : 568956
         * hostel_id : 1
         * user_id : 1
         * floor_id : 0
         * room_id : 1
         * bed_id : 1
         * share : 1
         * paid_amount : 7200
         * paid_date : 31 Oct 2019
         * payment_mode : cash
         * package_type : monthly
         * daily_basis : 450
         * monthly_basis : 5200
         * favourite : yes
         * ratings_given : yes
         * ratings_count : 1
         * ratings : 4.5
         */

        private String id;
        private String owner_id;
        private String name;
        private String address;
        private String lat;
        private String lng;
        private String image;
        private String ac;
        private String non_ac;
        private String recommended;
        private String hostel_type;
        private String terms_conditions;
        private String delete_status;
        private String created_on;
        private Object modified_on;
        private String BID;
        private String hostel_id;
        private String user_id;
        private String floor_id;
        private String room_id;
        private String bed_id;
        private String share;
        private String paid_amount;
        private String paid_date;
        private String payment_mode;
        private String package_type;
        private String daily_basis;
        private String monthly_basis;
        private String favourite;
        private String ratings_given;
        private String ratings_count;
        private String ratings;
        private String users_name;
        private String transaction_id;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
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

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getAc() {
            return ac;
        }

        public void setAc(String ac) {
            this.ac = ac;
        }

        public String getNon_ac() {
            return non_ac;
        }

        public void setNon_ac(String non_ac) {
            this.non_ac = non_ac;
        }

        public String getRecommended() {
            return recommended;
        }

        public void setRecommended(String recommended) {
            this.recommended = recommended;
        }

        public String getHostel_type() {
            return hostel_type;
        }

        public void setHostel_type(String hostel_type) {
            this.hostel_type = hostel_type;
        }

        public String getTerms_conditions() {
            return terms_conditions;
        }

        public void setTerms_conditions(String terms_conditions) {
            this.terms_conditions = terms_conditions;
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

        public Object getModified_on() {
            return modified_on;
        }

        public void setModified_on(Object modified_on) {
            this.modified_on = modified_on;
        }

        public String getBID() {
            return BID;
        }

        public void setBID(String BID) {
            this.BID = BID;
        }

        public String getHostel_id() {
            return hostel_id;
        }

        public void setHostel_id(String hostel_id) {
            this.hostel_id = hostel_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
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

        public String getPaid_amount() {
            return paid_amount;
        }

        public void setPaid_amount(String paid_amount) {
            this.paid_amount = paid_amount;
        }

        public String getPaid_date() {
            return paid_date;
        }

        public void setPaid_date(String paid_date) {
            this.paid_date = paid_date;
        }

        public String getPayment_mode() {
            return payment_mode;
        }

        public void setPayment_mode(String payment_mode) {
            this.payment_mode = payment_mode;
        }

        public String getPackage_type() {
            return package_type;
        }

        public void setPackage_type(String package_type) {
            this.package_type = package_type;
        }

        public String getDaily_basis() {
            return daily_basis;
        }

        public void setDaily_basis(String daily_basis) {
            this.daily_basis = daily_basis;
        }

        public String getMonthly_basis() {
            return monthly_basis;
        }

        public void setMonthly_basis(String monthly_basis) {
            this.monthly_basis = monthly_basis;
        }

        public String getFavourite() {
            return favourite;
        }

        public void setFavourite(String favourite) {
            this.favourite = favourite;
        }

        public String getRatings_given() {
            return ratings_given;
        }

        public void setRatings_given(String ratings_given) {
            this.ratings_given = ratings_given;
        }

        public String getRatings_count() {
            return ratings_count;
        }

        public void setRatings_count(String ratings_count) {
            this.ratings_count = ratings_count;
        }

        public String getRatings() {
            return ratings;
        }

        public void setRatings(String ratings) {
            this.ratings = ratings;
        }

        public String getUsers_name() {
            return users_name;
        }

        public void setUsers_name(String users_name) {
            this.users_name = users_name;
        }

        public String getTransaction_id() {
            return transaction_id;
        }

        public void setTransaction_id(String transaction_id) {
            this.transaction_id = transaction_id;
        }
    }
}
