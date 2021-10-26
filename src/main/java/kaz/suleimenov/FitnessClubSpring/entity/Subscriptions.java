package kaz.suleimenov.FitnessClubSpring.entity;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.util.Objects;


/**
 * Класс абонементов.
 */
@Entity
@EnableAutoConfiguration
@Table(name = "subscriprions")
public class Subscriptions {
    /**
     * Id колонки (первичный ключ - значение которое будет использоваться для обеспечения уникальности данных в текущей таблице).
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Клиент.
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="client_id", nullable=false)
    private Clients client;

    /**
     * Занятие.
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "activity_id", referencedColumnName = "id", nullable = false)
    private Activities activity;

    /**
     * Срок.
     */
    @Column(name = "date", nullable = false)
    private String date;

    /**
     * Цена.
     */
    @Column(name = "price", nullable = false)
    private String price;


    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param client - клиент
     * @param activity - занятие
     * @param date - срок
     * @param price - цена
     */
    public Subscriptions(Clients client, Activities activity, String date, String price) {
        this.client = client;
        this.activity = activity;
        this.date = date;
        this.price = price;
    }

    /**
     * Конструктор - создание нового объекта
     */
    Subscriptions() {}

    /**
     * Функция получения значения поля {@link Subscriptions#id}
     * @return возвращает id абонемента
     */
    public Long getId() {
        return id;
    }

    /**
     * Процедура определения id {@link Subscriptions#id}
     * @param id - id абонемента
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Функция получения значения поля {@link Subscriptions#client}
     * @return возвращает клиента
     */
    public Clients getClient() {
        return client;
    }

    /**
     * Процедура определения клинта {@link Subscriptions#client}
     * @param client - клиент
     */
    public void setClient(Clients client) {
        this.client = client;
    }

    /**
     * Функция получения значения поля {@link Subscriptions#activity}
     * @return возвращает занятие
     */
    public Activities getActivity() {
        return activity;
    }

    /**
     * Процедура определения занятия {@link Subscriptions#activity}
     * @param activity - занятие
     */
    public void setActivity(Activities activity) {
        this.activity = activity;
    }

    /**
     * Функция получения значения поля {@link Subscriptions#date}
     * @return возвращает срок
     */
    public String getDate() {
        return date;
    }

    /**
     * Процедура определения id {@link Subscriptions#date}
     * @param date - срок
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Функция получения значения поля {@link Subscriptions#price}
     * @return возвращает цену
     */
    public String getPrice() {
        return price;
    }

    /**
     * Процедура определения цены {@link Subscriptions#price}
     * @param price - цена
     */
    public void setPrice(String price) {
        this.price = price;
    }


    /**
     * Метод для точного определения объекта
     * @param o сам объект
     * @return равны ли параметры объекта
     */
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

    /**
     * Указывает, равен ли какой-либо другой объект этому.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.client, this.activity, this.date, this.price);
    }

    /**
     * Вывод в строку
     * @return строка
     */
    @Override
    public String toString() {
        return "Employee{" + "id=" + this.id + ", client='" + this.client + '\'' + ", activity='" + this.activity + '\'' +
                ", date='" + this.date + ", price='" + '}';
    }
}
