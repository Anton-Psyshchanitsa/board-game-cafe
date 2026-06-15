package com.boardgame.cafe.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@ToString(exclude = "user")
@Entity
@Table(name = "user_profiles")
public class UserProfile {

    @Id
    private Long userId;

    @Column(name = "bio")
    private String bio;

    @Column(name = "avatar_url", length = 500)
    private String avatarUrl;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserProfile)) return false;
        UserProfile that = (UserProfile) o;
        return userId != null && userId.equals(that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(userId);
    }
}