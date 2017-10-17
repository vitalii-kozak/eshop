package ua.kozak_vitalii.project_9.factories;

import com.sun.istack.internal.NotNull;
import ua.kozak_vitalii.project_9.enums.ServiceType;
import ua.kozak_vitalii.project_9.service.*;


public abstract class ServiceFactory {

    public abstract AdminService getAdminService();
    public abstract ClientService getClientService();

    public static ServiceFactory getFactory(@NotNull DaoFactory daoFactory, ServiceType type) {
        if (type == ServiceType.SIMPLE) {
            return SimpleServiceFactory.getInstance(daoFactory);
        }
        return null;
    }
}
