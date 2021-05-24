package ru.omsu.imit.khokhlov.barbershop.daoimpl;

import org.apache.ibatis.session.SqlSession;
import ru.omsu.imit.khokhlov.barbershop.mappers.*;
import ru.omsu.imit.khokhlov.barbershop.utils.MyBatisUtils;

public class BaseDaoImpl {

    protected SqlSession getSession() {
        return MyBatisUtils.getSqlSessionFactory().openSession();
    }

    protected UserMapper getUserMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(UserMapper.class);
    }

    protected AdminMapper getAdminMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(AdminMapper.class);
    }
    protected ServiceMapper getServiceMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(ServiceMapper.class);
    }
    protected SpecializationMapper getSpecializationMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(SpecializationMapper.class);
    }
    protected ClientMapper getClientMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(ClientMapper.class);
    }
    protected DayScheduleMapper getDayScheduleMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(DayScheduleMapper.class);
    }
    protected MasterMapper getMasterMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(MasterMapper.class);
    }
    protected ReservationMapper getReservationMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(ReservationMapper.class);
    }
    protected CookieMapper getCookieMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(CookieMapper.class);
    }
       protected ServiceMasterMapper getServiceMasterMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(ServiceMasterMapper.class);
    }
    protected ServiceReservationMapper getServiceReservationMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(ServiceReservationMapper.class);
    }


}