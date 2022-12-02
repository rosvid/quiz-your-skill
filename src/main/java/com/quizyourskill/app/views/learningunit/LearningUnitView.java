package com.quizyourskill.app.views.learningunit;

import com.quizyourskill.app.data.entity.files.MediaFile;
import com.quizyourskill.app.data.entity.learn.LearningPage;
import com.quizyourskill.app.data.entity.learn.LearningUnit;
import com.quizyourskill.app.data.entity.user.User;
import com.quizyourskill.app.data.service.learn.LearningUnitService;
import com.quizyourskill.app.security.AuthenticatedUser;
import com.quizyourskill.app.views.MainLayout;
import com.quizyourskill.app.views.course.CourseView;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasDynamicTitle;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;
import java.util.List;

@Route(value = "learning-unit", layout = MainLayout.class)
@PermitAll
public class LearningUnitView extends VerticalLayout implements HasDynamicTitle, HasUrlParameter<Integer>{
   private final LearningUnitService learningUnitService;
   private final User user;
   private String title = "Lerneinheit";
   private LearningUnit learningUnit;
   private Integer currentPageNumber = 0;

   public LearningUnitView(LearningUnitService learningUnitService, AuthenticatedUser authenticatedUser){
      this.learningUnitService = learningUnitService;
      user = authenticatedUser.get().orElse(null);
      setAlignItems(Alignment.CENTER);
   }

   @Override
   public String getPageTitle(){
      return title;
   }

   @Override
   public void setParameter(BeforeEvent event, Integer parameter){
      if(user != null){
         learningUnitService.findById(parameter).ifPresent(learningUnit -> {
            this.learningUnit = learningUnit;
            title = "Lerneinheit: " + learningUnit.getTitle();
            createLearningUnitLayout();
         });
      }
   }

/*   private void updateQueryParameters(Integer currentPageNumber){
      String deepLinkingUrl = RouteConfiguration.forSessionScope().getUrl(this.getClass(), currentPageNumber);
      getUI().ifPresent(ui -> ui.getPage().getHistory().replaceState(null, deepLinkingUrl));
   }*/

   private void createLearningUnitLayout(){
      removeAll();
      currentPageNumber = 0;

      VerticalLayout startLearningLayout = new VerticalLayout();
      startLearningLayout.setAlignItems(Alignment.CENTER);
      add(startLearningLayout);

      Image learningUnitImage = new Image("images/learn-your-skill-logo.png", "Lerneinheit");
      learningUnitImage.setMaxWidth("500px");
      startLearningLayout.add(learningUnitImage);
      startLearningLayout.add(new H1(learningUnit.getTitle()));
      Button startButton = new Button("starten");
      startButton.addClassName("start-button");
      startLearningLayout.add(startButton);

      startButton.addClickListener(click -> {
         currentPageNumber = 1;
         List<LearningPage> learningPages = learningUnit.getLearningPages();
         learningPages.stream().filter(learningPage -> learningPage.getPageNumber().equals(currentPageNumber)).findFirst().ifPresent(this::createLearningPageLayout);
      });
   }

   private void createLearningPageLayout(LearningPage learningPage){
      removeAll();

      VerticalLayout learningPageLayout = new VerticalLayout();
      learningPageLayout.setClassName("learning-page-layout");
      learningPageLayout.setSpacing(false);
      learningPageLayout.setMaxWidth("1000px");
      learningPageLayout.setAlignItems(Alignment.CENTER);
      add(learningPageLayout);

      HorizontalLayout headerLayout = new HorizontalLayout();
      headerLayout.setPadding(true);
      headerLayout.setSizeFull();
      learningPageLayout.add(headerLayout);

      Image learningUnitImage = new Image("images/learn-your-skill-logo.png", "Lerneinheit");
      learningUnitImage.setWidth("30%");

      VerticalLayout titleLayout = new VerticalLayout();
      titleLayout.setSizeFull();
      titleLayout.setPadding(false);
      titleLayout.setSpacing(false);
      headerLayout.add(learningUnitImage, titleLayout);

      H2 title = new H2("Lerneinheit");
      title.getStyle().set("margin-top", "22px");
      title.getStyle().set("margin-bottom", "10px");
      title.getStyle().set("font-size", "3em");
      H3 learningPageTitle = new H3(learningPage.getTitle());
      learningPageTitle.getStyle().set("margin", "0");
      learningPageTitle.getStyle().set("font-size", "2em");
      titleLayout.add(title, learningPageTitle);

      VerticalLayout contentLayout = new VerticalLayout();
      contentLayout.setAlignItems(Alignment.CENTER);
      contentLayout.getStyle().set("padding-top", "0px");
      learningPageLayout.add(contentLayout);

      Paragraph learningPageText = new Paragraph(learningPage.getText());
      learningPageText.setWidthFull();
      contentLayout.add(learningPageText);

      HorizontalLayout mediaLayout = createMediaLayout(learningPage);
      contentLayout.add(mediaLayout);

      HorizontalLayout buttonLayout = createButtonLayout(learningPage);
      contentLayout.add(buttonLayout);
   }

   private HorizontalLayout createMediaLayout(LearningPage learningPage){
      HorizontalLayout mediaLayout = new HorizontalLayout();
      mediaLayout.setPadding(true);
      mediaLayout.setSpacing(true);
      mediaLayout.setSizeFull();
      mediaLayout.setJustifyContentMode(JustifyContentMode.CENTER);
      mediaLayout.getStyle().set("padding-top", "0px");

      if(learningPage.getMediaFiles() != null){
         int mediaFilesCount = learningPage.getMediaFiles().size();
         for(MediaFile mediaFile : learningPage.getMediaFiles()){
            Image image = new Image(mediaFile.getPath(), "Bild " + mediaFile.getName());
            image.getStyle().set("border-radius", "20px");
            if(mediaFilesCount > 1)
               image.setWidth("50%");
            else
               image.setWidth("80%");
            mediaLayout.add(image);
         }
      }
      return mediaLayout;
   }

   private HorizontalLayout createButtonLayout(LearningPage learningPage){
      HorizontalLayout buttonLayout = new HorizontalLayout();
      buttonLayout.addClassName("button-layout");
      buttonLayout.setPadding(true);
      buttonLayout.setSpacing(true);
      buttonLayout.setWidthFull();
      buttonLayout.setJustifyContentMode(JustifyContentMode.EVENLY);
      buttonLayout.setAlignItems(Alignment.STRETCH);

      Button backButton = new Button("zurück");
      backButton.setWidthFull();
      backButton.addClickListener(click -> {
         if(learningPage.isFirstPage())
            createLearningUnitLayout();
         else{
            currentPageNumber--;
            List<LearningPage> learningPages = learningUnit.getLearningPages();
            learningPages.stream().filter(learningPage1 -> learningPage1.getPageNumber().equals(currentPageNumber)).findFirst().ifPresent(this::createLearningPageLayout);
         }
      });
      buttonLayout.add(backButton);

      Button detailsButton = new Button("Details");
      detailsButton.setWidthFull();
      detailsButton.addClickListener(click -> {
         UI.getCurrent().getPage().executeJs("document.querySelector('.learning-page-layout').style.filter = 'blur(5px)';");

         Dialog dialog = new Dialog(new Html("<p>" + learningPage.getDetails().getText() + "</p>"));
         dialog.setCloseOnOutsideClick(false);
         dialog.setCloseOnEsc(true);
         dialog.open();

         HorizontalLayout dialogButtonLayout = new HorizontalLayout();
         dialogButtonLayout.addClassName("button-layout");
         dialogButtonLayout.setPadding(true);
         dialogButtonLayout.setSpacing(true);
         dialogButtonLayout.setWidthFull();
         dialogButtonLayout.setJustifyContentMode(JustifyContentMode.CENTER);
         dialog.add(dialogButtonLayout);

         Button closeButton = new Button("Alles klar!", clickEvent -> {
            UI.getCurrent().getPage().executeJs("document.querySelector('.learning-page-layout').style.filter = 'blur(0px)';");
            dialog.close();
         });
         dialogButtonLayout.add(closeButton);

         // make sure that the dialog is closed when the user presses the ESC key
         dialog.addDialogCloseActionListener(clickEvent -> {
            UI.getCurrent().getPage().executeJs("document.querySelector('.learning-page-layout').style.filter = 'blur(0px)';");
            dialog.close();
         });
      });
      buttonLayout.add(detailsButton);

      Button nextButton = new Button("weiter");
      nextButton.setWidthFull();
      nextButton.addClickListener(click -> {
         if(learningPage.isLastPage())
            UI.getCurrent().navigate(CourseView.class, learningUnit.getCourse().getId().toString());
         else{
            currentPageNumber++;
            List<LearningPage> learningPages = learningUnit.getLearningPages();
            learningPages.stream().filter(learningPage1 -> learningPage1.getPageNumber().equals(currentPageNumber)).findFirst().ifPresent(this::createLearningPageLayout);
         }
      });

      if(learningPage.isLastPage())
         nextButton.setText("fertig");

      buttonLayout.add(nextButton);

      return buttonLayout;
   }
}