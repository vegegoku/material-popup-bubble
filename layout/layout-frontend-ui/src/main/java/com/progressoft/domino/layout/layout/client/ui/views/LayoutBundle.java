package com.progressoft.domino.layout.layout.client.ui.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;

public interface LayoutBundle extends ClientBundle{

    interface Style extends CssResource {
        String Layout();
    }

    @Source("css/Layout.gss")
    Style style();
}