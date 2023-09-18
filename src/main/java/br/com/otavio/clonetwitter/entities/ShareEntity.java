package br.com.otavio.clonetwitter.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity(name = "share")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShareEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "create_at", nullable = false)
    private String create_at;

    @ManyToOne
    @JoinColumn(name = "publication_share")
    private PublicationEntity publication;
}
