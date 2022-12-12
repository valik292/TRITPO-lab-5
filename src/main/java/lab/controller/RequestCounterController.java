package lab.controller;

import lab.counter.RequestCounter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestCounterController {
    Logger logger = LogManager.getLogger(RequestCounterController.class);

    @GetMapping(value = "/counter")
    public String getCounter() {
        logger.info("Visited RequestCounterController");
        return RequestCounter.getCounter() + " запросов было выполнено";
    }

}
