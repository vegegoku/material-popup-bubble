package com.progressoft.domino.layout.layout.shared.response;

import com.progressoft.brix.domino.api.shared.request.ResponseBean;

public class LayoutResponse extends ResponseBean {

    private String serverMessage;

    public LayoutResponse() {
    }

    public LayoutResponse(String serverMessage) {
        this.serverMessage = serverMessage;
    }

    public String getServerMessage() {
        return serverMessage;
    }

    public void setServerMessage(String serverMessage) {
        this.serverMessage = serverMessage;
    }
}
