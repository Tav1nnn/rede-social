package br.com.otavio.clonetwitter.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "follower")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_follower")
    private UserEntity userEntity;
}
