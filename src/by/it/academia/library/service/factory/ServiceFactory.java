package by.it.academia.library.service.factory;

import by.it.academia.library.service.ClientService;
import by.it.academia.library.service.LibraryService;
import by.it.academia.library.service.impl.ClientServiceImpl;
import by.it.academia.library.service.impl.LibraryServiceImpl;

final public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private final ClientService clientService = new ClientServiceImpl();
    private final LibraryService libraryService = new LibraryServiceImpl();

    private ServiceFactory(){}

    public static ServiceFactory getInstance(){
        return instance;
    }
    public ClientService getClientService(){
        return clientService;
    }
    public LibraryService getLibraryService(){
        return libraryService;
    }
}
