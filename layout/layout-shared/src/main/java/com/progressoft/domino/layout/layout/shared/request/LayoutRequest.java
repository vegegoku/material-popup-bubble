package com.progressoft.domino.layout.layout.shared.request;

import com.progressoft.brix.domino.api.shared.request.RequestBean;

public class LayoutRequest extends RequestBean {

    private String message;

    public LayoutRequest() {
    }

    public LayoutRequest(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
