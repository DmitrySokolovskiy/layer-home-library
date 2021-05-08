import by.it.academia.library.controller.Controller;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        while (true){
            int choice = 0;
            boolean authorized;
            String authorizedUser = "";

            Controller controller = new Controller();
            Scanner sc = new Scanner(System.in);

            //Выбираем авторизация или регистрацию:
            while (!(choice == 1 | choice == 2) ){
                System.out.println("Выберите 1 или 2 для входа:");
                System.out.println("1 - Авторизация");
                System.out.println("2 - Регистрация");
                if (sc.hasNextInt()){
                    choice = sc.nextInt();
                }
                if (choice == 1){
                    //Авторизация signIn
                    sc.nextLine();
                    String request = "SIGN_IN";
                    System.out.println("Введите логин:");
                    request +=  "#" + sc.nextLine();
                    System.out.println("Введите пароль:");
                    request +=  "#" + sc.nextLine();

                    String result = controller.executeTask(request);

                    while (!result.equals("Welcome!!!")){

                        System.out.println("\nЛогин или пароль не верен");
                        request = "SIGN_IN";
                        System.out.println("Введите логин:");
                        request +=  " " + sc.nextLine();
                        System.out.println("Введите пароль:");
                        request +=  " " + sc.nextLine();
                        result = controller.executeTask(request);
                    }
                    authorizedUser = request.split("#")[1];
                }else if (choice == 2){
                    //Регистрация
                    sc.nextLine();
                    String request = "REGISTRATION";
                    System.out.println("Введите логин:");
                    request +=  "#" + sc.nextLine();
                    System.out.println("Введите пароль:");
                    request +=  "#" + sc.nextLine();
                    System.out.println("Введите почту:");
                    request +=  "#" + sc.nextLine();

                    String result = controller.executeTask(request);
                    System.out.println(result);

                    if(!result.equals("Done!!!")){
                        System.out.println("Повторить регистрацию?Y/N");
                        if (sc.nextLine().toLowerCase().equals("n")){
                            choice = 0;
                        }
                    }

                    while (!result.equals("Done!!!") && choice !=0 ){
                        request = "REGISTRATION";
                        System.out.println("Введите логин:");
                        request +=  "#" + sc.nextLine();
                        System.out.println("Введите пароль:");
                        request +=  "#" + sc.nextLine();
                        System.out.println("Введите почту:");
                        request +=  "#" + sc.nextLine();

                        result = controller.executeTask(request);
                        System.out.println(result);
                        if(!result.equals("Done!!!")){
                            System.out.println("Повторить регистрацию?Y/N");
                            if (sc.nextLine().toLowerCase().equals("n")){
                                choice = 0;
                            }
                        }
                    }
                    authorizedUser = request.split("#")[1];
                }
            }


///////////////////////////////////////////////////////////
            System.out.println("Вы авторизированны как: " + authorizedUser);

            authorized = true;
            while (authorized){
                System.out.println("Наш текущий список книг:");
                String request = "PRINT_BOOK_LIST";
                String result = controller.executeTask(request);
                System.out.println(result);

                System.out.println();


                System.out.println("Выберите из доступых функций:");
                System.out.println("1: Добавить книгу в библиотеку");
                System.out.println("2: Удалить книгу из библиотеки");
                System.out.println("3: Вывести список всех книг");
                System.out.println("4: Повторить вывод доступного меню");
                System.out.println("5: Вывод подробной информации о книге");
                System.out.println("6: Выйти из системы");

                choice = 0;

                while (choice != 6){
                    boolean validValue = false;
                    while (!validValue){
                        if (sc.hasNextInt()){
                            choice = sc.nextInt();
                            if (choice <= 6 && choice >= 1){
                                validValue = true;
                            }
                        }
                    }

                    switch (choice){
                        case 1:
                            //Добавление новой книги, не добавлял ввод для экономии времени:)
                            request = "ADD_BOOK#\"4 градуса по Фаренгейту\"#Рей Брэдбери#2008#256#Роман," +
                                    "Научная фантастика,Политическаяфантастика,Антиутопическаялитература#" + authorizedUser;
                            result = controller.executeTask(request+" ");
                            System.out.println(result);
                            break;
                        case 2:
                            //Удаление книги, не добавлял ввод для экономии времени:)
                            request = "DELETE_BOOK#1118#" + authorizedUser;
                            result = controller.executeTask(request+" ");
                            System.out.println(result);
                            break;
                        case 3:
                            request = "PRINT_BOOK_LIST";
                            result = controller.executeTask(request);
                            System.out.println(result);
                            break;
                        case 4:
                            System.out.println("Выберите из доступых функций:");
                            System.out.println("1: Добавить книгу в библиотеку");
                            System.out.println("2: Удалить книгу из библиотеки");
                            System.out.println("3: Вывести список всех книг");
                            System.out.println("4: Повторить вывод доступного меню");
                            System.out.println("5: Вывод подробной информации о книге");
                            System.out.println("6: Выйти из системы");
                            break;
                        case 5:
                            request = "GET_BOOK_DETAIL_INFO#1111";
                            result = controller.executeTask(request);
                            System.out.println(result+'\n');
                            break;
                        case 6:
                            choice = 6;
                            break;
                    }
                    System.out.println("Выберите из доступых функций:");
                    System.out.println("1: Добавить книгу в библиотеку");
                    System.out.println("2: Удалить книгу из библиотеки");
                    System.out.println("3: Вывести список всех книг");
                    System.out.println("4: Повторить вывод доступного меню");
                    System.out.println("5: Вывод подробной информации о книге");
                    System.out.println("6: Выйти из системы");
                }

                authorized = false;
                System.out.println("Спасибо, вы разлогированны!");

            }
        }

    }
}
