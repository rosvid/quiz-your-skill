package com.quizyourskill.app.views;

import com.quizyourskill.app.components.appnav.AppNav;
import com.quizyourskill.app.components.appnav.AppNavItem;
import com.quizyourskill.app.data.service.course.CourseService;
import com.quizyourskill.app.security.AuthenticatedUser;
import com.quizyourskill.app.views.course.CourseView;
import com.quizyourskill.app.views.welcome.WelcomeView;
import com.quizyourskill.app.views.learningunit.LearningUnitView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.server.auth.AccessAnnotationChecker;
import com.vaadin.flow.theme.lumo.LumoUtility;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout{
   private final CourseService courseService;
   private final AuthenticatedUser authenticatedUser;
   private H2 viewTitle;

   public MainLayout(CourseService courseService, AuthenticatedUser authenticatedUser, AccessAnnotationChecker accessChecker){
      this.courseService = courseService;
      this.authenticatedUser = authenticatedUser;

      setPrimarySection(Section.DRAWER);
      addDrawerContent();
      addHeaderContent();
   }

   private void addHeaderContent(){
      DrawerToggle toggle = new DrawerToggle();
      toggle.getElement().setAttribute("aria-label", "Menu toggle");

      viewTitle = new H2();
      viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

      addToNavbar(true, toggle, viewTitle);
   }

   private void addDrawerContent(){
      H1 appName = new H1("Quiz Your Skill");
      appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
      Header header = new Header(appName);

      Scroller scroller = new Scroller(createNavigation());
      addToDrawer(header, scroller, createFooter());
   }

   private AppNav createNavigation(){
      // AppNav is not yet an official component.
      // For documentation, visit https://github.com/vaadin/vcf-nav#readme
      AppNav nav = new AppNav();

/*      if(accessChecker.hasAccess(WelcomeView.class))
         nav.addItem(new AppNavItem("Herzlich Willkommen zu Quiz Your Skill!", WelcomeView.class, "la la-globe"));
      if(accessChecker.hasAccess(PersonFormView.class))
         nav.addItem(new AppNavItem("Person Form", PersonFormView.class, "la la-user"));
      if(accessChecker.hasAccess(CardListView.class))
         nav.addItem(new AppNavItem("Card List", CardListView.class, "la la-list"));
      if(accessChecker.hasAccess(ImageListView.class))
         nav.addItem(new AppNavItem("Image List", ImageListView.class, "la la-th-list"));
      if(accessChecker.hasAccess(AboutView.class))
         nav.addItem(new AppNavItem("About", AboutView.class, "la la-file"));*/
      authenticatedUser.get().ifPresent(user -> addMenuItemsForUsersCourses(nav, user.getId()));

      return nav;
   }

   private void addMenuItemsForUsersCourses(AppNav nav, Integer userId){
      courseService.findAllByUserId(userId).ifPresent(courses -> {
         courses.forEach(course -> {
            String path = RouteConfiguration.forSessionScope().getUrl(CourseView.class, course.getId().toString());
            nav.addItem(new AppNavItem(course.getTitle(), path, new Icon(VaadinIcon.ACADEMY_CAP)));
         });
      });
   }

   private Footer createFooter(){
      Footer layout = new Footer();

      authenticatedUser.get().ifPresentOrElse(user -> {
         MenuBar userMenu = new MenuBar();
         userMenu.setThemeName("tertiary-inline contrast");

         MenuItem userName = userMenu.addItem("");
         Div div = new Div();
         div.add(user.getName());
         div.add(new Icon("lumo", "dropdown"));
         div.getElement().getStyle().set("display", "flex");
         div.getElement().getStyle().set("align-items", "center");
         div.getElement().getStyle().set("gap", "var(--lumo-space-s)");
         userName.add(div);
         userName.getSubMenu().addItem("Logout", e -> authenticatedUser.logout());

         layout.add(userMenu);
      }, () -> {
         Anchor loginLink = new Anchor("login", "Login");
         layout.add(loginLink);
      });

      return layout;
   }

   @Override
   protected void afterNavigation(){
      setDrawerOpened(false);
      if(getContent() instanceof WelcomeView)
         setDrawerOpened(authenticatedUser.get().isPresent());
      viewTitle.setText(getCurrentPageTitle());
   }

   private String getCurrentPageTitle(){
      String pageTitle = "";

      // if current page is of instance CourseView then get the title from the course
      if(getContent() instanceof CourseView courseView)
         pageTitle = courseView.getPageTitle();
      else if(getContent() instanceof LearningUnitView learningUnitView)
         pageTitle = learningUnitView.getPageTitle();
      else{
         PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
         if(title != null)
            pageTitle = title.value();
      }

      return pageTitle;
   }
}