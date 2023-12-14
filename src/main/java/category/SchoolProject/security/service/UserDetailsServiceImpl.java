package category.SchoolProject.security.service;

import category.SchoolProject.security.entities.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    IServiceAccount serviceAccount;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser =serviceAccount.getAppUser(username);
        if (appUser==null) throw new UsernameNotFoundException("User Not Found");
        List<GrantedAuthority> authorityList = new ArrayList<>();
        appUser.getRoles().forEach(e->authorityList.add(new SimpleGrantedAuthority(e.getNom())));
        return new User(appUser.getUserName(),appUser.getPassword(),authorityList);
    }
}
