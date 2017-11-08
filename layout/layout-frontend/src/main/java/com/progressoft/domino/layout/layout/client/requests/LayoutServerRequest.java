package com.progressoft.domino.layout.layout.client.requests;

import com.progressoft.brix.domino.api.client.request.ServerRequest;
import com.progressoft.domino.layout.layout.shared.response.LayoutResponse;
import com.progressoft.domino.layout.layout.shared.request.LayoutRequest;
import com.progressoft.brix.domino.api.client.annotations.Request;
import com.progressoft.brix.domino.api.client.annotations.Path;

@Request
@Path("LayoutRequest")
public class LayoutServerRequest extends ServerRequest<LayoutRequest, LayoutResponse> {
    public LayoutServerRequest(LayoutRequest requestBean) {
        super(requestBean);
    }
}
