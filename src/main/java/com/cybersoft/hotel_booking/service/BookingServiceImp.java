package com.cybersoft.hotel_booking.service;

import com.cybersoft.hotel_booking.DTO.BookingDTO;
import com.cybersoft.hotel_booking.entity.BookingEntity;
import com.cybersoft.hotel_booking.entity.BookingRoomEntity;
import com.cybersoft.hotel_booking.entity.UsersEntity;
import com.cybersoft.hotel_booking.repository.BookingRepository;
import com.cybersoft.hotel_booking.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImp implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private UsersRepository usersRepository;

//    @Override
//    public List<BookingEntity> findAllBookingByUsers(UsersEntity usersEntity) {
//        return bookingRepository.findByUsers(usersEntity).size() > 0 ?
//                bookingRepository.findByUsers(usersEntity) : null;
//    }

    @Override
    public List<BookingDTO> findAllBookingByUsersId(int id) {
        return bookingRepository.findBookingByUsersId(id);
    }

    @Override
    public List<BookingDTO> findAllBookingByUsersIdAndHotelId(int id, int hotelId) {
        return bookingRepository.findBookingByUsersIdAndHotelId(id, hotelId);
    }

    @Override
    public BookingDTO findBookingDetailByIDAndUserId(int id, int userId){
        return bookingRepository.findBookingDetailByIDAndUserId(id,userId).size() > 0 ?
                bookingRepository.findBookingDetailByIDAndUserId(id,userId).get(0) : null;
    }
}
