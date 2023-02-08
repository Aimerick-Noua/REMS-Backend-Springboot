package com.app.res.agents;

import com.app.res.dao.RoleDao;
import com.app.res.dao.UserDao;
import com.app.res.entity.Role;
import com.app.res.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class agentService {
    @Autowired
    private agentRepository agentDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private final agentRepository agentrepository;
    public agentService(agentRepository agentRepository) {
        this.agentrepository = agentRepository;
    }
        public List<agentClass> getAgents() {
            return agentrepository.findAll();
        }


    public agentClass addNewAgent(agentClass agentclass) {
        Optional<agentClass> findAgentByEmail = agentrepository.findAgentByEmail(agentclass.getEmail());
        if(findAgentByEmail.isPresent()){
            throw new IllegalStateException("Email already taken");
        }
        Role role = roleDao.findById("Agent").get();
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        agentclass.setRole(roles);
        Optional<agentClass> findAgentByUserName = agentrepository.findAgentByUserName(agentclass.getUserName());
        if(findAgentByUserName.isPresent()){
            throw new IllegalStateException("Username already taken");
        }
        agentclass.setPassword(getEncodedPassword(agentclass.getPassword()));
            return agentrepository.save(agentclass);
    }

    public void deleteAgent(Long agentId) {
       boolean exist=agentrepository.existsById(agentId);
       if(!exist){
           throw new IllegalStateException("Email with id does not exist");
       }
       agentrepository.deleteById(agentId);

    }
    public void initRoleAndAgent() {
        Role agentRole = new Role();
        agentRole.setRoleName("Agent");
        agentRole.setRoleDescription("Agent role");
        roleDao.save(agentRole);


        agentClass user = new agentClass();
        user.setFirstName("agents");
        user.setLastName("agents");
        user.setUserName("agents123");
        user.setDob("1995 june 22");
        user.setNationality("Cameroonian");
        user.setMarital_status("married");
        user.setSex("male");
        user.setEmail("agent@gmail.com");
        user.setTelNum("674743317");
        user.setAddress("yaounde");
        user.setPassword(getEncodedPassword("00000"));
        Set<Role> AgentRoles = new HashSet<>();
        AgentRoles.add(agentRole);
        user.setRole(AgentRoles);
        agentDao.save(user);

    }
    public String getEncodedPassword(String password){
        return passwordEncoder.encode(password);
    }

    public List<agentClass> getUser() {
        return agentDao.findAll();
    }

    public void deleteAgent(String agentId) {
    }
}
