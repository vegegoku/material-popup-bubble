package com.progressoft.domino.layout.layout.client.ui.views;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Document;
import com.google.gwt.event.logical.shared.*;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import gwt.material.design.addins.client.MaterialAddins;
import gwt.material.design.addins.client.base.constants.AddinsCssName;
import gwt.material.design.addins.client.bubble.js.JsBubble;
import gwt.material.design.addins.client.bubble.js.JsBubbleOptions;
import gwt.material.design.client.MaterialDesignBase;
import gwt.material.design.client.base.HasPosition;
import gwt.material.design.client.base.JsLoader;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.base.helper.ColorHelper;
import gwt.material.design.client.base.mixin.CssNameMixin;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.constants.Position;

import static gwt.material.design.jquery.client.api.JQuery.$;

public class MaterialPopupBubble extends MaterialWidget implements JsLoader, HasOpenHandlers<MaterialPopupBubble>,
        HasCloseHandlers<MaterialPopupBubble>, HasPosition {

    static {
        if (MaterialAddins.isDebug()) {
            MaterialDesignBase.injectCss(MaterialPopupMenuDebugClientBundle.INSTANCE.menuCssDebug());
            MaterialDesignBase.injectDebugJs(MaterialBubbleDebugClientBundle.INSTANCE.bubbleJsDebug());
            MaterialDesignBase.injectCss(MaterialBubbleDebugClientBundle.INSTANCE.bubbleCssDebug());
        } else {
            MaterialDesignBase.injectCss(MaterialPopupMenuClientBundle.INSTANCE.menuCss());
            MaterialDesignBase.injectJs(MaterialBubbleClientBundle.INSTANCE.bubbleJs());
            MaterialDesignBase.injectCss(MaterialBubbleClientBundle.INSTANCE.bubbleCss());
        }
    }

    private MaterialWidget triangle = new MaterialWidget(Document.get().createDivElement());
    private CssNameMixin<MaterialWidget, Position> positionMixin;
    private JsBubbleOptions options = new JsBubbleOptions();
    private int popupX;
    private int popupY;
    private String id;
    private Object selected;

    public MaterialPopupBubble() {
        super(Document.get().createDivElement());
        id = DOM.createUniqueId();
        setInitialClasses(AddinsCssName.POPUP_MENU, "bubble-popup", AddinsCssName.MENU_BAR, CssName.Z_DEPTH_1);
        getPositionMixin().setCssName(Position.LEFT);
        triangle.setStyleName(AddinsCssName.TRIANGLE);
        add(triangle);
        setVisible(false);
    }

    @Override
    protected void onLoad() {
        super.onLoad();
        load();
    }

    @Override
    public void load() {
        JsBubble.$(getElement()).bubble(options);
        $(this).attr("tabindex", "0");
        $(this).on("blur", e -> {
            close();
            return true;
        });

        close();
    }

    @Override
    protected void onUnload() {
        super.onUnload();

        unload();
    }

    @Override
    public void unload() {
        $(this).off("." + id);
        $("*").off("." + id);
    }

    @Override
    public void reload() {
        unload();
        load();
    }

    @Override
    public void setBackgroundColor(Color bgColor) {
        super.setBackgroundColor(bgColor);
        options.color = ColorHelper.setupComputedBackgroundColor(bgColor);
        reload();
    }


    /**
     * Set the popup position of the context menu
     *
     * @param popupX window x position
     * @param popupY window y position
     */
    public void setPopupPosition(int popupX, int popupY) {
        // Will check if the popup is out of container
        this.popupX = popupX;
        this.popupY = popupY;
        setLeft(popupX);
        setTop(popupY);
    }

    public void setPopupX(int popupX){
        this.popupX=popupX;
        setLeft(popupX);
    }

    public void setPopupY(int popupY){
        this.popupY=popupY;
        setTop(popupY);
    }


    public void open() {
        setVisible(true);
        Scheduler.get().scheduleDeferred(() -> setFocus(true));

        // Check if dropdown is out of the container (Left)
        if ($(this).width() + $(this).offset().left > body().width()) {
            setLeft(body().width() - $(this).width());
        }
        OpenEvent.fire(this, this);
    }

    public void close() {
        setVisible(false);
        CloseEvent.fire(this, this);
    }

    public Object getSelected() {
        return selected;
    }

    public void setSelected(Object selected) {
        this.selected = selected;
    }

    @Override
    public HandlerRegistration addCloseHandler(CloseHandler<MaterialPopupBubble> closeHandler) {
        return addHandler((CloseHandler<MaterialPopupBubble>) closeEvent -> {
            if (isEnabled()) {
                closeHandler.onClose(closeEvent);
            }
        }, CloseEvent.getType());
    }

    @Override
    public HandlerRegistration addOpenHandler(OpenHandler<MaterialPopupBubble> openHandler) {
        return addHandler((OpenHandler<MaterialPopupBubble>) openEvent -> {
            if (isEnabled()) {
                openHandler.onOpen(openEvent);
            }
        }, OpenEvent.getType());
    }

    public int getPopupX() {
        return popupX;
    }

    public int getPopupY() {
        return popupY;
    }

    public MaterialWidget getTriangle() {
        return triangle;
    }

    @Override
    public void setPosition(Position position) {
        options.position = position.getCssName();
        getPositionMixin().setCssName(position);
        reload();
    }

    @Override
    public Position getPosition() {
        return options.position != null ? Position.fromStyleName(options.position) : null;
    }

    protected CssNameMixin<MaterialWidget, Position> getPositionMixin() {
        if (positionMixin == null) {
            positionMixin = new CssNameMixin<>(triangle);
        }
        return positionMixin;
    }

    public void showRelativeTo(MaterialWidget widget){
        int windowWidth= Window.getClientWidth();
        final int x = widget.getAbsoluteLeft() - ((this.getWidth() - widget.getWidth()) / 2);
        int diff=0;
        if(windowWidth<(x+this.getWidth())){
            diff=(x+this.getWidth())-windowWidth;
        }

        setPopupPosition(x-diff, widget.getAbsoluteTop()+popupY);
        if(getTriangle().getAbsoluteLeft()<widget.getAbsoluteLeft()){
            getTriangle().setLeft(widget.getAbsoluteLeft()+(widget.getWidth()/2)-popupX-15);
        }
        open();
    }


}
