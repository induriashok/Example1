package com.joystays.joy.network;

/**
 * Created by prasad on 04/07/2017.
 */
public interface APICallback {
    void onAPISuccessResponse(int requestId, String responseString);

    void onAPIFailureResponse(int requestId, String errorString);
}
