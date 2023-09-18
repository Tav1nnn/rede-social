package br.com.otavio.clonetwitter.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity(name = "publication")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublicationEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "caption", nullable = false)
    private String caption;

    @Column(name = "create_at", nullable = false)
    private String create_at;

    @ManyToOne
    @JoinColumn(name = "user_publication")
    private UserEntity user;

    @OneToMany(mappedBy = "publication")
    private List<LikeEntity> likes;

    @OneToMany(mappedBy = "publication")
    private List<ShareEntity> shares;
}
