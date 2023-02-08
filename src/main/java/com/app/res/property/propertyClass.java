package com.app.res.property;

import com.app.res.image.ImageModel;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Table
public class propertyClass {
    @Id
    @SequenceGenerator(
            name = "property_sequence",
            sequenceName = "property_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "property_sequence"
    )
    private Long id;
    private String owner;
    public String name;
    public Integer price;
    public String location;
    public String category;
    public Integer numRoom;
    public Integer numBath;
    public Integer numLivingRoom;
    public Integer numKitchen;
    public Integer area;
    public String terms;
    public String nearbyPlaces;
    public String last_update;
    @Column(length = 1000)
    public String description;
    public String status;
    public String HousingAdvantages;
    public Integer rating;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="property_images",
    joinColumns = {
             @JoinColumn (name = "id")
    },
            inverseJoinColumns = {
            @JoinColumn(name = "image_id ")
            }
    )
    private Set<ImageModel> propertyImages;


    public propertyClass() {
    }

    public propertyClass(String owner, String name, Integer price, String location, String category, Integer numRoom, Integer numBath, Integer numLivingRoom, Integer numKitchen, Integer area, String terms, String nearbyPlaces, String last_update, String description, String status, String housingAdvantages, Integer rating) {
        this.owner = owner;
        this.name = name;
        this.price = price;
        this.location = location;
        this.category = category;
        this.numRoom = numRoom;
        this.numBath = numBath;
        this.numLivingRoom = numLivingRoom;
        this.numKitchen = numKitchen;
        this.area = area;
        this.terms = terms;
        this.nearbyPlaces = nearbyPlaces;
        this.last_update = last_update;
        this.description = description;
        this.status = status;
        this.HousingAdvantages = housingAdvantages;
        this.rating = rating;
    }

    public Set<ImageModel> getPropertyImages() {
        return propertyImages;
    }

    public void setPropertyImages(Set<ImageModel> propertyImages) {
        this.propertyImages = propertyImages;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getNumRoom() {
        return numRoom;
    }

    public void setNumRoom(Integer numRoom) {
        this.numRoom = numRoom;
    }

    public Integer getNumBath() {
        return numBath;
    }

    public void setNumBath(Integer numBath) {
        this.numBath = numBath;
    }

    public Integer getNumLivingRoom() {
        return numLivingRoom;
    }

    public void setNumLivingRoom(Integer numLivingRoom) {
        this.numLivingRoom = numLivingRoom;
    }

    public Integer getNumKitchen() {
        return numKitchen;
    }

    public void setNumKitchen(Integer numKitchen) {
        this.numKitchen = numKitchen;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public String getNearbyPlaces() {
        return nearbyPlaces;
    }

    public void setNearbyPlaces(String nearbyPlaces) {
        this.nearbyPlaces = nearbyPlaces;
    }

    public String getLast_update() {
        return last_update;
    }

    public void setLast_update(Date last_update) {
        this.last_update = String.valueOf(LocalDate.now());
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHousingAdvantages() {
        return HousingAdvantages;
    }

    public void setHousingAdvantages(String housingAdvantages) {
        HousingAdvantages = housingAdvantages;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }


    @Override
    public String toString() {
        return "propertyClass{" +
                "id=" + id +
                ", owner='" + owner + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", location='" + location + '\'' +
                ", category='" + category + '\'' +
                ", numRoom=" + numRoom +
                ", numBath=" + numBath +
                ", numLivingRoom=" + numLivingRoom +
                ", numKitchen=" + numKitchen +
                ", area=" + area +
                ", terms='" + terms + '\'' +
                ", nearbyPlaces='" + nearbyPlaces + '\'' +
                ", last_update='" + last_update + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", HousingAdvantages='" + HousingAdvantages + '\'' +
                ", rating='" + rating + '\'' +
                ", propertyImages=" + propertyImages +
                '}';
    }
}
