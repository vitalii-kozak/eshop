package ua.kozak_vitalii.project_9.factories;

import com.sun.istack.internal.NotNull;
import ua.kozak_vitalii.project_9.service.AdminService;
import ua.kozak_vitalii.project_9.service.AdminServiceImpl;
import ua.kozak_vitalii.project_9.service.ClientService;

public class SimpleServiceFactory extends ServiceFactory {

    private static SimpleServiceFactory instance;
    private DaoFactory daoFactory;

    private SimpleServiceFactory(@NotNull DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
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
        return new AdminServiceImpl(daoFactory.getUserDao(), daoFactory.getProductDao(), daoFactory.getCategoryDao());
    }

    @Override
    public ClientService getUserService() {
        return null;
    }

}
