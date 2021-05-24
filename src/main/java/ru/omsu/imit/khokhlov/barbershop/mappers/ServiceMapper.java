package ru.omsu.imit.khokhlov.barbershop.mappers;



import org.apache.ibatis.annotations.*;
import ru.omsu.imit.khokhlov.barbershop.model.user.Master;
import ru.omsu.imit.khokhlov.barbershop.model.user.master.Reservation;
import ru.omsu.imit.khokhlov.barbershop.model.user.master.Service;

import java.util.List;

public interface ServiceMapper {
    @Insert("INSERT INTO service (name,price) VALUES "
            +"(#{service.name},#{service.price})")
    @Options(useGeneratedKeys = true, keyProperty = "service.id")
    Integer insert(@Param("service") Service service);
    @Select("SELECT id,name,price FROM service WHERE id = #{id}")
    Service getById(@Param("id")int id);
    @Select("SELECT id,name,price FROM service WHERE id IN (SELECT service_id " +
            "FROM service_master WHERE master_id = #{master.user.id})")
    List<Service> getByMaster(@Param("master") Master master);
    @Select("SELECT id,name,price FROM service WHERE id IN (SELECT service_id " +
            "FROM service_reservation WHERE reservation_id = #{reservation.id})")
    List<Service> getByReservation(@Param("reservation") Reservation reservation);
    @Select("SELECT id,name,price FROM service WHERE name = #{name}")
    Service getByName(@Param("name")String name);
    @Delete("DELETE FROM service")
    void deleteAll();
}