package dmacc.beans;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDate; // Import LocalDate from java.time package

@Data
@NoArgsConstructor
@Entity
public class WeightBMI {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double weight; // in kilograms (allowing null values)
    private Double height; // in meters (allowing null values)
    private Double bmi; // BMI can be null if weight or height is null or zero
    private LocalDate date; // Date variable
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    // Constructors
    public WeightBMI() {
        // Default constructor
    }

    public WeightBMI(Double weight, Double height, LocalDate date) {
        this.weight = weight;
        this.height = height;
        this.date = date;
        calculateBMI();
    }

    // Getters and Setters
    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
        calculateBMI();
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
        calculateBMI();
    }

    public Double getBMI() {
        return bmi;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    // Method to calculate BMI
    private void calculateBMI() {
        if (weight != null && height != null && height > 0) {
            this.bmi = weight / (height * height);
        } else {
            this.bmi = null; // Handle null weight, null height, or zero height
        }
    }
}