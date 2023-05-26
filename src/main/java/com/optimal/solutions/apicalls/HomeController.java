package com.optimal.solutions.apicalls;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @Autowired
    private MyFeignClientForAPI feignClient;

    @Autowired
    private MyWebClient webClient;

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView result = new ModelAndView();
        result.setViewName("index");
        return result;
    }

    private String getFirstMeaningFromJSON(String json) {
        try {
            final JSONArray obj = new JSONArray(json);
            String meaning = obj.getJSONObject(0).getJSONArray("meanings")
                    .getJSONObject(0).getJSONArray("definitions")
                    .getJSONObject(0).getString("definition");
            return meaning;
        } catch (Exception e) {
            e.printStackTrace();
            return "Sorry, don't know the meaning";
        }
    }


    @GetMapping("/lookup")
    public ModelAndView lookupWord(HttpServletRequest request) {
        String word = request.getParameter("word");
        String meaning = "";
        try {
            meaning = getFirstMeaningFromJSON(feignClient.lookupWord(word));
        } catch (Exception e) {
            meaning = "Sorry, not found";
        }

        ModelAndView result = new ModelAndView();
        result.setViewName("definition");
        result.addObject("word",word);
        result.addObject("meaning",meaning);
        return result;
    }

    @GetMapping("/lookup2")
    public ModelAndView lookupWord2(HttpServletRequest request) {
        String word = request.getParameter("word");
        String meaning = "";
        try {
            meaning = getFirstMeaningFromJSON(RestTemplateUtil.getMeaning(word));
        } catch (Exception e) {
            meaning = "Sorry, not found";
        }

        ModelAndView result = new ModelAndView();
        result.setViewName("definition");
        result.addObject("word",word);
        result.addObject("meaning",meaning);
        return result;
    }

    @GetMapping("/lookup3")
    public ModelAndView lookupWord3(HttpServletRequest request) {
        String word = request.getParameter("word");
        String meaning = "";
        try {
            meaning = getFirstMeaningFromJSON(webClient.lookupWord(word));
        } catch (Exception e) {
            meaning = "Sorry, not found";
        }

        ModelAndView result = new ModelAndView();
        result.setViewName("definition");
        result.addObject("word",word);
        result.addObject("meaning",meaning);
        return result;
    }
}
