package kaz.suleimenov.FitnessClubSpring.entity;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Класс тренеров.
 */
@Entity 
@EnableAutoConfiguration
@Table(name = "trainers") 
public class Trainers {

    /**
     * Id колонки (первичный ключ - значение которое будет использоваться для обеспечения уникальности данных в текущей таблице).
     */
    @Id 
    @Column(name = "id") 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    /**
     * ФИО тренера.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Паспорт тренера.
     */
    @Column(name = "passport", nullable = false, unique = true)
    private String passport;

    /**
     * Телефон тренера.
     */
    @Column(name = "phone", nullable = false)
    private String phone;

    /**
     * Адрес тренера.
     */
    @Column(name = "address", nullable = false)
    private String address;

    /**
     * Занятия тренера
     */
    @OneToMany(mappedBy = "trainer", fetch = FetchType.LAZY)
    private List<Activities> activities = new ArrayList<>();

    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param name - ФИО
     * @param passport - паспорт
     * @param phone - телефон
     * @param address - адрес
     */
    public Trainers(String name, String passport, String phone, String address) {
        this.name = name;
        this.passport = passport;
        this.phone = phone;
        this.address = address;

    }

    /**
     * Конструктор - создание нового объекта
     */
    Trainers() {}

    /**
     * Функция получения значения поля {@link Trainers#id}
     * @return возвращает id тренера
     */
    public Long getId() {
        return id;
    }

    /**
     * Процедура определения id {@link Trainers#id}
     * @param id - id тренера
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Функция получения значения поля {@link Trainers#name}
     * @return возвращает ФИО тренера
     */
    public String getName() {
        return name;
    }

    /**
     * Процедура определения ФИО {@link Trainers#name}
     * @param name - ФИО тренера
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Функция получения значения поля {@link Trainers#passport}
     * @return возвращает паспорт тренера
     */
    public String getPassport() {
        return passport;
    }

    /**
     * Процедура определения паспорта {@link Trainers#passport}
     * @param passport - паспорт тренера
     */
    public void setPassport(String passport) {
        this.passport = passport;
    }

    /**
     * Функция получения значения поля {@link Trainers#phone}
     * @return возвращает телефон тренера
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Процедура определения телефона {@link Trainers#phone}
     * @param phone - телефон тренера
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Функция получения значения поля {@link Trainers#address}
     * @return возвращает телефон тренера
     */
    public String getAddress() {
        return address;
    }

    /**
     * Процедура определения адреса {@link Trainers#address}
     * @param address - адрес тренера
     */
    public void setAddress(String address) {
        this.address = address;
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
        if (!(o instanceof Trainers))
            return false;
        Trainers trainers = (Trainers) o;
        return Objects.equals(this.id, trainers.id) && Objects.equals(this.name, trainers.name)
                && Objects.equals(this.passport, trainers.passport) && Objects.equals(this.phone, trainers.phone)
                && Objects.equals(this.address, trainers.address);
    }

    /**
     * Указывает, равен ли какой-либо другой объект этому.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.passport, this.phone, this.address);
    }

    /**
     * Вывод в строку
     * @return строка
     */
    @Override
    public String toString() {
        return "Employee{" + "id=" + this.id + ", name='" + this.name + '\'' + ", passport='" + this.passport + '\'' +
                ", phone='" + this.phone + ", address='" + this.address + '}';
    }
}
