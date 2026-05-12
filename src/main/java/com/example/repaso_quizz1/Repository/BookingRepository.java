package com.example.repaso_quizz1.Repository;

import com.example.repaso_quizz1.Model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
