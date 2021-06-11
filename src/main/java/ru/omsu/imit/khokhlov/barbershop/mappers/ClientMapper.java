package ru.omsu.imit.khokhlov.barbershop.mappers;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import ru.omsu.imit.khokhlov.barbershop.model.user.Client;
import ru.omsu.imit.khokhlov.barbershop.model.user.User;

public interface ClientMapper {
    @Insert("INSERT INTO client (user_id,email,address,phone) VALUES "
            +"(#{client.user.id},#{client.email},#{client.address},#{client.phone})")

    Integer insert(@Param("client") Client client);

    @Select("SELECT * FROM client WHERE user_id = #{id}")
    @Results({@Result(property = "user", column = "user_id", javaType = User.class,
            one = @One(select = "ru.omsu.imit.khokhlov.barbershop.mappers.UserMapper.getById", fetchType = FetchType.LAZY)),
    })
    Client getById(@Param("id")int id);
    @Update("UPDATE client " +
            "SET email =#{client.email},address=#{client.address},phone =#{client.phone} " +
            "WHERE  user_id =#{client.user.id}")
    void update(@Param("client") Client client);

    @Delete("DELETE FROM client")
    void deleteAll();
}
