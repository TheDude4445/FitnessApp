package dmacc.beans;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDate; // Import LocalDate from java.time package

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@NoArgsConstructor
@Entity
public class WeightBMI {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double weight; // in lbs
    private Double feet; // in feet
    private Double inches; // in inches
    private LocalDate date; // Date variable
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    // Constructors
    public WeightBMI() {
        // Default constructor
    }

    public WeightBMI(Double weight, Double feet, Double inches, LocalDate date) {
        this.weight = weight;
        this.feet = feet;
        this.inches = inches;
        this.date = date;
    }

    // Getters and Setters
    
    public Long getId() {
        return id;
    }
    
    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;       
    }

    public Double getFeet() {
        return feet;
    }

    public void setFeet(Double feet) {
        this.feet = feet;   
    }

    public Double getInches() {
        return inches;
    }

    public void setInches(Double inches) {
        this.inches = inches;   
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public Client getClient() {
        return this.client;
    }
    
    public void setClient(Client client) {
        this.client = client;
    }
}