package back.offfice;

import javax.servlet.annotation.WebServlet;

import back.offfice.dbConnection.DBConnection;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.ClassResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;

/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Title("Porter - Back Office")
@Theme("mytheme")
public class MyUI extends UI {

    DBConnection connection;
    private Navigator navigator;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        createUI();

    }

    private void createUI(){

        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setSizeFull();
        setContent(mainLayout);
        mainLayout.setMargin(LoginPage.noMargin);
        mainLayout.setStyleName("image-background");
        navigator = new Navigator(this,mainLayout);
        navigator.addView("login",LoginPage.class);
        navigator.addView("common",CommonPage.class);

        getUI().getNavigator().navigateTo("login");
        navigator.setErrorView(LoginPage.class);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
