package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Автор: https://github.com/VlasovM
 * В данной задаче знакомимся с инструментом для простого создания веб-приложений - SpringBoot.
 * Данная программа будет выводить список заданий, которые будут добавляться в локальную БД.
 * Фронт-части в приложении нет. Чтобы добавить, удалить или изменить дела рекомендую воспользоваться
 * расширением Google Chrome: Talend API Tester.
 * Более подробная информация по решению задачки:
 * https://vk.com/@javlasov-znakomstvo-s-spring-framework-sozdanie-bek-chasti-veb-priloz
 * https://vk.com/@javlasov-uluchshenie-back-chasti-veb-prilozheniya-pro-rest-api-mvc-cr
 */


@SpringBootApplication
public class ToDoList {
    public static void main(String[] args) {
        SpringApplication.run(ToDoList.class, args);
    }
}
