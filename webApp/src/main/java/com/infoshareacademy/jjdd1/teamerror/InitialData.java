package com.infoshareacademy.jjdd1.teamerror;

import com.infoshareacademy.jjdd1.teamerror.file_loader.*;
import com.infoshareacademy.jjdd1.teamerror.trendy_engine.Trendy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Map;

/**
 * Created by Krystian on 2017-04-29.
 */
public class InitialData {

    private final Logger LOGGER = LoggerFactory.getLogger(InitialData.class);

    FilesContent filesContent;


    public InitialData() {
        super();
        LOGGER.info("Initial data start");


        filesContent = new OnDemandFilesContent();

        LOGGER.info("InitialServlet initialised");

    }
}

