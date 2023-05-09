package io.mxm.testers.domains;

import io.mxm.testers.domains.types.tokenSubject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Identity implements UserDetails, tokenSubject {

    public Identity(String username, String password, Studnet studnet) {
        this.username = username;
        this.password = password;
        this.studnet = studnet;
        this.role =Studnet.class.getName();
        this.subject = UUID.randomUUID().toString();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_name", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "subject")
    private String subject;

    @Column(name = "status", nullable = false)
    private boolean isActive = true;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "studnet_id")
    private Studnet studnet;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    @Override
    public String getSubject() {
        return subject;
    }
}
