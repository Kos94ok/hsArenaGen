package com.tianara.hsArenaGen.hsArenaGen;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public ModelAndView getIndex() {

        Map<String, Object> model = new HashMap<>();
        model.put("name", "teppo");

        return new ModelAndView("index", model);
    }
}
