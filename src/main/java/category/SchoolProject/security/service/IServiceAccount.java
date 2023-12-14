package category.SchoolProject.security.service;

import category.SchoolProject.security.entities.AppUser;

public interface IServiceAccount {
    public void addUser(String userName, String password, String mail);
    public void addRole(String nom);
    public void addRoleToUser(String userName, String nameRole);
    public void deleteRoleToUser(String userName, String nameRole);
    public AppUser getAppUser(String userName);
}
