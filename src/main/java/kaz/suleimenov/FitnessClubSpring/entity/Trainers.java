package kaz.suleimenov.FitnessClubSpring.entity;

import lombok.Data;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity 
@EnableAutoConfiguration
@Table(name = "trainers")
@Data
public class Trainers {

    @Id 
    @Column(name = "id") 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "passport", nullable = false, unique = true)
    private String passport;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "address", nullable = false)
    private String address;

    @OneToMany(mappedBy = "trainer", fetch = FetchType.LAZY)
    private List<Activities> activities = new ArrayList<>();

    public Trainers() {}

    public Trainers(String name, String passport, String phone, String address) {
        this.name = name;
        this.passport = passport;
        this.phone = phone;
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Trainers))
            return false;
        Trainers trainers = (Trainers) o;
        return Objects.equals(this.id, trainers.id) && Objects.equals(this.name, trainers.name)
                && Objects.equals(this.passport, trainers.passport) && Objects.equals(this.phone, trainers.phone)
                && Objects.equals(this.address, trainers.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.passport, this.phone, this.address);
    }

}
