package com.haleluque.social.media.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Many post associated to one user
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore     //avoids circular references
    private SocialUser socialUser;
}
