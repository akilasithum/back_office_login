package back.offfice;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ClassResource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;

public class CommonPage extends VerticalLayout implements View {

    private static final String URL = "http://163.172.156.224:8080/";
    private static final String imageWidth = "70%";

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        Object userName = UI.getCurrent().getSession().getAttribute("userName");
        if(userName == null|| userName.toString().isEmpty()){
            getUI().getNavigator().navigateTo("login");
        }
    }

    public CommonPage(){
        Object userName = UI.getCurrent().getSession().getAttribute("userName");
        String redirectURL = "/login?userName="+userName.toString();
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setSizeFull();
        HorizontalLayout btnLayout = new HorizontalLayout();


        Image skyApp = new Image(null, new ClassResource("sky_image.png"));
        skyApp.setWidth(imageWidth);
        skyApp.setHeight(imageWidth);
        skyApp.addStyleName("my-img-button");

        skyApp.addClickListener(clickEvent -> {
            getUI().getPage().setLocation(URL+"back-office"+redirectURL);
        });

        Image groundApp = new Image(null, new ClassResource("ground_image.png"));
        groundApp.setWidth(imageWidth);
        groundApp.setHeight(imageWidth);
        groundApp.addStyleName("my-img-button");

        groundApp.addClickListener(clickEvent -> {
            getUI().getPage().setLocation(URL+"aerotech-ground"+redirectURL);
        });

        Image galleyMgt = new Image(null, new ClassResource("galley_image.png"));
        galleyMgt.setWidth(imageWidth);
        galleyMgt.setHeight(imageWidth);
        galleyMgt.addStyleName("my-img-button");

        galleyMgt.addClickListener(clickEvent -> {
            Notification.show("Not implemented yet.", Notification.Type.WARNING_MESSAGE);
        });

        btnLayout.setSizeFull();
        verticalLayout.setStyleName("main-layout");
        verticalLayout.addComponent(btnLayout);
        btnLayout.addComponents(skyApp,groundApp,galleyMgt);
        btnLayout.setComponentAlignment(skyApp,Alignment.MIDDLE_CENTER);
        btnLayout.setComponentAlignment(groundApp,Alignment.MIDDLE_CENTER);
        btnLayout.setComponentAlignment(galleyMgt,Alignment.MIDDLE_CENTER);
        MarginInfo info = new MarginInfo(true);
        info.setMargins(true,false,false,false);
        verticalLayout.setMargin(info);
        addComponent(verticalLayout);
        setComponentAlignment(verticalLayout, Alignment.MIDDLE_CENTER);
    }
}
