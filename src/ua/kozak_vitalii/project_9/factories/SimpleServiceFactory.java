package ua.kozak_vitalii.project_9.factories;

import com.sun.istack.internal.NotNull;
import ua.kozak_vitalii.project_9.service.AdminService;
import ua.kozak_vitalii.project_9.service.AdminServiceImpl;
import ua.kozak_vitalii.project_9.service.ClientService;
import ua.kozak_vitalii.project_9.service.ClientServiceImpl;

public class SimpleServiceFactory extends ServiceFactory {

    private static SimpleServiceFactory instance;
    private DaoFactory daoFactory;
    private AdminService adminService;
    private ClientService clientService;

    private SimpleServiceFactory(@NotNull DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
        adminService = new AdminServiceImpl(daoFactory.getUserDao(), daoFactory.getProductDao(), daoFactory.getCategoryDao(), daoFactory.getOrderDao());
        clientService = new ClientServiceImpl(daoFactory.getUserDao(), daoFactory.getProductDao(), daoFactory.getCategoryDao(), daoFactory.getOrderDao());
    }

    public static SimpleServiceFactory getInstance(@NotNull DaoFactory daoFactory) {
        synchronized (SimpleServiceFactory.class) {
            if (instance == null) {
                instance = new SimpleServiceFactory(daoFactory);
            }
        }
        return instance;
    }

    @Override
    public AdminService getAdminService() {
        return adminService;
    }

    @Override
    public ClientService getClientService() {
        return clientService;
    }

}
