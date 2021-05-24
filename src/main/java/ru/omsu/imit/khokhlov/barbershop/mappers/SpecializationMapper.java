package ru.omsu.imit.khokhlov.barbershop.mappers;

import org.apache.ibatis.annotations.*;
import ru.omsu.imit.khokhlov.barbershop.model.user.Master;
import ru.omsu.imit.khokhlov.barbershop.model.user.master.Specialization;

public interface SpecializationMapper {
    @Insert("INSERT INTO specialization (name) VALUES "
            +"(#{specialization.name})")
    @Options(useGeneratedKeys = true, keyProperty = "specialization.id")
    Integer insert(@Param("specialization") Specialization specialization);
    @Select("SELECT id,name FROM specialization WHERE id = #{id}")
    Specialization getById(@Param("id")int id);
    @Select("SELECT id,name FROM specialization WHERE name = #{name}")
    Specialization getByName(@Param("name")String name);
    @Select("SELECT id,name FROM specialization WHERE id = #{master.specialization.id}")
    Specialization getByMaster(Master master);
    @Delete("DELETE FROM specialization")
    void deleteAll();
}
