package com.hnp.alfrescorestapi;

import com.hnp.alfrescorestapi.configuration.AlfrescoConfiguration;
import com.hnp.alfrescorestapi.utility.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AlfrescoRestApiApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(AlfrescoRestApiApplication.class, args);
    }

    @Autowired
    private AlfrescoConfiguration alfrescoConfiguration;

    @Autowired
    private JsonUtil jsonUtil;

    @Override
    public void run(String... args) throws Exception {







//        File file = new File("src/main/resources/test.json");
//        ObjectMapper mapper = new ObjectMapper();
//        //mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//
//        jsonUtil.jsonParserNodeChildren(file);






    }
}
