import by.it.academia.library.controller.Controller;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        while (true){
            boolean authorized = false;
            Controller controller = new Controller();
            Scanner sc = new Scanner(System.in);

            //Авторизация signIn
            String request = "SIGN_IN";
            System.out.println("Введите логин:");
            request +=  "#" + sc.nextLine();
            System.out.println("Введите пароль:");
            request +=  "#" + sc.nextLine();

            String result = controller.executeTask(request);
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

            authorized = true;
            System.out.println(result);
            while (authorized){
                System.out.println("Наш текущий список книг:");
                request = "PRINT_BOOK_LIST";
                result = controller.executeTask(request);
                System.out.println(result);

                System.out.println();


                System.out.println("Выберите из доступых функций:");
                System.out.println("1: Добавить книгу в библиотеку");
                System.out.println("2: Удалить книгу из библиотеки");
                System.out.println("3: Вывести список всех книг");
                System.out.println("4: Выйти из системы");
                System.out.println("5: Повторить вывод доступного меню");
                int choice = 0;
                while (choice != 4){
                    boolean validValue = false;
                    while (!validValue){
                        if (sc.hasNextInt()){
                            choice = sc.nextInt();
                            if (choice <= 5 && choice >= 1){
                                validValue = true;
                            }
                        }
                    }

                    switch (choice){
                        case 1:
                            //Добавление новой книги, не добавлял ввод для экономии времени:)
                            request = "ADD_BOOK#1238#\"4 градуса по Фаренгейту\"#Рей Брэдбери#2008#256#Роман," +
                                    "Научная фантастика,Политическаяфантастика,Антиутопическаялитература";
                            result = controller.executeTask(request+" ");
                            System.out.println(result);
                            break;
                        case 2:
                            //Удаление книги, не добавлял ввод для экономии времени:)
                            request = "DELETE_BOOK#1238#\"4 градуса по Фаренгейту\"#Рей Брэдбери#2008#256#Роман," +
                                    "Научная фантастика,Политическаяфантастика,Антиутопическаялитература";
                            result = controller.executeTask(request+" ");
                            System.out.println(result);
                            break;
                        case 3:
                            request = "PRINT_BOOK_LIST";
                            result = controller.executeTask(request);
                            System.out.println(result);
                            break;
                        case 4:
                            choice = 4;
                            break;
                        case 5:
                            System.out.println("Выберите из доступых функций:");
                            System.out.println("1: Добавить книгу в библиотеку");
                            System.out.println("2: Удалить книгу из библиотеки");
                            System.out.println("3: Вывести список всех книг");
                            System.out.println("4: Выйти из системы");
                            System.out.println("5: Повторить вывод доступного меню");
                            break;
                    }
                }
                authorized = false;
                System.out.println("Спасибо, вы разлогированны!");

            }
        }

    }
}
