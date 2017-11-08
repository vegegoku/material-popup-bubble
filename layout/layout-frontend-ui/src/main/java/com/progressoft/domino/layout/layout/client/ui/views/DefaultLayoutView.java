package com.progressoft.domino.layout.layout.client.ui.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.progressoft.brix.domino.api.client.annotations.UiView;
import com.progressoft.domino.layout.layout.client.presenters.LayoutPresenter;
import com.progressoft.domino.layout.layout.client.views.LayoutView;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.Position;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialPanel;

@UiView(presentable = LayoutPresenter.class)
public class DefaultLayoutView extends Composite implements LayoutView{

    interface DefaultLayoutViewUiBinder extends UiBinder<MaterialPanel, DefaultLayoutView> {
    }

    private static DefaultLayoutViewUiBinder ourUiBinder = GWT.create(DefaultLayoutViewUiBinder.class);

    @UiField
    MaterialLink help;

    @UiField
    MaterialLink apps;

    @UiField
    MaterialLink userProfile;

    @UiField
    MaterialLink more;

    @UiField
    MaterialPopupBubble dominoPopup;

    public DefaultLayoutView() {
        initWidget(ourUiBinder.createAndBindUi(this));
        RootPanel.get().add(this);
    }

    @UiHandler({"help","apps","userProfile","more"})
    void onClick(ClickEvent event){
        dominoPopup.showRelativeTo((MaterialWidget) event.getSource());
    }
}