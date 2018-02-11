package com.djamware.angular.service;

import com.djamware.angular.repositories.MongoSecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.djamware.angular.security.MongoUserDetails;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

public class CustomerUserDetailsService implements UserDetailsService {
    @Autowired
    private MongoSecurityRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MongoUserDetails user = repository.findByUsername(username);
        if( user == null) {
            throw new UsernameNotFoundException( "Username not found " + username);
        }
        System.out.println( " username found: " + user.getUsername());
        if( user.getAuthorities() == null) {
            System.out.println( " authorities null. Correcting");
            String[] auts = new String[] { "ROLE_user", "user"};
            user = new MongoUserDetails( username, user.getPassword(), auts );
        }
        System.out.println( "Authorities");
        for( GrantedAuthority s : user.getAuthorities()) {
            System.out.println( " > authority " + s.getAuthority() + ", string = " + s.toString());
        }
        return user;
    }
}
//public class CustomerUserDetailsService implements UserDetailsService {
//    @Autowired
//    private MongoClient mongoClient;
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        MongoDatabase database = mongoClient.getDatabase("munzeefastermongodb");
//        MongoCollection<Document> collection = database.getCollection("mongosecurity");
//        Document document = collection.find(Filters.eq("email",email)).first();
//        if(document!=null) {
//            String name = document.getString("name");
//            String surname = document.getString("surname");
//            String password = document.getString("password");
//            List<String> authorities = (List<String>) document.get("authorities");
//            MongoUserDetails mongoUserDetails = new MongoUserDetails(email,password,authorities.toArray(new String[authorities.size()]));
//            return mongoUserDetails;
//        }
//        return null;
//    }
//}
