package ru.omsu.imit.khokhlov.barbershop.mappers;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import ru.omsu.imit.khokhlov.barbershop.model.user.Master;
import ru.omsu.imit.khokhlov.barbershop.model.user.master.Service;


public interface ServiceMasterMapper {
    @Insert("INSERT INTO service_master (master_id,service_id) VALUES "
            +"(#{master.user.id},#{service.id})")
    Integer insert(@Param("service") Service service, @Param("master") Master master);
    @Delete("DELETE FROM service_master WHERE service_id = #{service.id} AND master_id = #{master.user.id}")
    void deleteMasterFromService(@Param("service") Service service, @Param("master") Master master);
    @Delete("DELETE FROM service_master")
    void deleteAll();
}
