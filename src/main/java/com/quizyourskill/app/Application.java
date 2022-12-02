package com.quizyourskill.app;

import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.sql.init.SqlDataSourceScriptDatabaseInitializer;
import org.springframework.boot.autoconfigure.sql.init.SqlInitializationProperties;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 * The entry point of the Spring Boot application.
 * <p>
 * Use the @PWA annotation make the application installable on phones, tablets and some desktop browsers.
 */
@SpringBootApplication
@Theme(value = "quizyourskill")
@PWA(name = "Quiz Your Skill", shortName = "QYS", offlineResources = {})
@NpmPackage(value = "line-awesome", version = "1.3.0")
@NpmPackage(value = "@vaadin-component-factory/vcf-nav", version = "1.0.6")
public class Application implements AppShellConfigurator{
   public static void main(String[] args){
      SpringApplication.run(Application.class, args);
   }

   @Bean
   SqlDataSourceScriptDatabaseInitializer dataSourceScriptDatabaseInitializer(
         DataSource dataSource, SqlInitializationProperties properties){
      return new SqlDataSourceScriptDatabaseInitializer(dataSource, properties){
         @Override
         public boolean initializeDatabase(){
            return super.initializeDatabase();
         }
      };
   }
}