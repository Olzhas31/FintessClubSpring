package kaz.suleimenov.FitnessClubSpring.entity;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.util.Objects;

/**
 * Класс аккаунтов для приложения.
 */
@Entity
@Table(name = "user_table")
@EnableAutoConfiguration
public class User {

    /**
     * Id колонки (первичный ключ - значение которое будет использоваться для обеспечения уникальности данных в текущей таблице).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Логин.
     */
    @Column
    private String login;

    /**
     * Пароль.
     */
    @Column
    private String password;

    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param login - логин
     * @param password - пароль
     */
    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    /**
     * Конструктор - создание нового объекта
     */
    User() {}

    /**
     * Функция получения значения поля {@link User#id}
     * @return возвращает id пользователя
     */
    public Long getId() {
        return id;
    }

    /**
     * Процедура определения id {@link User#id}
     * @param id - id пользователя
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Функция получения значения поля {@link User#login}
     * @return возвращает логин пользователя
     */
    public String getLogin() {
        return login;
    }

    /**
     * Процедура определения логина {@link User#login}
     * @param login - логин пользователя
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Функция получения значения поля {@link User#password}
     * @return возвращает пароль пользователя (он будет захэширован)
     */
    public String getPassword() {
        return password;
    }

    /**
     * Процедура определения и хэширования пароля {@link User#password}
     * @param password - захэшированный пароль пользователя
     */
    public void setPassword(String password) {
        this.password = DigestUtils.shaHex(password);
    }

    /**
     * Вывод в строку
     * @return строка
     */
    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof User))
            return false;
        User user = (User) o;
        return Objects.equals(this.id, user.id) && Objects.equals(this.login, user.login)
                && Objects.equals(this.password, user.password);
    }

    /**
     * Указывает, равен ли какой-либо другой объект этому.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.login, this.password);
    }
}
