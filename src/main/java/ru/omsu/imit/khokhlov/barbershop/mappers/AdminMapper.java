package ru.omsu.imit.khokhlov.barbershop.mappers;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import ru.omsu.imit.khokhlov.barbershop.model.user.Admin;
import ru.omsu.imit.khokhlov.barbershop.model.user.User;

public interface AdminMapper {
    @Insert("INSERT INTO admin (user_id,position) VALUES "
            + "(#{admin.user.id},#{admin.position})")
    Integer insert(@Param("admin") Admin admin);

    @Select("SELECT * FROM admin WHERE user_id = #{id}")
    @Results({@Result(property = "user", column = "user_id", javaType = User.class,
            one = @One(select = "ru.omsu.imit.khokhlov.barbershop.mappers.UserMapper.getById", fetchType = FetchType.LAZY)),
    })
    Admin getById(int id);

    @Update("UPDATE admin " +
            "SET position=#{admin.position} " +
            "WHERE  user_id =#{admin.user.id}")
    void update(@Param("admin") Admin admin);

    @Delete("DELETE FROM admin where user_id not in (1)")
    void deleteAll();
}
