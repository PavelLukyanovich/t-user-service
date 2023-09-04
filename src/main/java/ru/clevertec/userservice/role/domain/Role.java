package ru.clevertec.userservice.role.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.clevertec.userservice.user.domain.User;

import java.io.Serializable;
import java.util.List;

@Table(name = "user_role")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "user")
public class Role implements Serializable {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private java.util.UUID roleId;
    @Enumerated(EnumType.STRING)
    @Column(name = "role_name")
    private UserRole roleName;
    @Enumerated(EnumType.STRING)
    @Column(name = "role_type")
    private RoleType roleType;
    @OneToMany(mappedBy = "role",
            fetch = FetchType.LAZY,
            cascade = CascadeType.DETACH)
    @JsonIgnore
    @ToString.Exclude
    private List<User> user;
}
