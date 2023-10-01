package br.com.otavio.clonetwitter.entities;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity(name = "publication")
@Getter
@Setter
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
    private Date create_at;

    @ManyToOne
    @JoinColumn(name = "user_publication")
    private UserEntity user;

    @OneToMany(mappedBy = "publication", fetch = FetchType.LAZY)
    private List<LikeEntity> likes;

    @OneToMany(mappedBy = "publication", fetch = FetchType.LAZY)
    private List<ShareEntity> shares;
}
