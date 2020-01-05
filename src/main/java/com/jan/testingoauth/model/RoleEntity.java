package com.jan.testingoauth.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "m_role")
public class RoleEntity {

    @Id
    @GenericGenerator(name = "gen", strategy="increment")
    @GeneratedValue(generator = "gen")
    @Column(name = "id")
    private int id;

    @Column(name = "role_name")
    private String roleName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
