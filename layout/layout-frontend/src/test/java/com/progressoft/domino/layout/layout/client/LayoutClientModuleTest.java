package com.progressoft.domino.layout.layout.client;

import com.google.gwtmockito.GwtMockitoTestRunner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Before;

import static org.junit.Assert.*;

import com.progressoft.brix.domino.api.client.annotations.ClientModule;
import com.progressoft.brix.domino.test.api.client.DominoTestClient;
import com.progressoft.brix.domino.test.api.client.ClientContext;
import com.progressoft.domino.layout.layout.client.presenters.LayoutPresenter;
import com.progressoft.domino.layout.layout.client.requests.LayoutServerRequest;
import com.progressoft.domino.layout.layout.shared.request.LayoutRequest;
import com.progressoft.domino.layout.layout.shared.response.LayoutResponse;
import com.progressoft.domino.layout.layout.client.presenters.LayoutPresenterSpy;
import com.progressoft.domino.layout.layout.client.views.FakeLayoutView;


@ClientModule(name="TestLayout")
@RunWith(GwtMockitoTestRunner.class)
public class LayoutClientModuleTest{

    private LayoutPresenterSpy presenterSpy;
    private FakeLayoutView fakeView;
    private ClientContext clientContext;

    @Before
    public void setUp() {
        presenterSpy = new LayoutPresenterSpy();
        DominoTestClient.useModules(new LayoutModuleConfiguration(), new TestLayoutModuleConfiguration())
                .replacePresenter(LayoutPresenter.class, presenterSpy)
                .viewOf(LayoutPresenter.class, view -> fakeView= (FakeLayoutView) view)
                .onStartCompleted(clientContext -> this.clientContext = clientContext)
                .start();
    }

    @Test
    public void givenLayoutModule_whenContributingToMainExtensionPoint_thenShouldReceiveMainContext() throws Exception {
        assertNotNull(presenterSpy.getMainContext());
    }

    @Test
    public void givenLayoutClientModule_whenLayoutServerRequestIsSent_thenServerMessageShouldBeRecieved() {
        clientContext.forRequest(LayoutServerRequest.class).returnResponse(new LayoutResponse("Server message"));

        new LayoutServerRequest(new LayoutRequest("client message")).onSuccess(response -> assertEquals("Server message",response.getServerMessage()))
        .onFailed(failedResponse -> fail(failedResponse.getError().getMessage()))
        .send();
    }
}
