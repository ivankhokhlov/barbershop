package ru.omsu.imit.khokhlov.barbershop.mappers;

import org.apache.ibatis.annotations.*;
import ru.omsu.imit.khokhlov.barbershop.model.user.Master;
import ru.omsu.imit.khokhlov.barbershop.model.user.master.Reservation;
import ru.omsu.imit.khokhlov.barbershop.model.user.master.Service;

import java.time.LocalDate;
import java.util.List;

public interface ServiceMapper {
    @Insert("INSERT INTO service (name,price,duration) VALUES "
            + "(#{service.name},#{service.price},#{service.duration})")
    @Options(useGeneratedKeys = true, keyProperty = "service.id")
    Integer insert(@Param("service") Service service);

    @Select("SELECT id,name,price,duration FROM service WHERE id = #{id}")
    Service getById(@Param("id") int id);

    @Select("SELECT id,name,price,duration FROM service")
    List<Service> getAll();

    @Select("SELECT id,name,price,duration FROM service WHERE id IN (SELECT service_id " +
            "FROM service_master WHERE master_id = #{master.user.id})")
    List<Service> getByMaster(@Param("master") Master master);

    @Select("SELECT id,name,price,duration FROM service WHERE id IN (SELECT service_id " +
            "FROM service_reservation WHERE reservation_id = #{reservation.id})")
    List<Service> getByReservation(@Param("reservation") Reservation reservation);

    @Select("SELECT id,name,price,duration FROM service WHERE name = #{name}")
    Service getByName(@Param("name") String name);

    @Select("SELECT sum(price) FROM service WHERE id IN (SELECT service_id " +
            "FROM service_reservation WHERE reservation_id IN (SELECT id FROM reservation WHERE dayschedule_id IN (" +
            "SELECT id FROM dayschedule WHERE master_id = #{master.user.id} AND curDate >= #{dateStart} " +
            "AND curDate <= #{dateEnd})))")
    Integer getRevenueByMasterAndDate(@Param("master") Master master, @Param("dateStart") LocalDate dateStart, @Param("dateEnd") LocalDate dateEnd);

    @Select("SELECT sum(price) FROM service WHERE id IN (SELECT service_id " +
            "FROM service_reservation WHERE reservation_id IN (SELECT id FROM reservation WHERE dayschedule_id IN (" +
            "SELECT id FROM dayschedule WHERE curDate >= #{dateStart} " +
            "AND curDate <= #{dateEnd})))")
    Integer getRevenueByDate(@Param("dateStart") LocalDate dateStart, @Param("dateEnd") LocalDate dateEnd);

    @Delete("DELETE FROM service WHERE id  = #{service.id}")
    void deleteService(@Param("service") Service service);

    @Delete("DELETE FROM service")
    void deleteAll();
}
