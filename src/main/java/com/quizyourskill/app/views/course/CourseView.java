package com.quizyourskill.app.views.course;

import com.quizyourskill.app.data.entity.learn.LearningUnit;
import com.quizyourskill.app.data.entity.quiz.Quiz;
import com.quizyourskill.app.data.entity.user.User;
import com.quizyourskill.app.data.service.course.CourseService;
import com.quizyourskill.app.data.service.learn.LearningUnitService;
import com.quizyourskill.app.data.service.quiz.QuizService;
import com.quizyourskill.app.security.AuthenticatedUser;
import com.quizyourskill.app.views.MainLayout;
import com.quizyourskill.app.views.learningunit.LearningUnitView;
import com.quizyourskill.app.views.quiz.QuizView;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.security.PermitAll;

@Route(value = "course", layout = MainLayout.class)
@PermitAll
public class CourseView extends VerticalLayout implements HasDynamicTitle, HasUrlParameter<String>{
   private final CourseService courseService;
   private final LearningUnitService learningUnitService;
   private final QuizService quizService;
   private final User user;
   private String title = "Kurs";
   private Integer courseId;

   public CourseView(CourseService courseService, LearningUnitService learningUnitService, QuizService quizService, AuthenticatedUser authenticatedUser){
      this.courseService = courseService;
      this.learningUnitService = learningUnitService;
      this.quizService = quizService;
      user = authenticatedUser.get().orElse(null);
      addClassName("course-view");
      setAlignItems(Alignment.CENTER);
   }

   @Override
   public String getPageTitle(){
      return title;
   }

   @Override
   public void setParameter(BeforeEvent event, String parameter){
      if(StringUtils.isEmpty(parameter))
         event.rerouteTo("");
      else{
         try{
            courseId = Integer.parseInt(parameter);
            if(user != null)
               createCourseLayout();
         }
         catch(NumberFormatException e){
            courseId = null;
         }
      }
   }

   private void createCourseLayout(){
      removeAll();

      courseService.findById(courseId).ifPresentOrElse(course -> courseService.findAllByUserId(user.getId()).ifPresentOrElse(courses -> {
         if(courses.contains(course)){
            title = "Kurs: " + course.getTitle();
            add(new H1(course.getTitle()));
            add(new H2(course.getDescription()));
            add(new Paragraph("Starten Sie Ihre Lerneinheit oder das Quiz mit einem Klick auf das entsprechende Bild."));

            HorizontalLayout chooseLearningUnitOrQuizLayout = new HorizontalLayout();
            chooseLearningUnitOrQuizLayout.setClassName("learn-or-quiz-image-layout");
            chooseLearningUnitOrQuizLayout.setPadding(true);
            chooseLearningUnitOrQuizLayout.setSizeFull();
            chooseLearningUnitOrQuizLayout.setJustifyContentMode(JustifyContentMode.CENTER);
            add(chooseLearningUnitOrQuizLayout);

            learningUnitService.findAllByCourseId(courseId).ifPresent(learningUnits -> {
               LearningUnit learningUnit = learningUnits.iterator().next();
               Image learningUnitImage = new Image("images/learn-your-skill-logo.png", "Lerneinheit");
               learningUnitImage.getElement().setAttribute("title", learningUnit.getTitle());
               learningUnitImage.setWidth("50%");
               learningUnitImage.setMaxWidth("500px");
               chooseLearningUnitOrQuizLayout.add(learningUnitImage);
               String path = RouteConfiguration.forSessionScope().getUrl(LearningUnitView.class, learningUnit.getId());
               learningUnitImage.addClickListener(click -> getUI().ifPresent(ui -> ui.navigate(path)));
            });

            quizService.findAllByCourseId(courseId).ifPresent(quizzes -> {
               Quiz quiz = quizzes.iterator().next();
               Image quizImage = new Image("images/quiz-your-skill-logo.png", "Quiz");
               quizImage.getElement().setAttribute("title", quiz.getTitle());
               quizImage.setWidth("50%");
               quizImage.setMaxWidth("500px");
               chooseLearningUnitOrQuizLayout.add(quizImage);
               String path = RouteConfiguration.forSessionScope().getUrl(QuizView.class, quiz.getId());
               quizImage.addClickListener(click -> getUI().ifPresent(ui -> ui.navigate(path)));
            });
         }
         else
            add(new H1("Sie haben keinen Zugriff auf diesen Kurs."));
      }, () -> add(new H1("Sie sind bisher in keinem Kurs eingeschrieben."))), () -> add(new H1("Der Kurs existiert nicht.")));
   }
}