package com.app.res.agents;
import com.app.res.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface agentRepository extends JpaRepository<agentClass,Long> {
    Optional<agentClass>findAgentByEmail(String email);

    Optional<agentClass> findAgentByUserName(String userName);
}
