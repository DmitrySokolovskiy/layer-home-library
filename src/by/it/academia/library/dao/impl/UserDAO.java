package by.it.academia.library.dao.impl;

import by.it.academia.library.bean.User;
import by.it.academia.library.dao.FileUserDAO;
import by.it.academia.library.dao.exception.DAOException;
import by.it.academia.library.dao.exception.DAOResourceNotFoundException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class UserDAO implements FileUserDAO {
    private final String fileName = "newFile.txt";
    private final String delimeter = "#";

    @Override
    public void signIn(String login, String password) throws DAOException, DAOResourceNotFoundException {
        String filePath = String.format(".\\resources\\%s", fileName);
        Path path = Paths.get(filePath);
            if (Files.exists(path)){
                try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if(line.split(delimeter)[0].equals(login) && line.split(delimeter)[1].equals(password)){
                            return;
                        }
                    }
                    throw new DAOResourceNotFoundException("Пользователь не найден");
                }catch (IOException e){
                    throw new DAOException("Не удалось открыть файл на чтение", e);
                } catch (DAOResourceNotFoundException e) {
                    throw new DAOResourceNotFoundException(e);
                }
            }
    }

//    @Override
//    public void signIn(String login, String password) throws DAOException, DAOResourceNotFoundException {
//        String filePath = String.format(".\\resources\\%s", fileName);
//        Path path = Paths.get(filePath);
//        ArrayList<String> result = new ArrayList();
//        try {
//            if (Files.exists(path)){
//                File file = new File(filePath);
//                file.createNewFile();
//            }
//            FileReader users = new FileReader(filePath);
//            int c;
//            String s = "";
//            while ((c = users.read())!=-1){
//                char temp = (char) c;
//                if (c != '\n' & c != '\r') {
//                    s += temp;
//                }else if (c != '\r') {
//                    result.add(s+'\n');
//                    s = "";
//                }
//            }
//            for(int i = 0; i < result.size()-1; i++){
//                String[] user = result.get(i).split("\\$");
//                if (user[0].equals(login) && user[1].equals(password)){
//                    user[4] = "yes\n";
//                    result.set(i,String.format("%s$%s$%s$%s$%s",user[0],user[1],user[2],user[3],user[4]));
//                    return;
//            }
//            }
//            throw new DAOResourceNotFoundException();
//        } catch (IOException e) {
//            throw new DAOException(e);
//        } catch (DAOResourceNotFoundException e){
//            throw new DAOResourceNotFoundException("Пользователь не найден");
//        }finally {
//            try {
//                FileWriter wr1 = new FileWriter(filePath,false);
//                wr1.close();
//                FileWriter wr = new FileWriter(filePath,false);
//                for (String a: result) {
//                    wr.write(a+"\r");
//            }
//                wr.close();
//            }catch (IOException e){
//                throw new DAOException(e);
//            }
//        }
//    }



    @Override
    public void signOut(String login) throws DAOException, DAOResourceNotFoundException {
        String filePath = String.format(".\\resources\\%s", fileName);
        ArrayList<String> result = new ArrayList();
        try {
            FileReader users = new FileReader(filePath);
            int c;
            String s = "";
            while ((c = users.read())!=-1){
                if (c != 13){
                    s += (char)c;
                } else {
                    result.add(s);
                    s = "";
                }
            }
            for(int i = 0; i < result.size()-1; i++){
                String[] user = result.get(i).split(delimeter);
                if (user[0].equals(login)){
                    user[4] = "no\n";
                    result.set(i,String.format("%s#%s#%s#%s#%s",user[0],user[1],user[2],user[3],user[4]));
                    return;
                }
            }
            throw new DAOResourceNotFoundException();
        } catch (IOException e) {
            throw new DAOException(e);
        } catch (DAOResourceNotFoundException e){
            throw new DAOResourceNotFoundException("Пользователь не залогинен");
        }finally {
            try {
                FileWriter wr1 = new FileWriter(filePath,false);
                wr1.close();
                FileWriter wr = new FileWriter(filePath,false);
                for (String a: result) {
                    wr.write(a+"\r");
                }
                wr.close();
            }catch (IOException e){
                throw new DAOException(e);
            }
        }
    }


    @Override
    public void registration(User user) throws DAOException, DAOResourceNotFoundException {
        String filePath = String.format(".\\resources\\%s", fileName);
        String login = user.getLogin();
        ArrayList<String> result = new ArrayList();
        try {
            FileReader users = new FileReader(filePath);
            int c;
            String s = "";
            while ((c = users.read()) != -1) {
                if (c != 13) {
                    s += (char) c;
                } else {
                    result.add(s);
                    s = "";
                }
            }
            for (String usr : result) {
                String[] userList = usr.split("\\$");
                if (userList[0].equals(login)) {
                    throw new DAOResourceNotFoundException();
                }
            }
            FileWriter wr = new FileWriter(filePath,true);
            wr.write(String.format("%s$%s$%s$%s$%s", user.getLogin(), user.getPassword(), user.getEmail(),
                    user.getRole(), "no"));
            wr.close();
        }catch (DAOResourceNotFoundException e){
            throw new DAOResourceNotFoundException("Пользователь уже зарегистрирован");
        } catch (IOException e) {
            throw new DAOException(e);
        }
    }
}
