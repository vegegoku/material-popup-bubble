package com.progressoft.domino.layout.layout.client.presenters;

import com.progressoft.brix.domino.api.client.annotations.Presenter;
import com.progressoft.brix.domino.api.client.mvp.presenter.BaseClientPresenter;
import com.progressoft.domino.layout.layout.client.views.LayoutView;
import com.progressoft.brix.domino.api.shared.extension.MainContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Presenter
public class DefaultLayoutPresenter extends BaseClientPresenter<LayoutView> implements LayoutPresenter {

    private static final Logger LOGGER=LoggerFactory.getLogger(DefaultLayoutPresenter.class);

    @Override
    public void contributeToMainModule(MainContext context){
        LOGGER.info("Main context received at presenter "+DefaultLayoutPresenter.class.getSimpleName());
    }
}