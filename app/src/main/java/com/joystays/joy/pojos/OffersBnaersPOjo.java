package com.joystays.joy.pojos;

import java.util.List;

public class OffersBnaersPOjo {


    /**
     * status : true
     * message : Data Fetched Successfully!
     * response : [{"id":"3","image":"storage/Tulips1.jpg","created_on":"2020-12-05 20:10:52"}]
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
         * id : 3
         * image : storage/Tulips1.jpg
         * created_on : 2020-12-05 20:10:52
         */

        private String id;
        private String image;
        private String created_on;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getCreated_on() {
            return created_on;
        }

        public void setCreated_on(String created_on) {
            this.created_on = created_on;
        }
    }
}
