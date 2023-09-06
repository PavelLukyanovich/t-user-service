package ru.clevertec.userservice.role.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
import java.util.UUID;

@Table(name = "user_role")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "users")
@ToString(exclude = "users")
public class Role implements Serializable {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID roleId;
    @Column(name = "role_name")
    private String roleName;
    @OneToMany(mappedBy = "role",
            fetch = FetchType.LAZY,
            cascade = CascadeType.DETACH)
    private List<User> users;
}
