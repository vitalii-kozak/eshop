package ua.kozak_vitalii.project_9.factories;

import ua.kozak_vitalii.project_9.commands.*;
import ua.kozak_vitalii.project_9.enums.DaoType;
import ua.kozak_vitalii.project_9.enums.ServiceType;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {

    private static Map<String,Command> commands;

    static {
        ServiceFactory serviceFactory;
        DaoFactory daoFactory = DaoFactory.getFactory(DaoType.JDBC);
        serviceFactory = ServiceFactory.getFactory(daoFactory, ServiceType.SIMPLE);

        commands = new HashMap<>();
        commands.put("ADD", new AddToCartCommand());
        commands.put("DELETE", new DeleteFromCartCommand());
        commands.put("CHECKOUT", new CheckOutCommand());
        commands.put("AUTHENTICATE", new AuthenticateCommand(serviceFactory.getAdminService()));
        commands.put("CLIENT_REGISTRATION", new ClientRegistrationCommand());
        commands.put("ADD_NEW_USER", new AddNewUserCommand(serviceFactory.getAdminService()));
        commands.put("ADD_NEW_USER_BY_ADMIN", new AddNewUserAdminCommand(serviceFactory.getAdminService()));
        commands.put("UPDATE_USER", new MenuUpdateUserAdminCommand(serviceFactory.getAdminService()));
        commands.put("SELECT_USER", new SelectUserAdminCommand(serviceFactory.getAdminService()));
        commands.put("UPDATE_USER_INFO", new UpdateUserInfoAdminCommand(serviceFactory.getAdminService()));

        commands.put("LOGOUT", new LogOutCommand());

    }

    private CommandFactory() {
    }

    public static Command getCommand(String commandName) {
        return commands.get(commandName);
    }
}
