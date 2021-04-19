package com.joystays.joy.pojos;

import java.util.List;

public class BookingsPojo {

    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : [{"id":"1","owner_id":"1","name":"Prince Hostels Madhapur","address":"Beside that, Below this, Opposite that","lat":"17.4486","lng":"78.3908","image":"storage/profile_pics/5da5bc7022576.png","ac":"yes","non_ac":"yes","recommended":"yes","hostel_type":"boys","terms_conditions":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.","delete_status":"1","created_on":"2019-10-15 18:02:48","modified_on":null,"daily_basis":"5200","monthly_basis":"5200","favourite":"yes","ratings_given":"yes","ratings_count":"1","ratings":"4.5"}]
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
         * created_on : 2019-10-15 18:02:48
         * modified_on : null
         * daily_basis : 5200
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
        private String daily_basis;
        private String monthly_basis;
        private String favourite;
        private String ratings_given;
        private String ratings_count;
        private String ratings;

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
    }
}
