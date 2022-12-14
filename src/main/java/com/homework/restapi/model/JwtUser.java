package com.homework.restapi.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@EqualsAndHashCode(of = "uuid")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class JwtUser implements  UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder.Default
    private String uuid = UUID.randomUUID().toString();

    @OneToMany(
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "user_id")
    private List<Message> messages = new ArrayList<>();

    @Column
    private String username;

    @Column(unique = true)
    private String email;

    @Column
    private String password;


    @Column
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> role = new HashSet<>();

    @Column
    @Builder.Default
    private boolean enabled = false;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (var r : this.role) {
            var sga = new SimpleGrantedAuthority(r.name());
            authorities.add(sga);
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public void addMessage(Message message){
        messages.add(message);
    }

    public void removeMessage(Message message){
        messages.remove(message);
    }



}
