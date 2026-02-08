package com.imagekit.upload;

import io.imagekit.sdk.ImageKit;
import io.imagekit.sdk.config.Configuration;
import io.imagekit.sdk.models.FileCreateRequest;
import io.imagekit.sdk.models.results.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Upload {

    private static final Logger log = LoggerFactory.getLogger(Upload.class);

    private String publicKey;
    private String privateKey;
    private String endpointUrl;
    public Upload( @Value("${IMAGEKIT.PUBLIC.KEY}") String publicKey,
                   @Value("${IMAGEKIT.PRIVATE.KEY}") String privateKey,
                   @Value("${IMAGEKIT.ENDPOINT.URL}") String endpointUrl) {


        this.publicKey = publicKey;
        this.privateKey = privateKey;
        this.endpointUrl = endpointUrl;
        log.info("Public Key: " + publicKey+", Private Key: " + privateKey+", Endpoint URL: " + endpointUrl);
    }


    public ImageKit setConfiguration() {
        ImageKit imageKit = ImageKit.getInstance();
        Configuration configuration = new Configuration(publicKey,privateKey,endpointUrl);
        imageKit.setConfig(configuration);
        return imageKit;
    }

    public String upload(byte[] bytes, String fileName, String folderName) {

        FileCreateRequest fileCreateRequest = new FileCreateRequest(bytes,fileName);
        fileCreateRequest.setFolder(folderName);

        ImageKit imageKit = setConfiguration();
        try{
           Result result= imageKit.upload(fileCreateRequest);
           return result.getUrl();
        }catch (Exception e) {
        log.error("An exception thrown : ",e);
        return e.getMessage();
        }


    }

}
