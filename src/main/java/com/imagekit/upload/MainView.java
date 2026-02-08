package com.imagekit.upload;


import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.streams.InMemoryUploadHandler;
import com.vaadin.flow.server.streams.UploadHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Route("")
@PageTitle("Hello, Vaadin!")
public class MainView extends VerticalLayout {

    @Autowired
    private UploadService   uploadService;

    private static final Logger log = LoggerFactory.getLogger(MainView.class);

    public MainView() {
        this.add(new NativeLabel("Upload anything "));

        Image image = new Image();

        InMemoryUploadHandler handler = UploadHandler.inMemory((metadata, data) -> {
            try {
                String folder = metadata.contentType().contains("image") ? "/logos" : "/others";

               String url = uploadService.upload(data, metadata.fileName(), folder);
               log.info("Image url is : {}", url);
               image.setSrc(url);
                getUI().ifPresent(ui -> ui.access(() ->
                        Notification.show("Success! URL: " + url, 5000, Notification.Position.MIDDLE)
                ));
            } catch (Exception e) {
                getUI().ifPresent(ui -> ui.access(() ->
                        Notification.show("Error: " + e.getMessage())
                ));
            }
        });

        Upload upload = new Upload(handler);
        upload.setAcceptedFileTypes("image/png", "image/jpeg", "image/svg+xml");

        add(upload,image);

    }
}
