package com.joystays.joy.pojos;

public class PaymentTokenPojo {

    /**
     * status : true
     * message : Token generated
     * response : {"order_id":"JOYSTAYS198764","token":"AR9JCN4MzUIJiOicGbhJCLiQ1VKJiOiAXe0Jye.DNQfiQzNwIjY2MzN0YjNlVjI6ICdsF2cfJCL2AjMzUzM2gTNxojIwhXZiwiIS5USiojI5NmblJnc1NkclRmcvJCLiEjI6ICduV3btFkclRmcvJCLiQjN3gTOxMVWBR1UZ9kSiojIklkclRmcvJye.YbuG3XdoQc8kwLDuPOoaSfN8eBdE_ENLkkD747CAJ2sNeD4YHbSdwHpp0bTTOPAO_l"}
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
         * order_id : JOYSTAYS198764
         * token : AR9JCN4MzUIJiOicGbhJCLiQ1VKJiOiAXe0Jye.DNQfiQzNwIjY2MzN0YjNlVjI6ICdsF2cfJCL2AjMzUzM2gTNxojIwhXZiwiIS5USiojI5NmblJnc1NkclRmcvJCLiEjI6ICduV3btFkclRmcvJCLiQjN3gTOxMVWBR1UZ9kSiojIklkclRmcvJye.YbuG3XdoQc8kwLDuPOoaSfN8eBdE_ENLkkD747CAJ2sNeD4YHbSdwHpp0bTTOPAO_l
         */

        private String order_id;
        private String token;

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
