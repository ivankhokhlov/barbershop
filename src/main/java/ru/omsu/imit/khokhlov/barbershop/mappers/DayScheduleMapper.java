package ru.omsu.imit.khokhlov.barbershop.mappers;



import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import ru.omsu.imit.khokhlov.barbershop.model.user.Master;
import ru.omsu.imit.khokhlov.barbershop.model.user.master.DaySchedule;


import java.time.LocalDate;
import java.util.List;

public interface DayScheduleMapper {
    @Insert("INSERT INTO dayschedule (curDate,master_id,timeStart,timeEnd ) VALUES "
            +"(#{dayschedule.curDate},#{dayschedule.master.user.id},#{dayschedule.timeStart},#{dayschedule.timeEnd})")
    @Options(useGeneratedKeys = true, keyProperty = "dayschedule.id")
    Integer insert(@Param("dayschedule") DaySchedule dayschedule);
    @Select("SELECT id,curDate,master_id,timeStart,timeEnd FROM dayschedule WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "master", column = "master_id", javaType = Master.class,
                    one = @One(select = "ru.omsu.imit.khokhlov.barbershop.mappers.MasterMapper.getById", fetchType = FetchType.LAZY)),
            @Result(property = "reservations", column = "id", javaType = List.class,
                    many = @Many(select = "ru.omsu.imit.khokhlov.barbershop.mappers.ReservationMapper.getByDaySchedule", fetchType = FetchType.LAZY)),
    })
    DaySchedule getById(@Param("id")int id);
    @Select("SELECT id,curDate,master_id,timeStart,timeEnd FROM dayschedule WHERE master_id = #{master.user.id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "master", column = "master_id", javaType = Master.class,
                    one = @One(select = "ru.omsu.imit.khokhlov.barbershop.mappers.MasterMapper.getById", fetchType = FetchType.LAZY)),
            @Result(property = "reservations", column = "id", javaType = List.class,
                    many = @Many(select = "ru.omsu.imit.khokhlov.barbershop.mappers.ReservationMapper.getByDaySchedule", fetchType = FetchType.LAZY)),
    })
    List<DaySchedule> getByMaster(@Param("master") Master master);
    @Select("SELECT id,curDate,master_id,timeStart,timeEnd FROM dayschedule WHERE master_id = #{master.user.id} AND curDate >= #{dateStart} AND curDate <= #{dateEnd}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "master", column = "master_id", javaType = Master.class,
                    one = @One(select = "ru.omsu.imit.khokhlov.barbershop.mappers.MasterMapper.getById", fetchType = FetchType.LAZY)),
            @Result(property = "reservations", column = "id", javaType = List.class,
                    many = @Many(select = "ru.omsu.imit.khokhlov.barbershop.mappers.ReservationMapper.getByDaySchedule", fetchType = FetchType.LAZY)),
    })
    List<DaySchedule> getByMasterAndDateStartDateEnd(@Param("master") Master master, @Param("dateStart")LocalDate dateStart, @Param("dateEnd")LocalDate dateEnd);
    @Select("SELECT id,curDate,master_id,timeStart,timeEnd FROM dayschedule WHERE master_id = #{master.user.id} AND curDate = #{date}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "master", column = "master_id", javaType = Master.class,
                    one = @One(select = "ru.omsu.imit.khokhlov.barbershop.mappers.MasterMapper.getById", fetchType = FetchType.LAZY)),
            @Result(property = "reservations", column = "id", javaType = List.class,
                    many = @Many(select = "ru.omsu.imit.khokhlov.barbershop.mappers.ReservationMapper.getByDaySchedule", fetchType = FetchType.LAZY)),
    })
    DaySchedule getByMasterAndDate(@Param("master") Master master, @Param("date")LocalDate date);
    @Select("SELECT id,curDate,master_id,timeStart,timeEnd FROM dayschedule WHERE curDate >= #{dateStart} AND curDate <= #{dateEnd}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "master", column = "master_id", javaType = Master.class,
                    one = @One(select = "ru.omsu.imit.khokhlov.barbershop.mappers.MasterMapper.getById", fetchType = FetchType.LAZY)),
            @Result(property = "reservations", column = "id", javaType = List.class,
                    many = @Many(select = "ru.omsu.imit.khokhlov.barbershop.mappers.ReservationMapper.getByDaySchedule", fetchType = FetchType.LAZY)),
    })
    List<DaySchedule> getByDate(@Param("dateStart")LocalDate dateStart,@Param("dateEnd")LocalDate dateEnd);
    @Delete("DELETE FROM dayschedule WHERE id =#{dayschedule.id}")
    void delete(@Param("dayschedule") DaySchedule dayschedule);
    @Delete("DELETE FROM dayschedule")
    void deleteAll();
}
