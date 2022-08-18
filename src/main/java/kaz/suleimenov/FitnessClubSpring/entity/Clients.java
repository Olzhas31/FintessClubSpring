package kaz.suleimenov.FitnessClubSpring.entity;

import lombok.Data;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@EnableAutoConfiguration
@Table(name = "clients")
@Data
public class Clients {

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

    @OneToMany(mappedBy="client")
    private List<Subscriptions> subscriptions = new ArrayList<>();

    public Clients() {}

    public Clients(String name, String passport, String phone) {
        this.name = name;
        this.passport = passport;
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Clients))
            return false;
        Clients clients = (Clients) o;
        return Objects.equals(this.id, clients.id) && Objects.equals(this.name, clients.name)
                && Objects.equals(this.passport, clients.passport) && Objects.equals(this.phone, clients.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.passport, this.phone);
    }
}
