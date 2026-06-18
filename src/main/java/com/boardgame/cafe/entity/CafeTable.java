package com.boardgame.cafe.entity;

import jakarta.persistence.*;
        import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString(exclude = "bookings")
@Entity
@Table(name = "tables")
//переименован класс из Table в CafeTable из-за конфликта с @Table
public class CafeTable extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "table_number", nullable = false, unique = true, length = 10)
    private String tableNumber;

    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    @Column(name = "location", length = 100)
    private String location;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @OneToMany(mappedBy = "table", fetch = FetchType.LAZY)
    private List<Booking> bookings;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CafeTable)) return false;
        CafeTable cafeTable = (CafeTable) o;
        return tableNumber != null && tableNumber.equals(cafeTable.tableNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(tableNumber);
    }
}