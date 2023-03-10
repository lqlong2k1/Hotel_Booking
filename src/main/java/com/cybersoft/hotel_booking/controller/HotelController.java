package com.cybersoft.hotel_booking.controller;

import com.cybersoft.hotel_booking.entity.HotelEntity;
import com.cybersoft.hotel_booking.payload.response.DataResponse;
import com.cybersoft.hotel_booking.repository.HotelRepository;
import com.cybersoft.hotel_booking.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @Autowired
    private HotelRepository hotelRepository;

    @PostMapping("/findall") //Still keep this for Mr. Dai
    public List<HotelEntity> findall() {
        return hotelRepository.findAll();
    }

    //CRUD
    @PostMapping("")
    public ResponseEntity<?> addHotel(@RequestBody HotelEntity hotelEntity, BindingResult bindingResult) {
        DataResponse dataResponse = new DataResponse();

        if (bindingResult.hasErrors()) {//BAD REQUEST
            dataResponse.setStatus(HttpStatus.BAD_REQUEST.value());//400
            dataResponse.setDesc(HttpStatus.BAD_REQUEST.getReasonPhrase());//BAD REQUEST
            dataResponse.setSuccess(false);
            dataResponse.setData("");

            return ResponseEntity.ok(dataResponse);
        }

        HotelEntity hotelEntityAdded = hotelService.addHotel(hotelEntity);

        dataResponse.setStatus(HttpStatus.CREATED.value());//201
        dataResponse.setDesc(HttpStatus.CREATED.getReasonPhrase());//CREATED
        dataResponse.setSuccess(true);
        dataResponse.setData(hotelEntityAdded);

        return ResponseEntity.ok(dataResponse);
    }

    @GetMapping("")
    public ResponseEntity<?> findAllHotel() {
        DataResponse dataResponse = new DataResponse();

        List<HotelEntity> hotelEntityList = hotelService.findAllHotel();

        if (hotelEntityList.isEmpty()) {//NO CONTENT
            dataResponse.setStatus(HttpStatus.NO_CONTENT.value());//204
            dataResponse.setDesc(HttpStatus.NO_CONTENT.getReasonPhrase());//NO CONTENT
            dataResponse.setSuccess(false);
            dataResponse.setData("");

            return ResponseEntity.ok(dataResponse);
        }

        dataResponse.setStatus(HttpStatus.OK.value());//200
        dataResponse.setDesc(HttpStatus.OK.getReasonPhrase());//OK
        dataResponse.setSuccess(true);
        dataResponse.setData(hotelEntityList);

        return ResponseEntity.ok(dataResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findHotelById(@PathVariable("id") Integer id) {
        DataResponse dataResponse = new DataResponse();

        HotelEntity hotelEntity = hotelService.findHotelById(id);

        if (hotelEntity == null) {//NOT FOUND
            dataResponse.setStatus(HttpStatus.NOT_FOUND.value());//404
            dataResponse.setDesc(HttpStatus.NOT_FOUND.getReasonPhrase());//NOT FOUND
            dataResponse.setSuccess(false);
            dataResponse.setData("");

            return ResponseEntity.ok(dataResponse);
        }

        dataResponse.setStatus(HttpStatus.OK.value());//200
        dataResponse.setDesc(HttpStatus.OK.getReasonPhrase());//OK
        dataResponse.setSuccess(true);
        dataResponse.setData(hotelEntity);

        return ResponseEntity.ok(dataResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateHotel(@PathVariable("id") Integer id, @RequestBody HotelEntity hotelEntity, BindingResult bindingResult) {
        DataResponse dataResponse = new DataResponse();

        if (bindingResult.hasErrors()) {//BAD REQUEST
            dataResponse.setStatus(HttpStatus.BAD_REQUEST.value());//400
            dataResponse.setDesc(HttpStatus.BAD_REQUEST.getReasonPhrase());//BAD REQUEST
            dataResponse.setSuccess(false);
            dataResponse.setData("");

            return ResponseEntity.ok(dataResponse);
        }

        HotelEntity hotelEntityUpdated = hotelService.updateHotel(id, hotelEntity);

        if (hotelEntityUpdated == null) {//NOT FOUND
            dataResponse.setStatus(HttpStatus.NOT_FOUND.value());//404
            dataResponse.setDesc(HttpStatus.NOT_FOUND.getReasonPhrase());//NOT FOUND
            dataResponse.setSuccess(false);
            dataResponse.setData("");

            return ResponseEntity.ok(dataResponse);
        }

        dataResponse.setStatus(HttpStatus.OK.value());//200
        dataResponse.setDesc(HttpStatus.OK.getReasonPhrase());//OK
        dataResponse.setSuccess(true);
        dataResponse.setData(hotelEntityUpdated);

        return ResponseEntity.ok(dataResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHotelById(@PathVariable("id") Integer id) {
        DataResponse dataResponse = new DataResponse();

        boolean success = hotelService.deleteHotelById(id);

        if (!success) {//NOT FOUND
            dataResponse.setStatus(HttpStatus.NOT_FOUND.value());//404
            dataResponse.setDesc(HttpStatus.NOT_FOUND.getReasonPhrase());//NOT FOUND
            dataResponse.setSuccess(false);
            dataResponse.setData("");

            return ResponseEntity.ok(dataResponse);
        }

        dataResponse.setStatus(HttpStatus.OK.value());//200
        dataResponse.setDesc(HttpStatus.OK.getReasonPhrase());//OK
        dataResponse.setSuccess(true);
        dataResponse.setData("");

        return ResponseEntity.ok(dataResponse);
    }
}
