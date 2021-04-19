package com.joystays.joy.network;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public interface APIResponseCallback {
    void onSuccessResponse(int requestId, @NonNull String responseString,
                           @Nullable Object object);

    void onFailureResponse(int requestId, @NonNull String errorString);
}
