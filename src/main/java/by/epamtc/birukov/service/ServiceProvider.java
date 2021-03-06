package by.epamtc.birukov.service;

import by.epamtc.birukov.service.impl.ClientServiceImpl;
import by.epamtc.birukov.service.impl.TestServiceImpl;

public class ServiceProvider {

    private static final ServiceProvider instance = new ServiceProvider();
    public static ServiceProvider getInstance(){
        return instance;
    }

    public ServiceProvider() {
    }

    private final ClientService clientService = new ClientServiceImpl();
    private final TestService testService = new TestServiceImpl();

    public ClientService getClientService(){
        return clientService;
    }

    public TestService getTestService() {
        return testService;
    }
}
