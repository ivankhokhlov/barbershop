package ru.omsu.imit.khokhlov.barbershop.mappers;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import ru.omsu.imit.khokhlov.barbershop.model.user.Master;
import ru.omsu.imit.khokhlov.barbershop.model.user.User;
import ru.omsu.imit.khokhlov.barbershop.model.user.master.Reservation;
import ru.omsu.imit.khokhlov.barbershop.model.user.master.Service;
import ru.omsu.imit.khokhlov.barbershop.model.user.master.Specialization;

import java.util.List;

public interface MasterMapper {
    @Insert("INSERT INTO master (user_id,specialization_id) VALUES "
            + "(#{master.user.id},#{master.specialization.id})")
    Integer insert(@Param("master") Master master);

    @Update("UPDATE master " +
            "SET specialization_id=#{master.specialization.id} " +
            "WHERE  user_id =#{master.user.id}")
    void update(@Param("master") Master master);

    @Select("SELECT user_id ,specialization_id FROM master WHERE user_id = #{id}")
    @Results({
            @Result(property = "user.id", column = "user_id"),
            @Result(property = "user", column = "user_id", javaType = User.class,
                    one = @One(select = "ru.omsu.imit.khokhlov.barbershop.mappers.UserMapper.getById", fetchType = FetchType.LAZY)),
            @Result(property = "service", column = "user_id", javaType = List.class,
                    many = @Many(select = "ru.omsu.imit.khokhlov.barbershop.mappers.ServiceMapper.getByMaster", fetchType = FetchType.LAZY)),
            @Result(property = "specialization", column = "specialization_id", javaType = Specialization.class,
                    one = @One(select = "ru.omsu.imit.khokhlov.barbershop.mappers.SpecializationMapper.getById", fetchType = FetchType.LAZY)),
            @Result(property = "daySchedulesList", column = "user_id", javaType = List.class,
                    many = @Many(select = "ru.omsu.imit.khokhlov.barbershop.mappers.DayScheduleMapper.getByMaster", fetchType = FetchType.LAZY)),
    })
    Master getById(@Param("id") int id);

    @Select("SELECT master_id " +
            "FROM service_master WHERE service_id = #{service.id}")
    List<Integer> getByService(@Param("service") Service service);

    @Select("SELECT user_id ,specialization_id FROM master")
    @Results({
            @Result(property = "user.id", column = "user_id"),
            @Result(property = "user", column = "user_id", javaType = User.class,
                    one = @One(select = "ru.omsu.imit.khokhlov.barbershop.mappers.UserMapper.getById", fetchType = FetchType.LAZY)),
            @Result(property = "service", column = "user_id", javaType = List.class,
                    many = @Many(select = "ru.omsu.imit.khokhlov.barbershop.mappers.ServiceMapper.getByMaster", fetchType = FetchType.LAZY)),
            @Result(property = "specialization", column = "specialization_id", javaType = Specialization.class,
                    one = @One(select = "ru.omsu.imit.khokhlov.barbershop.mappers.SpecializationMapper.getById", fetchType = FetchType.LAZY)),
            @Result(property = "daySchedulesList", column = "user_id", javaType = List.class,
                    many = @Many(select = "ru.omsu.imit.khokhlov.barbershop.mappers.DayScheduleMapper.getByMaster", fetchType = FetchType.LAZY)),
    })
    List<Master> getAllMaster();
    @Select("SELECT user_id ,specialization_id FROM master WHERE specialization_id = #{specialization.id}")
    @Results({
            @Result(property = "user.id", column = "user_id"),
            @Result(property = "user", column = "user_id", javaType = User.class,
                    one = @One(select = "ru.omsu.imit.khokhlov.barbershop.mappers.UserMapper.getById", fetchType = FetchType.LAZY)),
            @Result(property = "service", column = "user_id", javaType = Service.class,
                    one = @One(select = "ru.omsu.imit.khokhlov.barbershop.mappers.ServiceMapper.getByMaster", fetchType = FetchType.LAZY)),
            @Result(property = "specialization", column = "specialization_id", javaType = Specialization.class,
                    one = @One(select = "ru.omsu.imit.khokhlov.barbershop.mappers.SpecializationMapper.getById", fetchType = FetchType.LAZY)),
            @Result(property = "daySchedulesList", column = "user_id", javaType = List.class,
                    many = @Many(select = "ru.omsu.imit.khokhlov.barbershop.mappers.DayScheduleMapper.getByMaster", fetchType = FetchType.LAZY)),
    })
    List<Master> getBySpecialization(@Param("specialization") Specialization specialization);

    @Select("SELECT user_id ,specialization_id FROM master WHERE user_id = " +
            " (SELECT master_id FROM daySchedules WHERE id = " +
            "(SELECT dayschedule_id FROM reservation WHERE id = #{reservation.id})")
    @Results({
            @Result(property = "user.id", column = "user_id"),
            @Result(property = "user", column = "user_id", javaType = User.class,
                    one = @One(select = "ru.omsu.imit.khokhlov.barbershop.mappers.UserMapper.getById", fetchType = FetchType.LAZY)),
            @Result(property = "service", column = "service_id", javaType = Service.class,
                    one = @One(select = "ru.omsu.imit.khokhlov.barbershop.mappers.ServiceMapper.getById", fetchType = FetchType.LAZY)),
            @Result(property = "specialization", column = "specialization_id", javaType = Specialization.class,
                    one = @One(select = "ru.omsu.imit.khokhlov.barbershop.mappers.SpecializationMapper.getById", fetchType = FetchType.LAZY)),
            @Result(property = "daySchedulesList", column = "user_id", javaType = List.class,
                    many = @Many(select = "ru.omsu.imit.khokhlov.barbershop.mappers.DayScheduleMapper.getByDoctor", fetchType = FetchType.LAZY)),
    })
    Master getByReservation(@Param("Reservation") Reservation reservation);
    @Delete("DELETE FROM master")
    void deleteAll();
}
