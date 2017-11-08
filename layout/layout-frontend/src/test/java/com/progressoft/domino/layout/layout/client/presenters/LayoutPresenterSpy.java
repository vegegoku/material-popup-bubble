package com.progressoft.domino.layout.layout.client.presenters;

import com.progressoft.domino.layout.layout.client.presenters.DefaultLayoutPresenter;
import com.progressoft.brix.domino.api.shared.extension.MainContext;

public class LayoutPresenterSpy extends DefaultLayoutPresenter{

    private MainContext mainContext;

    @Override
    public void contributeToMainModule(MainContext context) {
        super.contributeToMainModule(context);
        this.mainContext=context;
    }

    public MainContext getMainContext() {
        return mainContext;
    }

    @Override
    protected String getConcrete() {
        return DefaultLayoutPresenter.class.getCanonicalName();
    }
}
