package ru.geekbrains.lesson_4_spring_boot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.geekbrains.lesson_4_spring_boot.persist.UserRepository;
import java.util.stream.Collectors;


@Service
public class UserDetailServiceImpl implements UserDetailsService {


    private  UserRepository userRepository;



    @Autowired
    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username)
                .map(user -> new User(
                        user.getLogin(),
                        user.getPassword(),
                        user.getRoles().stream().map(role->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
                )).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}

