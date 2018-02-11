package com.djamware.angular.security;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

@Document(collection = "mongosecurity")
public class MongoUserDetails  implements UserDetails{
    @Id
    String id;
    private String username;
    private String password;
    private List<GrantedAuthority> grantedAuthorities;

    public MongoUserDetails() { }

    public MongoUserDetails(String username,String password,String[] authorities) {
        this.username = username;
        this.password = password;
        System.out.println( "Autorities: " + (authorities==null? "null" : authorities.length));
        if( authorities != null) {
            for( String aut : authorities) {
                System.out.println( " > autority = " + aut);
            }
        }
        this.grantedAuthorities = AuthorityUtils.createAuthorityList(authorities);
    }
    public void setAuthorities( String[] authorities) {
        this.grantedAuthorities = AuthorityUtils.createAuthorityList(authorities);
        System.out.println( "Setting authorities: " + authorities);
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        return username;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "MongoUserDetails{" +
                "username='" + username + '\'' +
                ", password='" + password + "' }";
    }
}