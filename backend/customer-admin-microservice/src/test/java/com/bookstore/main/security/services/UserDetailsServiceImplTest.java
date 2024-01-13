package com.bookstore.main.security.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bookstore.main.models.ERole;
import com.bookstore.main.models.Role;
import com.bookstore.main.models.User;
import com.bookstore.main.repository.UserRepository;

import java.util.Collection;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserDetailsServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserDetailsServiceImplTest {
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @MockBean
    private UserRepository userRepository;

    @Test
    void testLoadUserByUsername() throws UsernameNotFoundException {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setId(123L);
        user.setRoles(new HashSet<>());
        Optional<User> ofResult = Optional.of(user);
        when(this.userRepository.findByEmail((String) any())).thenReturn(ofResult);
        UserDetails actualLoadUserByUsernameResult = this.userDetailsServiceImpl.loadUserByUsername("janedoe");
        assertTrue(actualLoadUserByUsernameResult.getAuthorities().isEmpty());
        assertEquals("jane.doe@example.org", actualLoadUserByUsernameResult.getUsername());
        assertEquals("iloveyou", actualLoadUserByUsernameResult.getPassword());
        assertEquals(123L, ((UserDetailsImpl) actualLoadUserByUsernameResult).getId().longValue());
        verify(this.userRepository).findByEmail((String) any());
    }

    @Test
    void testLoadUserByUsername2() throws UsernameNotFoundException {
        Role role = new Role();
        role.setId(1);
        role.setName(ERole.ROLE_USER);

        HashSet<Role> roleSet = new HashSet<>();
        roleSet.add(role);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setId(123L);
        user.setRoles(roleSet);
        Optional<User> ofResult = Optional.of(user);
        when(this.userRepository.findByEmail((String) any())).thenReturn(ofResult);
        UserDetails actualLoadUserByUsernameResult = this.userDetailsServiceImpl.loadUserByUsername("janedoe");
        Collection<? extends GrantedAuthority> authorities = actualLoadUserByUsernameResult.getAuthorities();
        assertEquals(1, authorities.size());
        assertEquals("jane.doe@example.org", ((UserDetailsImpl) actualLoadUserByUsernameResult).getEmail());
        assertEquals("iloveyou", actualLoadUserByUsernameResult.getPassword());
        assertEquals(123L, ((UserDetailsImpl) actualLoadUserByUsernameResult).getId().longValue());
        assertEquals("ROLE_USER", ((List<? extends GrantedAuthority>) authorities).get(0).getAuthority());
        verify(this.userRepository).findByEmail((String) any());
    }

    @Test
    void testLoadUserByUsername3() throws UsernameNotFoundException {
        Role role = new Role();
        role.setId(1);
        role.setName(ERole.ROLE_USER);

        Role role1 = new Role();
        role1.setId(1);
        role1.setName(ERole.ROLE_USER);

        HashSet<Role> roleSet = new HashSet<>();
        roleSet.add(role1);
        roleSet.add(role);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setId(123L);
        user.setRoles(roleSet);
        Optional<User> ofResult = Optional.of(user);
        when(this.userRepository.findByEmail((String) any())).thenReturn(ofResult);
        UserDetails actualLoadUserByUsernameResult = this.userDetailsServiceImpl.loadUserByUsername("janedoe");
        Collection<? extends GrantedAuthority> authorities = actualLoadUserByUsernameResult.getAuthorities();
        assertEquals(2, authorities.size());
        assertEquals("jane.doe@example.org", ((UserDetailsImpl) actualLoadUserByUsernameResult).getEmail());
        assertEquals("iloveyou", actualLoadUserByUsernameResult.getPassword());
        assertEquals(123L, ((UserDetailsImpl) actualLoadUserByUsernameResult).getId().longValue());
        assertEquals("ROLE_USER", ((List<? extends GrantedAuthority>) authorities).get(0).toString());
        assertEquals("ROLE_USER", ((List<? extends GrantedAuthority>) authorities).get(1).toString());
        verify(this.userRepository).findByEmail((String) any());
    }

    @Test
    void testLoadUserByUsername4() throws UsernameNotFoundException {
        when(this.userRepository.findByEmail((String) any())).thenReturn(Optional.empty());
        assertThrows(UsernameNotFoundException.class, () -> this.userDetailsServiceImpl.loadUserByUsername("janedoe"));
        verify(this.userRepository).findByEmail((String) any());
    }
}

