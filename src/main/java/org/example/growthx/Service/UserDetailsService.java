package org.example.growthx.Service;

import org.example.growthx.repository.Userrepos;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    protected final Userrepos userrepos;

    public UserDetailsService(Userrepos userrepos) {
        this.userrepos = userrepos;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userrepos.findUserByUsername(username).orElseThrow(()->new UsernameNotFoundException("usernamenot founf"));

    }
}
