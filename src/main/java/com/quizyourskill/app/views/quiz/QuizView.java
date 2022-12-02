package com.quizyourskill.app.views.quiz;

import com.quizyourskill.app.data.entity.quiz.AnswerOption;
import com.quizyourskill.app.data.entity.quiz.Question;
import com.quizyourskill.app.data.entity.quiz.Quiz;
import com.quizyourskill.app.data.entity.quiz.QuizPage;
import com.quizyourskill.app.data.entity.user.User;
import com.quizyourskill.app.data.service.quiz.QuizService;
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
import java.util.Set;

@Route(value = "quiz", layout = MainLayout.class)
@PermitAll
public class QuizView extends VerticalLayout implements HasDynamicTitle, HasUrlParameter<Integer>{
   private final QuizService quizService;
   private final User user;
   private String title = "Quiz";
   private Quiz quiz;
   private Integer currentPageNumber;

   public QuizView(QuizService quizService, AuthenticatedUser authenticatedUser){
      this.quizService = quizService;
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
         quizService.findById(parameter).ifPresent(quiz -> {
            this.quiz = quiz;
            title = "Lerneinheit: " + quiz.getTitle();
            createQuizLayout();
         });
      }

   }

   private void createQuizLayout(){
      removeAll();
      currentPageNumber = 0;

      VerticalLayout startQuizLayout = new VerticalLayout();
      startQuizLayout.setAlignItems(Alignment.CENTER);
      add(startQuizLayout);

      Image quizImage = new Image("images/quiz-your-skill-logo.png", "Quiz");
      quizImage.setMaxWidth("500px");
      startQuizLayout.add(quizImage);
      startQuizLayout.add(new H1(quiz.getTitle()));
      Button startButton = new Button("starten");
      startButton.addClassName("start-button");
      startQuizLayout.add(startButton);

      startButton.addClickListener(click -> {
         currentPageNumber = 1;
         Set<QuizPage> quizPages = quiz.getQuizPages();
         quizPages.stream().filter(quizPage -> quizPage.getPageNumber().equals(currentPageNumber)).findFirst().ifPresent(this::createQuizPageLayout);
      });
   }

   private void createQuizPageLayout(QuizPage quizPage){
      removeAll();

      VerticalLayout quizPageLayout = new VerticalLayout();
      quizPageLayout.setClassName("quiz-page-layout");
      quizPageLayout.setSpacing(false);
      quizPageLayout.setMaxWidth("1000px");
      quizPageLayout.setAlignItems(Alignment.CENTER);
      add(quizPageLayout);

      HorizontalLayout headerLayout = new HorizontalLayout();
      headerLayout.setPadding(true);
      headerLayout.setSizeFull();
      quizPageLayout.add(headerLayout);

      Image learningUnitImage = new Image("images/quiz-your-skill-logo.png", "Quiz");
      learningUnitImage.setWidth("30%");

      VerticalLayout titleLayout = new VerticalLayout();
      titleLayout.setSizeFull();
      titleLayout.setPadding(false);
      titleLayout.setSpacing(false);
      headerLayout.add(learningUnitImage, titleLayout);

      H2 title = new H2("Fragerunde");
      title.getStyle().set("margin-top", "22px");
      title.getStyle().set("margin-bottom", "10px");
      title.getStyle().set("font-size", "3em");
      H3 quizPageTitle = new H3(quizPage.getTitle());
      quizPageTitle.getStyle().set("margin", "0");
      quizPageTitle.getStyle().set("font-size", "2em");
      titleLayout.add(title, quizPageTitle);

      VerticalLayout contentLayout = new VerticalLayout();
      contentLayout.setAlignItems(Alignment.CENTER);
      contentLayout.getStyle().set("padding-top", "0px");
      quizPageLayout.add(contentLayout);

      quizPage.getQuestions().forEach(question -> {
         VerticalLayout questionLayout = createQuestionLayout(question);
         contentLayout.add(questionLayout);
      });

      HorizontalLayout buttonLayout = createButtonLayout(quizPage);
      contentLayout.add(buttonLayout);
   }

   private VerticalLayout createQuestionLayout(Question question){
      VerticalLayout questionLayout = new VerticalLayout();
      questionLayout.setAlignItems(Alignment.CENTER);

      H3 questionTitle = new H3(question.getText());
      questionTitle.getStyle().set("margin", "0");
      questionTitle.getStyle().set("font-size", "1.5em");
      questionLayout.add(questionTitle);

      HorizontalLayout imageAndAnswerLayout = new HorizontalLayout();
      imageAndAnswerLayout.setAlignItems(Alignment.CENTER);
      imageAndAnswerLayout.setSpacing(false);
      questionLayout.add(imageAndAnswerLayout);

      if(question.getMediaFile() != null){
         Image image = new Image(question.getMediaFile().getPath(), "Frage");
         image.getStyle().set("border-radius", "20px");
         image.setMaxWidth("50%");
         imageAndAnswerLayout.add(image);
      }

      VerticalLayout answerLayout = new VerticalLayout();
      answerLayout.setClassName("answer-layout");
      imageAndAnswerLayout.add(answerLayout);

      char answerChar = 'A';
      for(AnswerOption answer : question.getAnswerOptions()){
         Button answerButton = new Button(answerChar + ": " + answer.getText());
         answerLayout.add(answerButton);
         answerChar++;

         answerButton.addClickListener(click -> {
            if(answerButton.hasClassName("button-clicked"))
               answerButton.removeClassName("button-clicked");
            else
               answerButton.addClassName("button-clicked");
         });
      }

      return questionLayout;
   }

   private HorizontalLayout createButtonLayout(QuizPage quizPage){
      HorizontalLayout buttonLayout = new HorizontalLayout();
      buttonLayout.addClassName("button-layout");
      buttonLayout.setPadding(true);
      buttonLayout.setSpacing(true);
      buttonLayout.setWidthFull();
      buttonLayout.setJustifyContentMode(JustifyContentMode.EVENLY);
      buttonLayout.setAlignItems(Alignment.STRETCH);

      Button backButton = new Button("Zurück");
      backButton.setWidthFull();
      backButton.addClickListener(click -> {
         if(quizPage.isFirstPage())
            createQuizLayout();
         else{
            currentPageNumber--;
            Set<QuizPage> quizPages = quiz.getQuizPages();
            quizPages.stream().filter(learningPage1 -> learningPage1.getPageNumber().equals(currentPageNumber)).findFirst().ifPresent(this::createQuizPageLayout);
         }
      });
      buttonLayout.add(backButton);

      Button detailsButton = new Button("Details");
      detailsButton.setWidthFull();
      detailsButton.addClickListener(click -> {
         UI.getCurrent().getPage().executeJs("document.querySelector('.quiz-page-layout').style.filter = 'blur(5px)';");

         Dialog dialog = new Dialog(new Html("<p>" + quizPage.getDetails().getText() + "</p>"));
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
            UI.getCurrent().getPage().executeJs("document.querySelector('.quiz-page-layout').style.filter = 'blur(0px)';");
            dialog.close();
         });
         dialogButtonLayout.add(closeButton);

         // make sure that the dialog is closed when the user presses the ESC key
         dialog.addDialogCloseActionListener(clickEvent -> {
            UI.getCurrent().getPage().executeJs("document.querySelector('.quiz-page-layout').style.filter = 'blur(0px)';");
            dialog.close();
         });
      });
      buttonLayout.add(detailsButton);

      Button nextButton = new Button("weiter");
      nextButton.setWidthFull();
      nextButton.addClickListener(click -> {
         if(quizPage.isLastPage())
            createFinalResultPage();
         else{
            currentPageNumber++;
            Set<QuizPage> quizPages = quiz.getQuizPages();
            quizPages.stream().filter(learningPage1 -> learningPage1.getPageNumber().equals(currentPageNumber)).findFirst().ifPresent(this::createQuizPageLayout);
         }
      });

      if(quizPage.isLastPage())
         nextButton.setText("fertig");

      buttonLayout.add(nextButton);

      return buttonLayout;
   }

   private void createFinalResultPage(){
      removeAll();

      VerticalLayout finalResultLayout = new VerticalLayout();
      finalResultLayout.setAlignItems(Alignment.CENTER);
      finalResultLayout.setJustifyContentMode(JustifyContentMode.CENTER);
      finalResultLayout.setClassName("final-result-layout");
      add(finalResultLayout);

      Image logo = new Image("images/quiz-your-skill-logo.png", "Quiz Your Skill Logo");
      logo.setMaxWidth("500px");
      finalResultLayout.add(logo);

      H2 title = new H2("Ihr Ergebnis");
      finalResultLayout.add(title);

      Paragraph resultText = new Paragraph("Sie haben " + quiz.getQuizPages().size() + " von " + quiz.getQuizPages().size() + " Fragen richtig beantwortet.");
      resultText.getStyle().set("font-size", "1.5em");
      finalResultLayout.add(resultText);

      Button backToOverviewButton = new Button("Zurück zum Kurs");
      backToOverviewButton.addClassName("back-to-course-button");
      backToOverviewButton.addClickListener(click -> UI.getCurrent().navigate(CourseView.class, quiz.getCourse().getId().toString()));
      finalResultLayout.add(backToOverviewButton);
   }
}