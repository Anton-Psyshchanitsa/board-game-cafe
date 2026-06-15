package com.boardgame.cafe.entity;

import com.boardgame.cafe.enums.GameCondition;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString(exclude = {"game", "bookingGames"})
@Entity
@Table(name = "game_instances")
public class GameInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "inventory_number", nullable = false, unique = true, length = 50)
    private String inventoryNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "condition", nullable = false, length = 20)
    private GameCondition condition;

    @Column(name = "purchase_date")
    private LocalDate purchaseDate;

    @Column(name = "is_available", nullable = false)
    private Boolean isAvailable;

    @Column(name = "notes")
    private String notes;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @OneToMany(mappedBy = "gameInstance", fetch = FetchType.LAZY)
    private List<BookingGame> bookingGames;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameInstance)) return false;
        GameInstance that = (GameInstance) o;
        return inventoryNumber != null && inventoryNumber.equals(that.inventoryNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(inventoryNumber);
    }
}