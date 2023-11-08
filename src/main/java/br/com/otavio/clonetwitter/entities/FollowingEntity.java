package br.com.otavio.clonetwitter.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity(name = "following")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FollowingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "user_following")
    private UserEntity userFollowingEntity;
}
