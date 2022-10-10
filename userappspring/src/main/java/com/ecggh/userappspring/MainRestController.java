package com.ecggh.userappspring;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/0.1")
public class MainRestController
{

    Logger logger = LoggerFactory.getLogger(MainRestController.class);


    @RequestMapping("/loggertest")
    public String index()
    {
        logger.trace("A TRACE Message");
        logger.debug("A DEBUG Message");
        logger.info("An INFO Message");
        logger.warn("A WARN Message");
        logger.error("An ERROR Message");

        return "Howdy! Check out the Logs to see the output...";
    }



}
