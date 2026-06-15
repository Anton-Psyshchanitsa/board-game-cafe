package com.boardgame.cafe.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString(exclude = "games")
@Entity
@Table(name = "game_categories")
public class GameCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Game> games;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameCategory)) return false;
        GameCategory that = (GameCategory) o;
        return name != null && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}