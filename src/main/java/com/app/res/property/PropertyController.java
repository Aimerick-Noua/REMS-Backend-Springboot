package com.app.res.property;

import com.app.res.image.ImageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class PropertyController {
    private final PropertyService propertyService;
    private final PropertyRepository propertyRepository;

    @Autowired
    @Lazy
    public PropertyController(PropertyService propertyService, PropertyRepository propertyRepository) {
        this.propertyService = propertyService;
        this.propertyRepository = propertyRepository;
    }

    @GetMapping(path = "/property")
    public List<propertyClass> getProperty() {
        return propertyService.getProperty();
    }
    @PostMapping(path="/property",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public propertyClass registerProperty(@RequestPart ("property") propertyClass property,
                                 @RequestPart ("imageFile") MultipartFile[] file){
        try {
            Set<ImageModel> images = uploadImage(file);
            property.setPropertyImages(images);
            return propertyService.addProperty(property);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    public Set<ImageModel> uploadImage(MultipartFile[] multipartFiles) throws IOException {
        Set<ImageModel> imageModels= new HashSet<>();
        for(MultipartFile file:multipartFiles){
            ImageModel imageModel=new ImageModel(
              file.getOriginalFilename(),
              file.getContentType(),
              file.getBytes()
            );
            imageModels.add(imageModel);
        }
        return imageModels;
    }
    @GetMapping(path = "property/{propertyId}")
    public ResponseEntity<propertyClass> getPropertyById(@PathVariable Long propertyId){
       propertyClass propertyClass = propertyRepository.findById(propertyId)
               .orElseThrow(()->new IllegalStateException("property not exist with id:"+propertyId));
       return ResponseEntity.ok(propertyClass);
    }

    @DeleteMapping(path = "/property/{propertyId}")
    public void deleteProperty(@PathVariable ("propertyId") Long propertyId){
        propertyService.deleteProperty(propertyId);
    }
    @PutMapping(path="/property/{propertyId}",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<propertyClass> updateProperty(@PathVariable Long propertyId,@RequestPart ("property") propertyClass new_agent,
                                                        @RequestPart ("imageFile") MultipartFile[] file ) {
        propertyClass user = propertyRepository.findById(propertyId).orElseThrow(()->new IllegalStateException("error while attempting to delete data"));
        user.setName(new_agent.getName());
        user.setOwner(new_agent.getOwner());
        user.setPrice(new_agent.getPrice());
        user.setLocation(new_agent.getLocation());
        user.setCategory(new_agent.getCategory());
        user.setNumRoom(new_agent.getNumRoom());
        user.setNumBath(new_agent.getNumBath());
        user.setNumLivingRoom(new_agent.getNumLivingRoom());
        user.setNumKitchen(new_agent.getNumKitchen());
        user.setArea(new_agent.getArea());
        user.setTerms(new_agent.getTerms());
        user.setNearbyPlaces(new_agent.getNearbyPlaces());
        user.setDescription(new_agent.getDescription());
        user.setStatus(new_agent.getStatus());
        user.setHousingAdvantages(new_agent.getHousingAdvantages());
        user.setRating(new_agent.getRating());
        user.setPropertyImages(new_agent.getPropertyImages());
        final propertyClass agent_new = propertyRepository.save(user);




        return ResponseEntity.ok(agent_new);


    }



}


