package com.app.res.property;

import com.app.res.agents.agentClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PropertyRepository extends JpaRepository<propertyClass,Long> {
    Optional<propertyClass> findAgentById(Long id);

}
