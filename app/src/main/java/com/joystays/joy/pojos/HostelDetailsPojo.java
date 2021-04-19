package com.joystays.joy.pojos;

import java.util.List;

public class HostelDetailsPojo {

    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : {"id":"1","owner_id":"1","name":"Prince Hostels Madhapur","address":"Beside that, Below this, Opposite that","lat":"17.4486","lng":"78.3908","image":"storage/profile_pics/5da5bc7022576.png","ac":"yes","non_ac":"yes","recommended":"yes","hostel_type":"boys","terms_conditions":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.","delete_status":"1","created_on":"2019-10-15 18:02:48","modified_on":null,"daily_basis":"450","monthly_basis":"6200","favourite":"yes","ratings_given":"no","ratings_count":"0","ratings":{"overall_ratings":"3","ac_ratings":"3","ac_count":"13","breakfast_ratings":"1","breakfast_count":"6","wifi_ratings":"1","wifi_count":"6","tv_ratings":"1","tv_count":"6","washroom_ratings":"1","washroom_count":"6","hostelstaff_ratings":"1","hostelstaff_count":"6"},"prices":[{"share_alpha":"Single","id":"1","owner_id":"1","hostel_id":"1","share":"1","owner":"500","advance":"2500","refundable_amount":"2000","joy_price":"450","room_type":"ac","delete_status":"1","package_type":"daily","created_on":"2019-10-22 03:03:02","modified_on":"0000-00-00 00:00:00"},{"share_alpha":"Two","id":"2","owner_id":"1","hostel_id":"1","share":"2","owner":"7500","advance":"2000","refundable_amount":"1500","joy_price":"7200","room_type":"ac","delete_status":"1","package_type":"daily","created_on":"2019-10-22 02:03:02","modified_on":"0000-00-00 00:00:00"},{"share_alpha":"Three","id":"3","owner_id":"1","hostel_id":"1","share":"3","owner":"6500","advance":"2500","refundable_amount":"2000","joy_price":"6200","room_type":"ac","delete_status":"1","package_type":"daily","created_on":"2019-10-22 03:03:02","modified_on":"0000-00-00 00:00:00"},{"share_alpha":"Four","id":"4","owner_id":"1","hostel_id":"1","share":"4","owner":"5500","advance":"2000","refundable_amount":"1500","joy_price":"5200","room_type":"ac","delete_status":"1","package_type":"daily","created_on":"2019-10-22 02:03:02","modified_on":"0000-00-00 00:00:00"}],"facilities":[{"id":"4","owner_id":"1","hostel_id":"1","facility_id":"4","delete_status":"0","created_on":"2019-10-21 01:04:06","updated_on":"0000-00-00 00:00:00","title":"CC.Camera","icon":"storage/images/cccamera.jpg","description":"Camera Security"},{"id":"3","owner_id":"1","hostel_id":"1","facility_id":"3","delete_status":"1","created_on":"2019-10-21 02:02:06","updated_on":"0000-00-00 00:00:00","title":"Television","icon":"storage/images/tvh.jpg","description":"Television with no of channels"},{"id":"2","owner_id":"1","hostel_id":"1","facility_id":"2","delete_status":"1","created_on":"2019-10-21 01:04:06","updated_on":"0000-00-00 00:00:00","title":"Parking","icon":"storage/images/h7.jpg","description":"Lot of space for Parking"},{"id":"1","owner_id":"1","hostel_id":"1","facility_id":"1","delete_status":"1","created_on":"2019-10-21 02:02:06","updated_on":"0000-00-00 00:00:00","title":"Free Wifi","icon":"storage/images/wifi.jpg","description":"Free wifi"}],"floors":[{"floor":"0","floorname":"Ground Floor"},{"floor":"1","floorname":"First Floor"}],"owner_details":{"id":"1","name":"John Doe","hostel_name":"Prince Hostels","mobile":"9999999999","password":"e10adc3949ba59abbe56e057f20f883e","website":"www.princehostels.com","address":"Beside that, Below this, Opposite that","description":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.","languages":"Telugu, Hindi, English","profile_pic":null,"status":"1","delete_status":"1","created_on":"2019-10-15 18:02:48","modified_on":"2020-01-06 13:23:52"}}
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
         * daily_basis : 450
         * monthly_basis : 6200
         * favourite : yes
         * ratings_given : no
         * ratings_count : 0
         * ratings : {"overall_ratings":"3","ac_ratings":"3","ac_count":"13","breakfast_ratings":"1","breakfast_count":"6","wifi_ratings":"1","wifi_count":"6","tv_ratings":"1","tv_count":"6","washroom_ratings":"1","washroom_count":"6","hostelstaff_ratings":"1","hostelstaff_count":"6"}
         * prices : [{"share_alpha":"Single","id":"1","owner_id":"1","hostel_id":"1","share":"1","owner":"500","advance":"2500","refundable_amount":"2000","joy_price":"450","room_type":"ac","delete_status":"1","package_type":"daily","created_on":"2019-10-22 03:03:02","modified_on":"0000-00-00 00:00:00"},{"share_alpha":"Two","id":"2","owner_id":"1","hostel_id":"1","share":"2","owner":"7500","advance":"2000","refundable_amount":"1500","joy_price":"7200","room_type":"ac","delete_status":"1","package_type":"daily","created_on":"2019-10-22 02:03:02","modified_on":"0000-00-00 00:00:00"},{"share_alpha":"Three","id":"3","owner_id":"1","hostel_id":"1","share":"3","owner":"6500","advance":"2500","refundable_amount":"2000","joy_price":"6200","room_type":"ac","delete_status":"1","package_type":"daily","created_on":"2019-10-22 03:03:02","modified_on":"0000-00-00 00:00:00"},{"share_alpha":"Four","id":"4","owner_id":"1","hostel_id":"1","share":"4","owner":"5500","advance":"2000","refundable_amount":"1500","joy_price":"5200","room_type":"ac","delete_status":"1","package_type":"daily","created_on":"2019-10-22 02:03:02","modified_on":"0000-00-00 00:00:00"}]
         * facilities : [{"id":"4","owner_id":"1","hostel_id":"1","facility_id":"4","delete_status":"0","created_on":"2019-10-21 01:04:06","updated_on":"0000-00-00 00:00:00","title":"CC.Camera","icon":"storage/images/cccamera.jpg","description":"Camera Security"},{"id":"3","owner_id":"1","hostel_id":"1","facility_id":"3","delete_status":"1","created_on":"2019-10-21 02:02:06","updated_on":"0000-00-00 00:00:00","title":"Television","icon":"storage/images/tvh.jpg","description":"Television with no of channels"},{"id":"2","owner_id":"1","hostel_id":"1","facility_id":"2","delete_status":"1","created_on":"2019-10-21 01:04:06","updated_on":"0000-00-00 00:00:00","title":"Parking","icon":"storage/images/h7.jpg","description":"Lot of space for Parking"},{"id":"1","owner_id":"1","hostel_id":"1","facility_id":"1","delete_status":"1","created_on":"2019-10-21 02:02:06","updated_on":"0000-00-00 00:00:00","title":"Free Wifi","icon":"storage/images/wifi.jpg","description":"Free wifi"}]
         * floors : [{"floor":"0","floorname":"Ground Floor"},{"floor":"1","floorname":"First Floor"}]
         * owner_details : {"id":"1","name":"John Doe","hostel_name":"Prince Hostels","mobile":"9999999999","password":"e10adc3949ba59abbe56e057f20f883e","website":"www.princehostels.com","address":"Beside that, Below this, Opposite that","description":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.","languages":"Telugu, Hindi, English","profile_pic":null,"status":"1","delete_status":"1","created_on":"2019-10-15 18:02:48","modified_on":"2020-01-06 13:23:52"}
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
        private String modified_on;
        private String daily_basis;
        private String monthly_basis;
        private String favourite;
        private String ratings_given;
        private String ratings_count;
        private String all_ratings;
        private RatingsBean ratings;
        private OwnerDetailsBean owner_details;
        private List<PricesBean> prices;
        private List<FacilitiesBean> facilities;
        private List<FloorsBean> floors;
        private List<HostelBannersBean> hostel_banners;

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

        public String getModified_on() {
            return modified_on;
        }

        public void setModified_on(String modified_on) {
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

        public RatingsBean getRatings() {
            return ratings;
        }

        public void setRatings(RatingsBean ratings) {
            this.ratings = ratings;
        }

        public OwnerDetailsBean getOwner_details() {
            return owner_details;
        }

        public void setOwner_details(OwnerDetailsBean owner_details) {
            this.owner_details = owner_details;
        }

        public List<PricesBean> getPrices() {
            return prices;
        }

        public void setPrices(List<PricesBean> prices) {
            this.prices = prices;
        }

        public List<FacilitiesBean> getFacilities() {
            return facilities;
        }

        public void setFacilities(List<FacilitiesBean> facilities) {
            this.facilities = facilities;
        }

        public List<FloorsBean> getFloors() {
            return floors;
        }

        public void setFloors(List<FloorsBean> floors) {
            this.floors = floors;
        }


        public List<HostelBannersBean> getHostel_banners() {
            return hostel_banners;
        }

        public void setHostel_banners(List<HostelBannersBean> hostel_banners) {
            this.hostel_banners = hostel_banners;
        }

        public String getAll_ratings() {
            return all_ratings;
        }

        public void setAll_ratings(String all_ratings) {
            this.all_ratings = all_ratings;
        }

        public static class RatingsBean {
            /**
             * overall_ratings : 3
             * ac_ratings : 3
             * ac_count : 13
             * breakfast_ratings : 1
             * breakfast_count : 6
             * wifi_ratings : 1
             * wifi_count : 6
             * tv_ratings : 1
             * tv_count : 6
             * washroom_ratings : 1
             * washroom_count : 6
             * hostelstaff_ratings : 1
             * hostelstaff_count : 6
             */

            private String overall_ratings;
            private String ac_ratings;
            private String ac_count;
            private String breakfast_ratings;
            private String breakfast_count;
            private String wifi_ratings;
            private String wifi_count;
            private String tv_ratings;
            private String tv_count;
            private String washroom_ratings;
            private String washroom_count;
            private String hostelstaff_ratings;
            private String hostelstaff_count;
            private String property_manager_response_ratings;
            private String room_status_ratings;

            public String getProperty_manager_response_ratings() {
                return property_manager_response_ratings;
            }

            public void setProperty_manager_response_ratings(String property_manager_response_ratings) {
                this.property_manager_response_ratings = property_manager_response_ratings;
            }

            public String getRoom_status_ratings() {
                return room_status_ratings;
            }

            public void setRoom_status_ratings(String room_status_ratings) {
                this.room_status_ratings = room_status_ratings;
            }

            public String getOverall_ratings() {
                return overall_ratings;
            }

            public void setOverall_ratings(String overall_ratings) {
                this.overall_ratings = overall_ratings;
            }

            public String getAc_ratings() {
                return ac_ratings;
            }

            public void setAc_ratings(String ac_ratings) {
                this.ac_ratings = ac_ratings;
            }

            public String getAc_count() {
                return ac_count;
            }

            public void setAc_count(String ac_count) {
                this.ac_count = ac_count;
            }

            public String getBreakfast_ratings() {
                return breakfast_ratings;
            }

            public void setBreakfast_ratings(String breakfast_ratings) {
                this.breakfast_ratings = breakfast_ratings;
            }

            public String getBreakfast_count() {
                return breakfast_count;
            }

            public void setBreakfast_count(String breakfast_count) {
                this.breakfast_count = breakfast_count;
            }

            public String getWifi_ratings() {
                return wifi_ratings;
            }

            public void setWifi_ratings(String wifi_ratings) {
                this.wifi_ratings = wifi_ratings;
            }

            public String getWifi_count() {
                return wifi_count;
            }

            public void setWifi_count(String wifi_count) {
                this.wifi_count = wifi_count;
            }

            public String getTv_ratings() {
                return tv_ratings;
            }

            public void setTv_ratings(String tv_ratings) {
                this.tv_ratings = tv_ratings;
            }

            public String getTv_count() {
                return tv_count;
            }

            public void setTv_count(String tv_count) {
                this.tv_count = tv_count;
            }

            public String getWashroom_ratings() {
                return washroom_ratings;
            }

            public void setWashroom_ratings(String washroom_ratings) {
                this.washroom_ratings = washroom_ratings;
            }

            public String getWashroom_count() {
                return washroom_count;
            }

            public void setWashroom_count(String washroom_count) {
                this.washroom_count = washroom_count;
            }

            public String getHostelstaff_ratings() {
                return hostelstaff_ratings;
            }

            public void setHostelstaff_ratings(String hostelstaff_ratings) {
                this.hostelstaff_ratings = hostelstaff_ratings;
            }

            public String getHostelstaff_count() {
                return hostelstaff_count;
            }

            public void setHostelstaff_count(String hostelstaff_count) {
                this.hostelstaff_count = hostelstaff_count;
            }
        }

        public static class OwnerDetailsBean {
            /**
             * id : 1
             * name : John Doe
             * hostel_name : Prince Hostels
             * mobile : 9999999999
             * password : e10adc3949ba59abbe56e057f20f883e
             * website : www.princehostels.com
             * address : Beside that, Below this, Opposite that
             * description : Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
             * languages : Telugu, Hindi, English
             * profile_pic : null
             * status : 1
             * delete_status : 1
             * created_on : 2019-10-15 18:02:48
             * modified_on : 2020-01-06 13:23:52
             */

            private String id;
            private String name;
            private String hostel_name;
            private String mobile;
            private String password;
            private String website;
            private String address;
            private String description;
            private String languages;
            private String profile_pic;
            private String status;
            private String delete_status;
            private String created_on;
            private String modified_on;

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

            public String getLanguages() {
                return languages;
            }

            public void setLanguages(String languages) {
                this.languages = languages;
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

            public String getModified_on() {
                return modified_on;
            }

            public void setModified_on(String modified_on) {
                this.modified_on = modified_on;
            }
        }

        public static class PricesBean {
            /**
             * share_alpha : Single
             * id : 1
             * owner_id : 1
             * hostel_id : 1
             * share : 1
             * owner : 500
             * advance : 2500
             * refundable_amount : 2000
             * joy_price : 450
             * room_type : ac
             * delete_status : 1
             * package_type : daily
             * created_on : 2019-10-22 03:03:02
             * modified_on : 0000-00-00 00:00:00
             */

            private String share_alpha;
            private String id;
            private String owner_id;
            private String hostel_id;
            private String share;
            private String owner;
            private String advance;
            private String refundable_amount;
            private String joy_price;
            private String room_type;
            private String delete_status;
            private String package_type;
            private String created_on;
            private String modified_on;

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

            public String getDelete_status() {
                return delete_status;
            }

            public void setDelete_status(String delete_status) {
                this.delete_status = delete_status;
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

            public String getModified_on() {
                return modified_on;
            }

            public void setModified_on(String modified_on) {
                this.modified_on = modified_on;
            }
        }

        public static class FacilitiesBean {
            /**
             * id : 4
             * owner_id : 1
             * hostel_id : 1
             * facility_id : 4
             * delete_status : 0
             * created_on : 2019-10-21 01:04:06
             * updated_on : 0000-00-00 00:00:00
             * title : CC.Camera
             * icon : storage/images/cccamera.jpg
             * description : Camera Security
             */

            private String id;
            private String owner_id;
            private String hostel_id;
            private String facility_id;
            private String delete_status;
            private String created_on;
            private String updated_on;
            private String title;
            private String icon;
            private String description;

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

            public String getFacility_id() {
                return facility_id;
            }

            public void setFacility_id(String facility_id) {
                this.facility_id = facility_id;
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

            public String getUpdated_on() {
                return updated_on;
            }

            public void setUpdated_on(String updated_on) {
                this.updated_on = updated_on;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }
        }

        public static class FloorsBean {
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

        public static class HostelBannersBean {
            /**
             * id : 3
             * owner_id : 2
             * hostel_id : 2
             * banner : storage/banner_img/Doha-City-4K-Wallpaper-3840x21601.jpg
             * delete_status : 1
             * created_on : 2020-01-30 18:34:53
             * modified_on : 0000-00-00 00:00:00
             */

            private String id;
            private String owner_id;
            private String hostel_id;
            private String banner;
            private String delete_status;
            private String created_on;
            private String modified_on;

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

            public String getBanner() {
                return banner;
            }

            public void setBanner(String banner) {
                this.banner = banner;
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

            public String getModified_on() {
                return modified_on;
            }

            public void setModified_on(String modified_on) {
                this.modified_on = modified_on;
            }
        }


    }
}
