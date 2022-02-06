package com.study.jpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_user")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue
    @Column(length = 32)
    private Long id;

    @Column(length = 64)
    private String username;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<Order> orderList;

    public User(String userId){
        this.setId(Long.valueOf(userId));
    }

    public User() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) && username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }
}
