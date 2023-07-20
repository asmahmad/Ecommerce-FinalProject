//package com.ecommerce.config;
//
//import java.util.Collection;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.ecommerce.domain.Admin;
//import com.ecommerce.domain.Customer;
//import com.ecommerce.domain.Role;
//import com.ecommerce.repository.AdminRepository;
//import com.ecommerce.repository.CustomerRepository;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private AdminRepository adminRepository;
//
//    @Autowired
//    private CustomerRepository customerRepository;
//
//    
//    public UserDetails loadUserByUsernameAdmin(String username) throws UsernameNotFoundException {
//        Admin admin = adminRepository.findByUsername(username);
//        if(admin ==null){
//            throw new UsernameNotFoundException("Could not find username");
//        }
//        return new User(
//                admin.getUsername(),
//                admin.getPassword(),
//                admin.getRoles().stream().map(role-> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList()));
//        }
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Customer customer = customerRepository.findByUsername(username);
//        if(customer == null){
//            throw new UsernameNotFoundException("Could not find username");
//        }
//        return new User(customer.getUsername(),
//                customer.getPassword(),
//                customer.getRoles()
//                        .stream()
//                        .map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList()));
//    }
//}
//
