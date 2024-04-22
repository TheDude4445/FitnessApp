package dmacc.beans;

import java.util.Collections;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Exercise> exercises;
    
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Nutrition> nutrition;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<WeightBMI> BMI;

    public Client() {
    }

    public Client(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.exercises = Collections.emptyList();
        this.nutrition = Collections.emptyList();;
    }

    public Client(String name, String email, String phoneNumber, List<Exercise> exercises, List<Nutrition> nutrition, List<WeightBMI> BMI) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.exercises = exercises;
        this.nutrition = nutrition;
        this.BMI = BMI;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public List<Nutrition> getNutrition() {
        return nutrition;
    }

    public void setNutrition(List<Nutrition> nutrition) {
        this.nutrition = nutrition;
    }

    public List<WeightBMI> getBMI() {
        return BMI;
    }

    public void setBMI(List<WeightBMI> BMI) {
        this.BMI = BMI;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", exercises=" + exercises +
                ", nutrition=" + nutrition +
                ", BMI=" + BMI +
                '}';
    }
}
