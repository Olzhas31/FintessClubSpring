package kaz.suleimenov.FitnessClubSpring.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Класс занятий.
 */
@Entity
@EnableAutoConfiguration
@Table(name = "activities")
public class Activities implements Serializable {

    /**
     * Id колонки (первичный ключ - значение которое будет использоваться для обеспечения уникальности данных в текущей таблице).
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Название занятия.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Тренер, ведущий это занятие.
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "trainer_id", referencedColumnName = "id", nullable = false)
    private Trainers trainer;

    /**
     * Зал, в котором ведется занятие.
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "area_id", referencedColumnName = "id", nullable = false)
    private Areas area;

    /**
     * Абонементы, которые есть с таким занятием.
     */
    @OneToMany(mappedBy = "activity", fetch = FetchType.LAZY, cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Subscriptions> subscriptions = new ArrayList<>();


    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param name - название
     * @param trainer - тренер
     * @param area - зал
     */
    public Activities(String name, Trainers trainer, Areas area) {
        this.name = name;
        this.trainer = trainer;
        this.area = area;
    }

    /**
     * Конструктор - создание нового объекта
     */
    Activities() {}

    /**
     * Функция получения значения поля {@link Activities#id}
     * @return возвращает id занятия
     */
    public Long getId() {
        return id;
    }

    /**
     * Процедура определения id {@link Activities#id}
     * @param id - id занятия
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Функция получения значения поля {@link Activities#name}
     * @return возвращает назвнание занятия
     */
    public String getName() {
        return name;
    }

    /**
     * Процедура определения названия {@link Activities#name}
     * @param name - название занятия
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Функция получения значения поля {@link Activities#trainer}
     * @return возвращает тренера, ведущего занятие
     */
    public Trainers getTrainer() {
        return trainer;
    }

    /**
     * Процедура определения тренера {@link Activities#trainer}
     * @param trainer - тренер занятия
     */
    public void setTrainer(Trainers trainer) {
        this.trainer = trainer;
    }

    /**
     * Функция получения значения поля {@link Activities#area}
     * @return возвращает зал, в котором проводится занятие
     */
    public Areas getArea() {
        return area;
    }

    /**
     * Процедура определения зала {@link Activities#area}
     * @param area - зал занятия
     */
    public void setArea(Areas area) {
        this.area = area;
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
        if (!(o instanceof Activities))
            return false;
        Activities activities = (Activities) o;
        return Objects.equals(this.id, activities.id) && Objects.equals(this.name, activities.name)
                && Objects.equals(this.trainer, activities.trainer) && Objects.equals(this.area, activities.area);
    }

    /**
     * Указывает, равен ли какой-либо другой объект этому.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.trainer, this.area);
    }

    /**
     * Вывод в строку
     * @return строка
     */
    @Override
    public String toString() {
        return "Employee{" + "id=" + this.id + ", name='" + this.name + '\'' +
                ", trainer_id='" + this.trainer + '\'' + ", area_id='" + this.area + '\'' + '}';
    }
}
