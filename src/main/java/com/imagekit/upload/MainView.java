package com.imagekit.upload;


import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("")
@PageTitle("Hello, Vaadin!")
public class MainView extends VerticalLayout {


    public MainView() {
        this.add(new NativeLabel("Hello, Vaadin!"));


        Upload upload = new Upload();

    }
}
