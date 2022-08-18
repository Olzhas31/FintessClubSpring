package kaz.suleimenov.FitnessClubSpring.entity;

import lombok.Data;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.util.Objects;

@Entity
@EnableAutoConfiguration
@Table(name = "subscriprions")
@Data
public class Subscriptions {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="client_id", nullable=false)
    private Clients client;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "activity_id", referencedColumnName = "id", nullable = false)
    private Activities activity;

    @Column(name = "date", nullable = false)
    private String date;

    @Column(name = "price", nullable = false)
    private String price;

    public Subscriptions() {}

    public Subscriptions(Clients client, Activities activity, String date, String price) {
        this.client = client;
        this.activity = activity;
        this.date = date;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Subscriptions))
            return false;
        Subscriptions subscriptions = (Subscriptions) o;
        return Objects.equals(this.id, subscriptions.id) && Objects.equals(this.client, subscriptions.client)
                && Objects.equals(this.activity, subscriptions.activity)
                && Objects.equals(this.date, subscriptions.date)
                && Objects.equals(this.price, subscriptions.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.client, this.activity, this.date, this.price);
    }

}
