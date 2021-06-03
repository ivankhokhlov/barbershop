package ru.omsu.imit.khokhlov.barbershop.mappers;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import ru.omsu.imit.khokhlov.barbershop.model.user.master.Reservation;
import ru.omsu.imit.khokhlov.barbershop.model.user.master.Service;


public interface ServiceReservationMapper {
    @Insert("INSERT INTO service_reservation (reservation_id,service_id) VALUES "
            +"(#{reservation.id},#{service.id})")
    Integer insert(@Param("service") Service service, @Param("reservation") Reservation reservation);
    @Delete("DELETE FROM service_reservation WHERE reservation_id = #{reservation.id}")
    void deleteReservation(@Param("reservation") Reservation reservation);
    @Delete("DELETE FROM service_reservation WHERE service_id = #{service.id} AND reservation_id = #{reservation.id}")
    void deleteReservationFromService(@Param("service") Service service, @Param("reservation") Reservation reservation);
    @Delete("DELETE FROM service_reservation")
    void deleteAll();
}
