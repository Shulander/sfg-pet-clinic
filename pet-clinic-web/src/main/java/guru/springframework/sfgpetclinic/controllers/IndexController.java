package guru.springframework.sfgpetclinic.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IndexController {

    @RequestMapping({"", "/", "index.html"})
    public String index() {
        log.info("index mapping");
        return "index";
    }

    @RequestMapping({"/oups"})
    public String notImplemented() {
        return "notimplemented";
    }
}
