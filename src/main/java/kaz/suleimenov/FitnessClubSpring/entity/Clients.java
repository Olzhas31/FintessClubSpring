package kaz.suleimenov.FitnessClubSpring.entity;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Класс клиентов.
 */
@Entity
@EnableAutoConfiguration
@Table(name = "clients")
public class Clients {
    /**
     * Id колонки (первичный ключ - значение которое будет использоваться для обеспечения уникальности данных в текущей таблице).
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * ФИО клиента.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Паспорт клиента.
     */
    @Column(name = "passport", nullable = false, unique = true)
    private String passport;

    /**
     * Телефон клиента.
     */
    @Column(name = "phone", nullable = false)
    private String phone;

    /**
     * Абонементы клиента
     */
    @OneToMany(mappedBy="client")
    private List<Subscriptions> subscriptions = new ArrayList<>();

    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param name - ФИО
     * @param passport - паспорт
     * @param phone - телефон
     */
    public Clients(String name, String passport, String phone) {
        this.name = name;
        this.passport = passport;
        this.phone = phone;
    }

    /**
     * Конструктор - создание нового объекта
     */
    Clients() {}

    /**
     * Функция получения значения поля {@link Clients#id}
     * @return возвращает id клиента
     */
    public Long getId() {
        return id;
    }

    /**
     * Процедура определения id {@link Clients#id}
     * @param id - id клиента
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Функция получения значения поля {@link Clients#name}
     * @return возвращает ФИО клиента
     */
    public String getName() {
        return name;
    }

    /**
     * Процедура определения ФИО {@link Clients#name}
     * @param name - ФИО клиента
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Функция получения значения поля {@link Clients#passport}
     * @return возвращает паспорт клиента
     */
    public String getPassport() {
        return passport;
    }

    /**
     * Процедура определения паспорта {@link Clients#passport}
     * @param passport - паспорт клиента
     */
    public void setPassport(String passport) {
        this.passport = passport;
    }

    /**
     * Функция получения значения поля {@link Clients#phone}
     * @return возвращает телефон клиента
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Процедура определения телефона {@link Clients#phone}
     * @param phone - телефон клиента
     */
    public void setPhone(String phone) {
        this.phone = phone;
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
        if (!(o instanceof Clients))
            return false;
        Clients clients = (Clients) o;
        return Objects.equals(this.id, clients.id) && Objects.equals(this.name, clients.name)
                && Objects.equals(this.passport, clients.passport) && Objects.equals(this.phone, clients.phone);
    }

    /**
     * Указывает, равен ли какой-либо другой объект этому.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.passport, this.phone);
    }

    /**
     * Вывод в строку
     * @return строка
     */
    @Override
    public String toString() {
        return "Employee{" + "id=" + this.id + ", name='" + this.name + '\'' + ", passport='" + this.passport + '\'' +
                ", phone='" + this.phone + '}';
    }
}
