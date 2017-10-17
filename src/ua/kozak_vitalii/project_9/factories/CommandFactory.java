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
        commands.put("", new IndexJspCommand());

        commands.put("ADD", new AddToCartCommand(serviceFactory.getAdminService()));
        commands.put("DELETE", new DeleteFromCartCommand(serviceFactory.getAdminService()));
        commands.put("CHECKOUT", new CheckOutCommand());
        commands.put("SEND_ORDER", new SendOrderCommand(serviceFactory.getAdminService()));

        commands.put("AUTHENTICATE", new AuthenticateCommand(serviceFactory.getAdminService()));
        commands.put("CLIENT_REGISTRATION", new ClientRegistrationCommand());

        commands.put("USER_INFO", new UserInfoCommand());
        commands.put("UPDATE_USER_PASSWORD", new UserPasswordChangeCommand());
        commands.put("NEW_USER_PASSWORD", new newUserPasswordCommand(serviceFactory.getAdminService()));

        commands.put("ADD_NEW_USER", new AddNewUserCommand(serviceFactory.getAdminService()));
        commands.put("ADD_NEW_USER_BY_ADMIN", new AddNewUserAdminCommand(serviceFactory.getAdminService()));
        commands.put("UPDATE_USER", new MenuUpdateUserAdminCommand(serviceFactory.getAdminService()));
        commands.put("SELECT_USER", new SelectUserAdminCommand(serviceFactory.getAdminService()));
        commands.put("UPDATE_USER_INFO", new UpdateUserInfoAdminCommand(serviceFactory.getAdminService()));

        commands.put("CATEGORY_CREATE_MENU", new CategoryCreateCommand(serviceFactory.getAdminService()));
        commands.put("CATEGORY_CREATE_LINK", new ReturnPageCategoryCreationCommand(serviceFactory.getAdminService()));
        commands.put("ADD_NEW_CATEGORY_BY_ADMIN", new AddNewCategoryCommand(serviceFactory.getAdminService()));
        commands.put("EDIT_CATEGORY_LINK", new ReturnPageEditCategoryCreationCommand(serviceFactory.getAdminService()));
        commands.put("UPDATE_CATEGORY_INFO", new UpdateCategoryInfoAdminCommand(serviceFactory.getAdminService()));

        commands.put("PRODUCT_CREATE_MENU", new ProductCreateCommand(serviceFactory.getAdminService()));
        commands.put("PRODUCT_CREATE_LINK", new ReturnPageProductCreationCommand(serviceFactory.getAdminService()));
        commands.put("ADD_NEW_PRODUCT_BY_ADMIN", new AddNewProductCommand(serviceFactory.getAdminService()));
        commands.put("EDIT_PRODUCT_LINK", new ReturnPageEditProductCreationCommand(serviceFactory.getAdminService()));
        commands.put("UPDATE_PRODUCT_INFO", new UpdateProductInfoAdminCommand(serviceFactory.getAdminService()));

        commands.put("CLIENT_INFO", new ClientInfoCommand());
        commands.put("ORDER_CREATE_MENU", new OrderCreateCommand(serviceFactory.getAdminService()));

        commands.put("LOGOUT", new LogOutCommand());

    }

    private CommandFactory() {
    }

    public static Command getCommand(String commandName) {
        return commands.get(commandName);
    }
}
