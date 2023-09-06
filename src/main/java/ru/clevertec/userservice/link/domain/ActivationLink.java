package ru.clevertec.userservice.link.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Table(name = "links")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivationLink {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "link_id")
    private UUID linkId;
    @Column(name = "link")
    private String link;
}
