package com.cybersoft.hotel_booking.repository;

import com.cybersoft.hotel_booking.DTO.BookingDTO;
import com.cybersoft.hotel_booking.entity.BookingEntity;
import com.cybersoft.hotel_booking.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity,Integer> {
    List<BookingEntity> findByUsers(UsersEntity usersEntity);
    List<BookingEntity> findByIdAndUsers(int id, UsersEntity usersEntity);

    @Query(value = "select b.*, r.room_name as room_name, " +
            "h.hotel_name as hotel_name, h.address as hotel_address, " +
            "h.email as hotel_email, h.phone as hotel_phone " +
            "from room r , hotel h, booking b,booking_room br " +
            "where b.id=br.booking_id and  r.hotel_id=h.id " +
            "and br.room_id=r.id and br.chosen_status=1 and b.users_id= (:id)", nativeQuery = true)
    List<BookingDTO> findBookingByUsersId(int id);

    @Query(value = "select b.*, r.room_name as room_name, " +
            "h.hotel_name as hotel_name, h.address as hotel_address, " +
            "h.email as hotel_email, h.phone as hotel_phone " +
            "from room r , hotel h, booking b,booking_room br " +
            "where b.id=br.booking_id and  r.hotel_id=h.id " +
            "and br.room_id=r.id and br.chosen_status=1 and b.users_id= (:id) and h.id=(:hotelId)", nativeQuery = true)
    List<BookingDTO> findBookingByUsersIdAndHotelId(int id, int hotelId);

    @Query(value = "select b.*, r.room_name as room_name, " +
            "h.hotel_name as hotel_name, h.address as hotel_address, " +
            "h.email as hotel_email, h.phone as hotel_phone " +
            "from room r , hotel h, booking b,booking_room br " +
            "where b.id=br.booking_id and  r.hotel_id=h.id " +
            "and br.room_id=r.id and br.chosen_status=1 and b.users_id= (:userId) and b.id= (:id)", nativeQuery = true)
    List<BookingDTO> findBookingDetailByIDAndUserId(int id, int userId);
}
