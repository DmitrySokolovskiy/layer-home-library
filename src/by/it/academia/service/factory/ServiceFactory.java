package by.it.academia.service.factory;

import by.it.academia.service.ClientService;
import by.it.academia.service.LibraryService;
import by.it.academia.service.impl.ClientServiceImpl;
import by.it.academia.service.impl.LibraryServiceImpl;

final public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private final ClientService clientService = new ClientServiceImpl();
    private final LibraryService libraryService = new LibraryServiceImpl();

    private ServiceFactory(){}

    public static ServiceFactory getInstance(){
        return instance;
    }
    public ClientService getCLientService(){
        return clientService;
    }
    public LibraryService getLibraryService(){
        return libraryService;
    }
}
