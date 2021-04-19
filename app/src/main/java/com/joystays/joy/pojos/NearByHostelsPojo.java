package com.joystays.joy.pojos;

import java.util.List;

public class NearByHostelsPojo {

    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : [{"id":"4","owner_id":"2","name":"Prince Hostels secunderabad","address":"Beside that, Below this, Opposite that","lat":"17.4399","lng":"78.4983","image":"storage/profile_pics/5da99d76caf19.png","ac":"yes","non_ac":"yes","recommended":"yes","delete_status":"1","created_on":"2019-10-18 16:39:42","modified_on":null,"daily_basis":null,"monthly_basis":null,"favourite":"no","ratings_given":"no","ratings_count":"0","ratings":null,"distance":"0.00"},{"id":"2","owner_id":"1","name":"Prince Hostels Jubilee Hills","address":"Beside that, Below this, Opposite that","lat":"17.4326","lng":"78.4071","image":"storage/profile_pics/5da5bc7022626.png","ac":"yes","non_ac":"yes","recommended":"no","delete_status":"1","created_on":"2019-10-15 18:02:48","modified_on":null,"daily_basis":null,"monthly_basis":null,"favourite":"no","ratings_given":"no","ratings_count":"0","ratings":null,"distance":"9.71"},{"id":"1","owner_id":"1","name":"Prince Hostels Madhapur","address":"Beside that, Below this, Opposite that","lat":"17.4486","lng":"78.3908","image":"storage/profile_pics/5da5bc7022576.png","ac":"yes","non_ac":"yes","recommended":"yes","delete_status":"1","created_on":"2019-10-15 18:02:48","modified_on":null,"daily_basis":"5200","monthly_basis":"5200","favourite":"yes","ratings_given":"yes","ratings_count":"1","ratings":"4.5","distance":"11.44"},{"id":"3","owner_id":"2","name":"Prince Hostels Hitech City","address":"Beside that, Below this, Opposite that","lat":"17.4435","lng":"78.3772","image":"storage/profile_pics/5da99d76c4a80.png","ac":"yes","non_ac":"yes","recommended":"no","delete_status":"1","created_on":"2019-10-18 16:39:42","modified_on":null,"daily_basis":null,"monthly_basis":null,"favourite":"no","ratings_given":"no","ratings_count":"0","ratings":null,"distance":"12.85"}]
     * recommended_hostels : [{"id":"4","owner_id":"2","name":"Prince Hostels secunderabad","address":"Beside that, Below this, Opposite that","lat":"17.4399","lng":"78.4983","image":"storage/profile_pics/5da99d76caf19.png","ac":"yes","non_ac":"yes","recommended":"yes","delete_status":"1","created_on":"2019-10-18 16:39:42","modified_on":null,"daily_basis":null,"monthly_basis":null,"favourite":"no","ratings_given":"no","ratings_count":"0","ratings":null,"distance":"0.00"},{"id":"1","owner_id":"1","name":"Prince Hostels Madhapur","address":"Beside that, Below this, Opposite that","lat":"17.4486","lng":"78.3908","image":"storage/profile_pics/5da5bc7022576.png","ac":"yes","non_ac":"yes","recommended":"yes","delete_status":"1","created_on":"2019-10-15 18:02:48","modified_on":null,"daily_basis":"5200","monthly_basis":"5200","favourite":"yes","ratings_given":"yes","ratings_count":"1","ratings":"4.5","distance":"11.44"}]
     */

    private boolean status;
    private String message;
    private List<ResponseBean> response;
    private List<RecommendedHostelsBean> recommended_hostels;

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

    public List<RecommendedHostelsBean> getRecommended_hostels() {
        return recommended_hostels;
    }

    public void setRecommended_hostels(List<RecommendedHostelsBean> recommended_hostels) {
        this.recommended_hostels = recommended_hostels;
    }

    public static class ResponseBean {
        /**
         * id : 4
         * owner_id : 2
         * name : Prince Hostels secunderabad
         * address : Beside that, Below this, Opposite that
         * lat : 17.4399
         * lng : 78.4983
         * image : storage/profile_pics/5da99d76caf19.png
         * ac : yes
         * non_ac : yes
         * recommended : yes
         * delete_status : 1
         * created_on : 2019-10-18 16:39:42
         * modified_on : null
         * daily_basis : null
         * monthly_basis : null
         * favourite : no
         * ratings_given : no
         * ratings_count : 0
         * ratings : null
         * distance : 0.00
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
        private String delete_status;
        private String created_on;
        private Object modified_on;
        private String daily_basis;
        private String monthly_basis;
        private String favourite;
        private String ratings_given;
        private String ratings_count;
        private String ratings;
        private String distance;
        private String hostel_type;
        private String exact_distance;

        public String getHostel_type() {
            return hostel_type;
        }

        public void setHostel_type(String hostel_type) {
            this.hostel_type = hostel_type;
        }

        public String getDaily_basis() {
            return daily_basis;
        }

        public void setDaily_basis(String daily_basis) {
            this.daily_basis = daily_basis;
        }

        public String getRatings() {
            return ratings;
        }

        public void setRatings(String ratings) {
            this.ratings = ratings;
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


        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getExact_distance() {
            return exact_distance;
        }

        public void setExact_distance(String exact_distance) {
            this.exact_distance = exact_distance;
        }
    }

    public static class RecommendedHostelsBean {
        /**
         * id : 4
         * owner_id : 2
         * name : Prince Hostels secunderabad
         * address : Beside that, Below this, Opposite that
         * lat : 17.4399
         * lng : 78.4983
         * image : storage/profile_pics/5da99d76caf19.png
         * ac : yes
         * non_ac : yes
         * recommended : yes
         * delete_status : 1
         * created_on : 2019-10-18 16:39:42
         * modified_on : null
         * daily_basis : null
         * monthly_basis : null
         * favourite : no
         * ratings_given : no
         * ratings_count : 0
         * ratings : null
         * distance : 0.00
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
        private String delete_status;
        private String created_on;
        private Object modified_on;
        private Object daily_basis;
        private Object monthly_basis;
        private String favourite;
        private String ratings_given;
        private String ratings_count;
        private Object ratings;
        private String distance;

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

        public Object getDaily_basis() {
            return daily_basis;
        }

        public void setDaily_basis(Object daily_basis) {
            this.daily_basis = daily_basis;
        }

        public Object getMonthly_basis() {
            return monthly_basis;
        }

        public void setMonthly_basis(Object monthly_basis) {
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

        public Object getRatings() {
            return ratings;
        }

        public void setRatings(Object ratings) {
            this.ratings = ratings;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }
    }
}
