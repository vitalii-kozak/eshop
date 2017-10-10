package ua.kozak_vitalii.project_9.domain;

import com.sun.istack.internal.NotNull;
import ua.kozak_vitalii.project_9.enums.UserType;

public class Admin extends User {

    public Admin(@NotNull String login, @NotNull String password, @NotNull String name, @NotNull String surname, @NotNull boolean isBlocked) {
        super(login, password, name, surname, isBlocked);
    }

    public Admin(@NotNull String email, @NotNull String password, @NotNull String name, @NotNull String surname) {
        super(email, password, name, surname);
    }

    @Override
    public UserType getUserType() {
        return UserType.ADMIN;
    }

    @Override
    public String toString() {
        String isBlockedText =  getIsBlocked() ?  "Denied" : "Allowed";
        return "Admin{" +
                "id=" + getId() +
                ", email='" + getLogin() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", name='" + getName() + '\'' +
                ", surname='" + getSurname() + '\'' +
                ", Access='" + isBlockedText +
                '}';
    }
}