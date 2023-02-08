package com.app.res.dao;

import com.app.res.agents.agentClass;
import com.app.res.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends CrudRepository<User,String> {
    Optional<User> findAgentByUserName(String email);

}
