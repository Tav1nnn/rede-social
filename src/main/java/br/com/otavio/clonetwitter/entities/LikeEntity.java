package br.com.otavio.clonetwitter.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import java.io.Serializable;

@Entity(name = "like")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikeEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "create_at", nullable = false)
    private String create_at;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "publication_like")
    private PublicationEntity publication;
}
