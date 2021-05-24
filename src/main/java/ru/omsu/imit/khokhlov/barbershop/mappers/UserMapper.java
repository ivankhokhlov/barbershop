package ru.omsu.imit.khokhlov.barbershop.mappers;

import org.apache.ibatis.annotations.*;
import ru.omsu.imit.khokhlov.barbershop.model.user.User;

public interface UserMapper {

        @Insert("INSERT INTO user (firstName,lastName,patronymic,login,password,user_type) VALUES "
                +"(#{user.firstName},#{user.lastName},#{user.patronymic},#{user.login},#{user.password}," +
                "#{user.userType})")

        @Options(useGeneratedKeys = true, keyProperty = "user.id")
        Integer insert(@Param("user")User user);

        @Select("SELECT id,firstName,lastName,patronymic,login,password,user_type as userType FROM user WHERE id = #{id}")
        User getById(@Param("id")int id);
        @Select("SELECT id,firstName,lastName,patronymic,login,password,user_type as userType FROM user WHERE login = #{login}")
        User getByLogin(@Param("login")String login);
        @Select("SELECT id,firstName,lastName,patronymic,login,password,user_type as userType FROM user WHERE login = #{login} AND password = #{password}")
        User getByLoginAndPassword(@Param("login")String login, @Param("password")String password);
        @Update("UPDATE user " +
                "SET firstName=#{user.firstName},lastName=#{user.lastName},patronymic=#{user.patronymic},password=#{user.password} " +
                "WHERE id =#{user.id}")
        void update(@Param("user") User user);
        @Delete("DELETE FROM user WHERE user_id=#{id} ")
        void deleteById(@Param("id")int id);
        @Delete("DELETE FROM user  where id not in (1)")
        void deleteAll();
}
