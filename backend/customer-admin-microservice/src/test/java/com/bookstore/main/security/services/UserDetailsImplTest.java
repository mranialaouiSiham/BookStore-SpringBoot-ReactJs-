package com.bookstore.main.security.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.bookstore.main.models.ERole;
import com.bookstore.main.models.Role;
import com.bookstore.main.models.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

class UserDetailsImplTest {
    @Test
    void testConstructor() {
        ArrayList<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        UserDetailsImpl actualUserDetailsImpl = new UserDetailsImpl(123L, "jane.doe@example.org", "iloveyou",
                grantedAuthorityList);

        assertSame(grantedAuthorityList, actualUserDetailsImpl.getAuthorities());
        assertEquals("jane.doe@example.org", actualUserDetailsImpl.getEmail());
        assertEquals(123L, actualUserDetailsImpl.getId().longValue());
        assertEquals("iloveyou", actualUserDetailsImpl.getPassword());
        assertEquals("jane.doe@example.org", actualUserDetailsImpl.getUsername());
        assertTrue(actualUserDetailsImpl.isAccountNonExpired());
        assertTrue(actualUserDetailsImpl.isAccountNonLocked());
        assertTrue(actualUserDetailsImpl.isCredentialsNonExpired());
        assertTrue(actualUserDetailsImpl.isEnabled());
    }

    @Test
    void testBuild() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setId(123L);
        user.setRoles(new HashSet<>());
        UserDetailsImpl actualBuildResult = UserDetailsImpl.build(user);
        assertTrue(actualBuildResult.getAuthorities().isEmpty());
        assertEquals("jane.doe@example.org", actualBuildResult.getUsername());
        assertEquals("iloveyou", actualBuildResult.getPassword());
        assertEquals(123L, actualBuildResult.getId().longValue());
    }

    @Test
    void testBuild2() {
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
        UserDetailsImpl actualBuildResult = UserDetailsImpl.build(user);
        Collection<? extends GrantedAuthority> authorities = actualBuildResult.getAuthorities();
        assertEquals(1, authorities.size());
        assertEquals("jane.doe@example.org", actualBuildResult.getEmail());
        assertEquals("iloveyou", actualBuildResult.getPassword());
        assertEquals(123L, actualBuildResult.getId().longValue());
        assertEquals("ROLE_USER", ((List<? extends GrantedAuthority>) authorities).get(0).getAuthority());
    }

    @Test
    void testBuild3() {
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
        UserDetailsImpl actualBuildResult = UserDetailsImpl.build(user);
        Collection<? extends GrantedAuthority> authorities = actualBuildResult.getAuthorities();
        assertEquals(2, authorities.size());
        assertEquals("jane.doe@example.org", actualBuildResult.getEmail());
        assertEquals("iloveyou", actualBuildResult.getPassword());
        assertEquals(123L, actualBuildResult.getId().longValue());
        assertEquals("ROLE_USER", ((List<? extends GrantedAuthority>) authorities).get(0).toString());
        assertEquals("ROLE_USER", ((List<? extends GrantedAuthority>) authorities).get(1).toString());
    }

    @Test
    void testEquals() {
        assertFalse(UserDetailsImpl.build(new User("jane.doe@example.org", "iloveyou")).equals(null));
        assertFalse(UserDetailsImpl.build(new User("jane.doe@example.org", "iloveyou"))
                .equals("Different type to UserDetailsImpl"));
    }

    @Test
    void testEquals2() {
        UserDetailsImpl buildResult = UserDetailsImpl.build(new User("jane.doe@example.org", "iloveyou"));
        assertTrue(buildResult.equals(buildResult));
        int expectedHashCodeResult = buildResult.hashCode();
        assertEquals(expectedHashCodeResult, buildResult.hashCode());
    }

    @Test
    void testEquals3() {
        UserDetailsImpl buildResult = UserDetailsImpl.build(new User("jane.doe@example.org", "iloveyou"));
        UserDetailsImpl buildResult1 = UserDetailsImpl.build(new User("jane.doe@example.org", "iloveyou"));
        assertTrue(buildResult.equals(buildResult1));
        int notExpectedHashCodeResult = buildResult.hashCode();
        assertFalse(Objects.equals(notExpectedHashCodeResult, buildResult1.hashCode()));
    }

    @Test
    void testEquals4() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setId(123L);
        user.setRoles(new HashSet<>());
        UserDetailsImpl buildResult = UserDetailsImpl.build(user);
        assertFalse(buildResult.equals(UserDetailsImpl.build(new User("jane.doe@example.org", "iloveyou"))));
    }
}

