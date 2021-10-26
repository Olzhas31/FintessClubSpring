package kaz.suleimenov.FitnessClubSpring.entity;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Класс залов.
 */
@Entity
@EnableAutoConfiguration
@Table(name = "areas")
public class Areas implements Serializable {

    /**
     * Id колонки (первичный ключ - значение которое будет использоваться для обеспечения уникальности данных в текущей таблице).
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Название зала.
     */
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    /**
     * Занятия, которые проходят в определенном зале.
     */
    @OneToMany(mappedBy = "area", fetch = FetchType.LAZY, cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Activities> activities = new ArrayList<>();

    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param name - название
     */
    public Areas(String name) {
        this.name = name;
    }

    /**
     * Конструктор - создание нового объекта
     */
    public Areas() {}


    /**
     * Функция получения значения поля {@link Areas#id}
     * @return возвращает id зала
     */
    public Long getId() {
        return id;
    }

    /**
     * Процедура определения id {@link Areas#id}
     * @param id - id зала
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Функция получения значения поля {@link Areas#name}
     * @return возвращает назвнание зала
     */
    public String getName() {
        return name;
    }

    /**
     * Процедура определения названия {@link Areas#name}
     * @param name - название зала
     */
    public void setName(String name) {
        this.name = name;
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
        if (!(o instanceof Areas))
            return false;
        Areas areas = (Areas) o;
        return Objects.equals(this.id, areas.id) && Objects.equals(this.name, areas.name);
    }

    /**
     * Указывает, равен ли какой-либо другой объект этому.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name);
    }

    /**
     * Вывод в строку
     * @return строка
     */
    @Override
    public String toString() {
        return "Employee{" + "id=" + this.id + ", name='" + this.name + '\'' + '}';
    }
}
