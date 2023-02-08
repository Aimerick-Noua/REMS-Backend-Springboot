package com.app.res.property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PropertyService {
  
    private final PropertyService propertyService;
    @Autowired
    private final PropertyRepository propertyRepository;

    public PropertyService(@Lazy PropertyService propertyService, PropertyRepository propertyRepository) {
        this.propertyService = propertyService;
        this.propertyRepository = propertyRepository;
    }

    public List<propertyClass> getProperty() {
        return propertyRepository.findAll();
    }

    public propertyClass addProperty(propertyClass property) {
        Optional<propertyClass> findPropertyById = propertyRepository.findAgentById(property.getId());
        return propertyRepository.save(property);
    }

    public void deleteProperty(Long propertyId) {
        boolean exist=propertyRepository.existsById(propertyId);
        if(!exist){
            throw new IllegalStateException("id does not exist");
        }
        propertyRepository.deleteById(propertyId);

    }


}
