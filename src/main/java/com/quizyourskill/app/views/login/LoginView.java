package com.quizyourskill.app.views.login;

import com.quizyourskill.app.security.AuthenticatedUser;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.internal.RouteUtil;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@AnonymousAllowed
@PageTitle("Login")
@Route(value = "login")
public class LoginView extends LoginOverlay implements BeforeEnterObserver{
   private final AuthenticatedUser authenticatedUser;

   public LoginView(AuthenticatedUser authenticatedUser){
      this.authenticatedUser = authenticatedUser;
      setAction(RouteUtil.getRoutePath(VaadinService.getCurrent().getContext(), getClass()));

      LoginI18n i18n = LoginI18n.createDefault();
      i18n.setHeader(new LoginI18n.Header());
      i18n.getHeader().setTitle("Quiz Your Skill");
      i18n.getHeader().setDescription("Melde Sie sich bei Ihrem Konto an");
      i18n.getForm().setTitle("Login");
      i18n.getForm().setUsername("Benutzername");
      i18n.getForm().setPassword("Passwort");
      i18n.getForm().setSubmit("Anmelden");
      i18n.setAdditionalInformation(null);
      setI18n(i18n);

      setForgotPasswordButtonVisible(false);
      setOpened(true);
   }

   @Override
   public void beforeEnter(BeforeEnterEvent event){
      if(authenticatedUser.get().isPresent()){
         // Already logged in
         setOpened(false);
         event.forwardTo("");
      }

      setError(event.getLocation().getQueryParameters().getParameters().containsKey("error"));
   }
}