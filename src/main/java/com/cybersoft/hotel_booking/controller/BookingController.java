package com.cybersoft.hotel_booking.controller;

import com.cybersoft.hotel_booking.DTO.BookingDTO;
import com.cybersoft.hotel_booking.entity.BookingEntity;
import com.cybersoft.hotel_booking.entity.BookingRoomEntity;
import com.cybersoft.hotel_booking.entity.UsersEntity;
import com.cybersoft.hotel_booking.jwt.JwtTokenFilter;
import com.cybersoft.hotel_booking.jwt.JwtTokenHelper;
import com.cybersoft.hotel_booking.model.BookingModel;
import com.cybersoft.hotel_booking.payload.response.DataResponse;
import com.cybersoft.hotel_booking.repository.BookingRepository;
import com.cybersoft.hotel_booking.repository.UsersRepository;
import com.cybersoft.hotel_booking.service.BookingService;
import com.cybersoft.hotel_booking.service.UsersService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    JwtTokenHelper jwtTokenHelper;
    @Autowired
    JwtTokenFilter jwtTokenFilter;
    @Autowired
    private BookingService bookingService;

    @Autowired
    private UsersRepository usersRepository;


    @GetMapping("")
    public ResponseEntity<?> findBookingByUserId() {
        DataResponse dataResponse = new DataResponse();
        Gson gson = new Gson();
        String token = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization").substring(7);
        String json = jwtTokenHelper.decodeToken(token);
        Map map = gson.fromJson(json, Map.class);
        String email = map.get("username").toString();
        UsersEntity user = usersRepository.findByEmail(email).get(0);
        List<BookingDTO> listBooking = bookingService.findAllBookingByUsersId(user.getId());
        if (listBooking.size() == 0) {
            dataResponse.setStatus(HttpStatus.OK.value());//200
            dataResponse.setDesc(HttpStatus.OK.getReasonPhrase());//OK
            dataResponse.setSuccess(true);
            dataResponse.setData("Is Empty");
        } else {
            dataResponse.setStatus(HttpStatus.OK.value());//200
            dataResponse.setDesc(HttpStatus.OK.getReasonPhrase());//OK
            dataResponse.setSuccess(true);
            dataResponse.setData(listBooking);
        }

        return ResponseEntity.ok(dataResponse);

    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<?> findBookingByUserIdAndHotelId(@PathVariable("hotelId") Integer id) {
        DataResponse dataResponse = new DataResponse();
        Gson gson = new Gson();
        String token = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization").substring(7);
        String json = jwtTokenHelper.decodeToken(token);
        Map map = gson.fromJson(json, Map.class);
        String email = map.get("username").toString();
        UsersEntity user = usersRepository.findByEmail(email).get(0);
        List<BookingDTO> listBooking = bookingService.findAllBookingByUsersIdAndHotelId(user.getId(), id);
        if (listBooking.isEmpty()) {
            dataResponse.setStatus(HttpStatus.OK.value());//200
            dataResponse.setDesc(HttpStatus.OK.getReasonPhrase());//OK
            dataResponse.setSuccess(true);
            dataResponse.setData("Is Empty");
        } else {
            dataResponse.setStatus(HttpStatus.OK.value());//200
            dataResponse.setDesc(HttpStatus.OK.getReasonPhrase());//OK
            dataResponse.setSuccess(true);
            dataResponse.setData(listBooking);
        }

        return ResponseEntity.ok(dataResponse);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getBookingDetail(@PathVariable("id") Integer id) {
        DataResponse dataResponse = new DataResponse();
        Gson gson = new Gson();
        String token = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization").substring(7);
        String json = jwtTokenHelper.decodeToken(token);
        Map map = gson.fromJson(json, Map.class);
        String email = map.get("username").toString();
        UsersEntity user = usersRepository.findByEmail(email).get(0);
        BookingDTO bookingDetail =  bookingService.findBookingDetailByIDAndUserId(id,user.getId());
        if (bookingDetail==null){
            dataResponse.setStatus(HttpStatus.OK.value());//200
            dataResponse.setDesc(HttpStatus.OK.getReasonPhrase());//OK
            dataResponse.setSuccess(true);
            dataResponse.setData("Is empty");
        } else {
            dataResponse.setStatus(HttpStatus.OK.value());//200
            dataResponse.setDesc(HttpStatus.OK.getReasonPhrase());//OK
            dataResponse.setSuccess(true);
            dataResponse.setData(bookingDetail);
        }
        return ResponseEntity.ok(dataResponse);

    }
}
