package main;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {

    @RequestMapping("/")
    public String homePage() {
        return "Добро пожаловать на стартовую страницу приложения toDo List!";
    }
}
