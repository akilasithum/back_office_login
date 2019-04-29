package back.offfice;

import back.offfice.dbConnection.DBConnection;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ClassResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;

public class LoginPage extends VerticalLayout implements View {
    private static final long serialVersionUID = 1L;
    public static final String NAME = "";
    DBConnection connection;
    private VerticalLayout logoLayout = new VerticalLayout();
    MarginInfo leftMargin = new MarginInfo(false,false,false,true);
    public static MarginInfo noMargin = new MarginInfo(false,false,false,false);

    public LoginPage(){

        HorizontalLayout layout = new HorizontalLayout();
        layout.setMargin(leftMargin);
        addComponent(layout);
        layout.addComponent(logoLayout);
        logoLayout.setMargin(noMargin);

        Image logo = new Image();
        logo.setSource(new ClassResource("logo.png"));
        logo.setWidth(170, Unit.PIXELS);
        logo.setHeight(50, Unit.PIXELS);
        logoLayout.addComponent(logo);

        VerticalLayout mainLayout = new VerticalLayout();
        addComponent(mainLayout);
        //mainLayout.setStyleName("login-image-background");
        mainLayout.setSizeFull();
        mainLayout.setMargin(true);
        setSpacing(true);
        setMargin(noMargin);
        VerticalLayout panel = new VerticalLayout();
        panel.setStyleName("trBackground");
        panel.setSizeUndefined();
        /*Image logo = new Image();
        logo.setSource(new ClassResource("logo.png"));
        logo.setWidth(370, Unit.PIXELS);
        logo.setHeight(130, Unit.PIXELS);
        mainLayout.addComponent(logo);*/
        mainLayout.setSpacing(true);
        mainLayout.addComponent(panel);

        connection = DBConnection.getInstance();


        FormLayout content = new FormLayout();
        TextField username = new TextField("Username");
        content.addComponent(username);
        PasswordField password = new PasswordField("Password");
        content.addComponent(password);

        Button send = new Button("Login");
        send.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                if(connection.isLoginSuccessful(username.getValue(), password.getValue())){
                    VaadinSession.getCurrent().setAttribute("user", username.getValue());
                    getSession().setAttribute("userName",username.getValue());
                    //((HybridUI)getUI()).navigate();
                    getUI().getNavigator().navigateTo("common");
                    //Page.getCurrent().reload();
                }else{
                    Notification.show("Invalid credentials", Notification.Type.ERROR_MESSAGE);
                }
            }

        });
        content.addComponent(send);
        content.setSizeUndefined();
        content.setMargin(true);
        panel.addComponent(content);
        //mainLayout.setComponentAlignment(logo,Alignment.MIDDLE_CENTER);
        mainLayout.setComponentAlignment(panel,Alignment.MIDDLE_CENTER);
        setComponentAlignment(mainLayout, Alignment.MIDDLE_CENTER);

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }

}

