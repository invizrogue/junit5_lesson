package study.qa;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;

public class MiniJUnit {

    public static void main(String[] args) throws Exception {
        // собираем все методы в классе
        Method[] methods = DemoTest.class.getDeclaredMethods();
        // пробегаем по методам
        for (Method method : methods) {
            // выделяем метод только с аннотацией Test
            Test annotation = method.getAnnotation(Test.class);
            // если такая аннотация найдена, то переменная не будет пустой
            if (annotation != null) {
                // создаём инстанс
                DemoTest instance = DemoTest.class.getConstructor().newInstance();
                try {
                    // запускаем метод в инстансе
                    method.invoke(instance);
                } catch (Exception e) {
                    // если при выполнении метод падает с ошибкой, то ловим её
                    System.out.println("TEST FAILED!!!");
                    return;
                }
                // иначе говорим, что тестпройден
                System.out.println("TEST PASSED!!!");
            }
        }
    }
}
