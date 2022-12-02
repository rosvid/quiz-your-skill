package com.quizyourskill.app.views.welcome;

import com.quizyourskill.app.security.AuthenticatedUser;
import com.quizyourskill.app.views.MainLayout;
import com.quizyourskill.app.views.login.LoginView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("Herzlich Willkommen zu Quiz Your Skill!")
@Route(value = "welcome", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@AnonymousAllowed
public class WelcomeView extends VerticalLayout{
   private final AuthenticatedUser authenticatedUser;

   public WelcomeView(AuthenticatedUser authenticatedUser){
      this.authenticatedUser = authenticatedUser;
      setAlignItems(Alignment.CENTER);
      createLayout();
   }

   private void createLayout(){
      // create welcome label
      H1 title = new H1("Herzlich Willkommen zu ...");

      // add quiz-your-skill-logo.png from resources
      Image quizYourSkillLogo = new Image("images/quiz-your-skill-logo.png", "My Alt Image");
      quizYourSkillLogo.setMaxWidth("500px");

      add(title, quizYourSkillLogo);

      this.authenticatedUser.get().ifPresentOrElse(user -> {
         add(new H2("Bitte wählen Sie Ihren Kurs im Menü aus."));
      }, () -> {
         add(new H2("Bitte loggen Sie sich ein um Ihre Kurse absolvieren zu können."));
         Button loginButton = new Button("Zum Login", event -> getUI().ifPresent(ui -> ui.navigate(LoginView.class)));
         loginButton.addThemeVariants(ButtonVariant.LUMO_LARGE);
         loginButton.getStyle().set("cursor", "pointer");
         add(loginButton);
      });
   }
}