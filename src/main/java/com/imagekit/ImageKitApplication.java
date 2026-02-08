package com.imagekit;

import com.vaadin.flow.component.page.AppShellConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ImageKitApplication implements AppShellConfigurator {
    public static void main(String[] args) {
        SpringApplication.run(ImageKitApplication.class, args);
    }

}
