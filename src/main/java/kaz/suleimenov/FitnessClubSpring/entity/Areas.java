package kaz.suleimenov.FitnessClubSpring.entity;


import lombok.Data;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@EnableAutoConfiguration
@Table(name = "areas")
@Data
public class Areas implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "area", fetch = FetchType.LAZY, cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Activities> activities = new ArrayList<>();

    public Areas(String name) {
        this.name = name;
    }

    public Areas() {}

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Areas))
            return false;
        Areas areas = (Areas) o;
        return Objects.equals(this.id, areas.id) && Objects.equals(this.name, areas.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name);
    }

}
