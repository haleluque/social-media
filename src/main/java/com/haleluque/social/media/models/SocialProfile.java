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
public class SocialProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "social_user")
    @JsonIgnore     //avoids circular references
    private SocialUser user;

    private String description;

    /**
     * Maintains the consistency of the relationship
     */
    public void setSocialUser(SocialUser socialUser){
        user = socialUser;
        if (user.getSocialProfile() != this)
           socialUser.setSocialProfile(this);
    }
}
