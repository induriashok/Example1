package com.joystays.joy.network;

import android.content.Context;

import java.util.ArrayList;
import java.util.prefs.Preferences;

/**
 * Created by Prasad on 05/07/2017.
 */
public abstract class BaseApiModel implements APICallback {

    protected Context context;
    protected APIResponseCallback apiResponseCallback;
    protected APIHandler apiHandler;
    protected Preferences preferences;
    //    protected String x_auth_token = null;
    /**
     * Requests queue to keeps pending requests.
     */
    private ArrayList<APIHandler> requestsQueue;

    /**
     * Maintains's the state of queue process
     */
    private boolean isQueueInProgress = false;

    public BaseApiModel(Context context) {

        //initializes the requests
        requestsQueue = new ArrayList<>();
    }


    /**
     * Set's {@link APIHandler} to Request Queue
     *
     * @param apiHandler
     */
    protected void addAPIHandlerToQueue(APIHandler apiHandler) {
        if (requestsQueue != null) {
            requestsQueue.add(apiHandler);
        }
    }

    /**
     * Fetch the Request Queue
     *
     * @return
     */
    private ArrayList<APIHandler> getAPIHandlerToQueue() {
        return requestsQueue;
    }

    /**
     * Execute the Request Queue
     */
    protected synchronized void execute() {
        if (requestsQueue != null) {
            if (!isQueueInProgress || !requestsQueue.isEmpty()) {
                //Process the request from queue
                APIHandler apiHandler = requestsQueue.get(0);
                if (apiHandler != null) {
                    //Before making hit to server set isQueueInProgress to true
                    isQueueInProgress = true;
                    //     apiHandler.requestAPI();
                } else {
                    isQueueInProgress = false;
                }
            } else {
                isQueueInProgress = false;
            }
        } else {
            isQueueInProgress = false;
        }
    }

    /**
     * On Success of Queue Handle Requested API
     */
    protected void onSuccessOperateQueue() {
        if (requestsQueue != null && !requestsQueue.isEmpty()) {
            //Remove the reference of request wht get executed successfully
            requestsQueue.remove(0);
            //go to request next in queue.
            // execute();
        }
    }

    /**
     * On Failure of Queue Manage Failed Request API
     */
    protected void onFaliureOperateQueue() {
        if (requestsQueue != null && !requestsQueue.isEmpty()) {
            APIHandler apiHandler = requestsQueue.get(0);
            if (apiHandler != null) {
                //Process the request from queue
                //  apiHandler.requestAPI(params);
            }
        }
    }

    @Override
    public void onAPISuccessResponse(int requestId, String responseString) {

    }

    @Override
    public void onAPIFailureResponse(int requestId, String errorString) {

    }
}
