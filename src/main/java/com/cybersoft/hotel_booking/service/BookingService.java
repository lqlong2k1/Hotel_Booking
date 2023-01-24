package com.cybersoft.hotel_booking.service;

import com.cybersoft.hotel_booking.DTO.BookingDTO;
import com.cybersoft.hotel_booking.entity.BookingEntity;
import com.cybersoft.hotel_booking.entity.BookingRoomEntity;
import com.cybersoft.hotel_booking.entity.UsersEntity;

import java.util.List;

public interface BookingService {

    //View all booking by userID
    public List<BookingDTO> findAllBookingByUsersId(int id);

    //View all booking by userID and hotelID
    public List<BookingDTO> findAllBookingByUsersIdAndHotelId(int id, int hotelId);

    public BookingDTO findBookingDetailByIDAndUserId(int id, int userId);
}

