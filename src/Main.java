import by.it.academia.library.controller.Controller;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Controller controller = new Controller();
        Scanner sc = new Scanner(System.in);

        //Авторизация signIn
        String request = "SIGN_IN";
//        System.out.println("Введите логин:");
//        request +=  " " + sc.nextLine();
//        System.out.println("Введите пароль:");
//        request +=  " " + sc.nextLine();

        String result = controller.executeTask(request);
        result = "Welcome!!!";
        while (result != "Welcome!!!"){
            try {
                Runtime.getRuntime().exec("cmd /c cls");
            } catch (IOException e){
                System.out.println("OOPS" + e);
            }

            System.out.println("\nЛогин или пароль не верен");
            request = "SIGN_IN";
            System.out.println("Введите логин:");
            request +=  " " + sc.nextLine();
            System.out.println("Введите пароль:");
            request +=  " " + sc.nextLine();
            result = controller.executeTask(request);
        }
        System.out.println(result);

        //Выводим список книг:
//        request = "PRINT_BOOK_LIST";
//        result = controller.executeTask(request);
//        System.out.println(result);

        //Добавление новой книги
        request = "ADD_BOOK#1238#\"4 градуса по Фаренгейту\"#Рей Брэдбери#2008#256#Роман,Научная фантастика,Политическаяфантастика,Антиутопическаялитература";
        result = controller.executeTask(request+" ");
        System.out.println(result);

        //Удаление книги
        request = "DELETE_BOOK#1238#\"4 градуса по Фаренгейту\"#Рей Брэдбери#2008#256#Роман,Научная фантастика,Политическаяфантастика,Антиутопическаялитература";
        result = controller.executeTask(request+" ");
        System.out.println(result);

        request = "DELETE_BOOK#6542#\"Портрет Дориана Грея\"#Оскар Уайльд#1890#530#Готика, Философский роман";
        result = controller.executeTask(request+" ");
        System.out.println(result);

        request = "ADD_BOOK#6542#\"Портрет Дориана Грея\"#Оскар Уайльд#1890#530#Готика, Философский роман";
        result = controller.executeTask(request+" ");
        System.out.println(result);



        //Регистрация
//        param.clear();
//        param.put("command", "REGISTRATION");
//        param.put("login", "Sokolovs_D");
//        param.put("password", "password");
//        param.put("email", "mail@mail.ru");
//        param.put("role", "User");
//        System.out.println(controller.executeTask(param));
    }
}
