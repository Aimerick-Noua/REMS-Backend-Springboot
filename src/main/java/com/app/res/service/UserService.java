package com.app.res.service;

import com.app.res.dao.RoleDao;
import com.app.res.dao.UserDao;
import com.app.res.entity.Role;
import com.app.res.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleDao roleDao;
    public User registerNewUser(User user){
        Role role = roleDao.findById("User").get();
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRole(roles);
        Optional<User> findAgentByEmail = userDao.findAgentByUserName(user.getUserName());
        if(findAgentByEmail.isPresent()){
            throw new IllegalStateException("Username already taken");
        }
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));
      return userDao.save(user);
    }
    public void initRoleAndUser(){
        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleDao.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("User role");
        roleDao.save(userRole);

        Role agentRole = new Role();
        agentRole.setRoleName("Agent");
        agentRole.setRoleDescription("Agent role");
        roleDao.save(userRole);


        User adminUser = new User();
        adminUser.setUserFirstName("admin");
        adminUser.setUserLastName("admin");
        adminUser.setUserName("admin123");
        adminUser.setUserEmail("admin@gmail.com");
        adminUser.setUserPassword(getEncodedPassword("00000"));
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userDao.save(adminUser);

        User agent = new User();
        agent.setUserFirstName("agent");
        agent.setUserLastName("agent");
        agent.setUserName("agent237");
        agent.setUserEmail("admin@gmail.com");
        agent.setUserPassword(getEncodedPassword("00000"));
        Set<Role> agentRoles = new HashSet<>();
        agentRoles.add(agentRole);
        agent.setRole(agentRoles);
        userDao.save(agent);

    }
    public String getEncodedPassword(String password){
        return passwordEncoder.encode(password);
    }

    public List<User> getUser() {
        return (List<User>) userDao.findAll();
    }

    public void deleteAgent(String agentId) {
    }
}
