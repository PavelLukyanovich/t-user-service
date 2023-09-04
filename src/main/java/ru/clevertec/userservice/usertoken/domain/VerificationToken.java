package ru.clevertec.userservice.usertoken.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.clevertec.userservice.user.domain.User;

import java.time.LocalDateTime;


@Table(name = "verificationtoken")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "user")
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private java.util.UUID id;
    @Column(name = "token")
    private String token;
    @OneToOne
    private User user;
    @Column(name = "expire_date")
    private LocalDateTime expiryDate;

}
