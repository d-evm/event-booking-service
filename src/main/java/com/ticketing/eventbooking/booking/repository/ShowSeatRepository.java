package com.ticketing.eventbooking.booking.repository;

import com.ticketing.eventbooking.booking.model.ShowSeat;
import com.ticketing.eventbooking.booking.model.SeatStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import jakarta.persistence.LockModeType;
import java.util.List;
import java.util.UUID;

public interface ShowSeatRepository extends JpaRepository<ShowSeat, UUID> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
        select ss from ShowSeat ss
        where ss.show.id = :showId
        and ss.seat.id in :seatIds
        """)
    List<ShowSeat> findAndLockSeats(UUID showId, List<UUID> seatIds);

    List<ShowSeat> findByShowIdAndStatus(UUID showId, SeatStatus status);

    List<ShowSeat> findByShowId(UUID showId);
}
