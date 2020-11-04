package by.epamtc.birukov.service;

import by.epamtc.birukov.service.impl.ClientServiceImpl;

public class ServiceProvider {

    private static final ServiceProvider instance = new ServiceProvider();
    public static ServiceProvider getInstance(){
        return instance;
    }

    public ServiceProvider() {
    }

    private final ClientService serviceProvider = new ClientServiceImpl();

    public ClientService getClientService(){
        return serviceProvider;
    }


}
