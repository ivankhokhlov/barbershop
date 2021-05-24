package ru.omsu.imit.khokhlov.barbershop.mappers;


import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import ru.omsu.imit.khokhlov.barbershop.model.user.Client;
import ru.omsu.imit.khokhlov.barbershop.model.user.Master;
import ru.omsu.imit.khokhlov.barbershop.model.user.master.DaySchedule;
import ru.omsu.imit.khokhlov.barbershop.model.user.master.Reservation;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ReservationMapper {
    @Insert("INSERT INTO reservation (timeStart,timeEnd,ticket,client_id,daySchedule_id) VALUES "
            +"(#{reservation.timeStart},#{reservation.timeEnd},#{reservation.ticket},#{reservation.client.user.id},#{reservation.daySchedule.id})")
    @Options(useGeneratedKeys = true, keyProperty = "reservation.id")
    Integer insert(@Param("reservation") Reservation reservation);


    @Select("SELECT id,timeStart,timeEnd,ticket,client_id,daySchedule_id FROM reservation WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "client", column = "client_id", javaType = Client.class,
                    one = @One(select = "ru.omsu.imit.khokhlov.barbershop.mappers.ClientMapper.getById", fetchType = FetchType.LAZY)),
            @Result(property = "daySchedule", column = "daySchedule_id", javaType = DaySchedule.class,
                    one = @One(select = "ru.omsu.imit.khokhlov.barbershop.mappers.DayScheduleMapper.getById", fetchType = FetchType.LAZY)),
            @Result(property = "services", column = "id", javaType = List.class,
                    many = @Many(select = "ru.omsu.imit.khokhlov.barbershop.mappers.ServiceMapper.getByReservation", fetchType = FetchType.LAZY)),
    })
    Reservation getById(@Param("id")int id);
    @Select("SELECT id,timeStart,timeEnd,ticket,client_id,daySchedule_id FROM reservation WHERE daySchedule_id = #{daySchedule.id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "client", column = "client_id", javaType = Client.class,
                    one = @One(select = "ru.omsu.imit.khokhlov.barbershop.mappers.ClientMapper.getById", fetchType = FetchType.LAZY)),
            @Result(property = "daySchedule", column = "daySchedule_id", javaType = DaySchedule.class,
                    one = @One(select = "ru.omsu.imit.khokhlov.barbershop.mappers.DayScheduleMapper.getById", fetchType = FetchType.LAZY)),
            @Result(property = "services", column = "id", javaType = List.class,
                    many = @Many(select = "ru.omsu.imit.khokhlov.barbershop.mappers.ServiceMapper.getByReservation", fetchType = FetchType.LAZY)),
    })
    Reservation getByDaySchedule(@Param("daySchedule")DaySchedule daySchedule);
    @Select("SELECT id,timeStart,timeEnd,ticket,client_id,daySchedule_id FROM reservation WHERE ticket = #{ticket}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "client", column = "client_id", javaType = Client.class,
                    one = @One(select = "ru.omsu.imit.khokhlov.barbershop.mappers.ClientMapper.getById", fetchType = FetchType.LAZY)),
            @Result(property = "daySchedule", column = "daySchedule_id", javaType = DaySchedule.class,
                    one = @One(select = "ru.omsu.imit.khokhlov.barbershop.mappers.DayScheduleMapper.getById", fetchType = FetchType.LAZY)),
            @Result(property = "services", column = "id", javaType = List.class,
                    many = @Many(select = "ru.omsu.imit.khokhlov.barbershop.mappers.ServiceMapper.getByReservation", fetchType = FetchType.LAZY)),
    })
    Reservation getByTicket(@Param("ticket")String ticket);
    @Update("UPDATE reservation " +
            "SET client_id = #{reservation.client.user.id} " +
            "WHERE id = #{reservation.id}")
    void updatePatient(@Param("reservation") Reservation reservation);

    @Select("SELECT id,timeStart,timeEnd,ticket,client_id,daySchedule_id FROM reservation WHERE timeStart = #{time} " +
            "AND dayschedule_id = (SELECT id FROM dayschedule WHERE curDate = #{date} " +
            "AND master_id = #{master.user.id});")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "client", column = "client_id", javaType = Client.class,
                    one = @One(select = "ru.omsu.imit.khokhlov.barbershop.mappers.ClientMapper.getById", fetchType = FetchType.LAZY)),
            @Result(property = "daySchedule", column = "daySchedule_id", javaType = DaySchedule.class,
                    one = @One(select = "ru.omsu.imit.khokhlov.barbershop.mappers.DayScheduleMapper.getById", fetchType = FetchType.LAZY)),
            @Result(property = "services", column = "id", javaType = List.class,
                    many = @Many(select = "ru.omsu.imit.khokhlov.barbershop.mappers.ServiceMapper.getByReservation", fetchType = FetchType.LAZY)),
    })
    Reservation getByDateTimeAndMaster(@Param("date")LocalDate date, @Param("time") LocalTime time, @Param("master") Master master);
    @Select("SELECT id,timeStart,timeEnd,ticket,client_id,daySchedule_id  FROM reservation WHERE timeStart >= #{timeStart} AND timeStart <= #{timeEnd} " +
            "AND dayschedule_id = (SELECT id FROM dayschedule WHERE curDate = #{date} " +
            "AND master_id = #{master.user.id});")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "client", column = "client_id", javaType = Client.class,
                    one = @One(select = "ru.omsu.imit.khokhlov.barbershop.mappers.ClientMapper.getById", fetchType = FetchType.LAZY)),
            @Result(property = "daySchedule", column = "daySchedule_id", javaType = DaySchedule.class,
                    one = @One(select = "ru.omsu.imit.khokhlov.barbershop.mappers.DayScheduleMapper.getById", fetchType = FetchType.LAZY)),
            @Result(property = "services", column = "id", javaType = List.class,
                    many = @Many(select = "ru.omsu.imit.khokhlov.barbershop.mappers.ServiceMapper.getByReservation", fetchType = FetchType.LAZY)),
    })
    List<Reservation> getByDateTimeStartTimeEndAndDoctorId(@Param("date")LocalDate date, @Param("timeStart") LocalTime timeStart,
                                                           @Param("timeEnd") LocalTime timeEnd, @Param("master") Master master);
    @Delete("DELETE FROM reservation")
    void deleteAll();
}
