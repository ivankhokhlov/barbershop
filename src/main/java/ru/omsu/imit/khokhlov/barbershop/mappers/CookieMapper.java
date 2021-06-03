package ru.omsu.imit.khokhlov.barbershop.mappers;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import ru.omsu.imit.khokhlov.barbershop.model.user.Cookie;
import ru.omsu.imit.khokhlov.barbershop.model.user.User;

public interface CookieMapper {
    @Insert("INSERT INTO cookie (user_id,uuid) VALUES "
            +"(#{cookie.user.id},#{cookie.uuid})")
    Integer insert(@Param("cookie") Cookie cookie);

    @Select("SELECT * FROM cookie WHERE user_id = #{id}")
    @Results({@Result(property = "user", column = "user_id", javaType = User.class,
            one = @One(select = "ru.omsu.imit.khokhlov.barbershop.mappers.UserMapper.getById", fetchType = FetchType.LAZY)),
    })
    Cookie getById(@Param("id")int id);

    @Select("SELECT * FROM cookie WHERE uuid = #{uuid}")
    @Results({@Result(property = "user", column = "user_id", javaType = User.class,
            one = @One(select = "ru.omsu.imit.khokhlov.barbershop.mappers.UserMapper.getById", fetchType = FetchType.LAZY)),
    })
    Cookie getByUUID(@Param("uuid")String uuid);

    @Delete("DELETE FROM cookie WHERE uuid = #{uuid}")
    void deleteByUUID(@Param("uuid")String uuid);
    @Delete("DELETE FROM cookie WHERE user_id = #{id}")
    void deleteById(@Param("id")int id);
    @Delete("DELETE FROM cookie")
    void deleteAll();
}
