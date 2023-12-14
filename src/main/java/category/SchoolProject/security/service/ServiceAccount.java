package category.SchoolProject.security.service;

import category.SchoolProject.security.entities.AppRole;
import category.SchoolProject.security.entities.AppUser;
import category.SchoolProject.security.repository.RoleRepository;
import category.SchoolProject.security.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class ServiceAccount implements IServiceAccount{
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public void addUser(String userName, String password, String mail) {
        AppUser user= userRepository.findAppUserByUserName(userName);
        if (user!=null)
            throw new RuntimeException("This User Already Exists");
        userRepository.save(
                AppUser.builder().
                        id(UUID.randomUUID().toString())
                        .userName(userName)
                        .email(mail)
                        .password(passwordEncoder.encode(password))
                        .build()
        );
    }

    @Override
    public void addRole(String nom) {
        AppRole role=roleRepository.findById(nom).orElse(null);
        if(role!=null) throw new RuntimeException("This Role already exists");
        roleRepository.save(AppRole.builder().nom(nom).build());
    }

    @Override
    public void addRoleToUser(String userName, String nameRole) {
        AppUser user= userRepository.findAppUserByUserName(userName);
        AppRole role= roleRepository.findById(nameRole).get();
        user.getRoles().add(role);
    }

    @Override
    public void deleteRoleToUser(String userName, String nameRole) {

    }

    @Override
    public AppUser getAppUser(String userName) {
        return userRepository.findAppUserByUserName(userName);
    }
}
