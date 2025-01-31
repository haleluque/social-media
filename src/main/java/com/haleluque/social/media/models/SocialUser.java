package com.haleluque.social.media.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocialUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Bidirectional relationship, owned by SocialProfile
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private SocialProfile socialProfile;

    //Bidirectional relationship, owned by Post
    @OneToMany(mappedBy = "socialUser")
    private List<Post> posts = new ArrayList<>();

    //Bidirectional relationship, owned by SocialUser
    //@ManyToMany(fetch = FetchType.EAGER) //lazy by default
    @ManyToMany
    @JoinTable(//join table
            name = "user_group",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private Set<SocialGroup> groups = new HashSet<>();

    //Avoids stackoverflow exception
    @Override
    public int hashCode(){
        return Objects.hash(id);
    }


    /**
     * maintaining the consistency on both sides of the bidirectional relationship
     * when a social profile is added with a new user, it will create the relationship with the user
     */
    public void setSocialProfile(SocialProfile socialProfile) {
        socialProfile.setUser(this);
        this.socialProfile = socialProfile;
    }
}
