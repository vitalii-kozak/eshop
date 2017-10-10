package ua.kozak_vitalii.project_9.factories;

import ua.kozak_vitalii.project_9.commands.*;
import ua.kozak_vitalii.project_9.enums.DaoType;
import ua.kozak_vitalii.project_9.enums.ServiceType;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
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
    }

    private CommandFactory() {
    }

    public static Command getCommand(String commandName) {
        return commands.get(commandName);
    }
}
