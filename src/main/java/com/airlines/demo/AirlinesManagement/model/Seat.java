package com.airlines.demo.AirlinesManagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatId;
    private int seatNUmber;
    private char row;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }

    public int getSeatNUmber() {
        return seatNUmber;
    }

    public void setSeatNUmber(int seatNUmber) {
        this.seatNUmber = seatNUmber;
    }

    public char getRow() {
        return row;
    }

    public void setRow(char row) {
        this.row = row;
    }
}
