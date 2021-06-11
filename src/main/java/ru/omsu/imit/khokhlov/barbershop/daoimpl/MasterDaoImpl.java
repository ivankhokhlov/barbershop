package ru.omsu.imit.khokhlov.barbershop.daoimpl;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.omsu.imit.khokhlov.barbershop.dao.MasterDao;
import ru.omsu.imit.khokhlov.barbershop.model.user.Master;
import ru.omsu.imit.khokhlov.barbershop.model.user.master.Reservation;
import ru.omsu.imit.khokhlov.barbershop.model.user.master.Service;
import ru.omsu.imit.khokhlov.barbershop.model.user.master.Specialization;

import java.util.List;

@Component
public class MasterDaoImpl extends BaseDaoImpl implements MasterDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(MasterDaoImpl.class);

    @Override
    public Master insert(Master master) {
        LOGGER.debug("DAO insert master {}", master);
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).insert(master.getUser());
                getMasterMapper(sqlSession).insert(master);
                for (Service service : master.getService()) {
                    getServiceMasterMapper(sqlSession).insert(service, master);
                }
            } catch (RuntimeException ex) {
                LOGGER.info("Can't insert master {} {}", master, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return master;
    }

    @Override
    public Master update(Master master) {
        LOGGER.debug("DAO update master {}", master);
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).update(master.getUser());
                getMasterMapper(sqlSession).update(master);
                master = getMasterMapper(sqlSession).getById(master.getUser().getId());
            } catch (RuntimeException ex) {
                LOGGER.info("Can't update master {} {}", master, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return master;
    }


    @Override
    public Master getById(int id) {
        LOGGER.debug("DAO get master by Id {}", id);
        try (SqlSession sqlSession = getSession()) {
            return getMasterMapper(sqlSession).getById(id);
        } catch (RuntimeException ex) {
            LOGGER.info("Can't get master ", ex);
            throw ex;
        }
    }

    @Override
    public List<Master> getAllMaster() {
        LOGGER.debug("DAO get all masters ");
        try (SqlSession sqlSession = getSession()) {
            return getMasterMapper(sqlSession).getAllMaster();
        } catch (RuntimeException ex) {
            LOGGER.info("Can't get  all masters ", ex);
            throw ex;
        }
    }

    @Override
    public Master addServices(Master master, List<Service> services) {
        LOGGER.debug("DAO addServices master,services {},{}", master, services);
        try (SqlSession sqlSession = getSession()) {
            try {
                for (Service service : services) {
                    getServiceMasterMapper(sqlSession).insert(service, master);
                    master = getMasterMapper(sqlSession).getById(master.getUser().getId());
                }
            } catch (RuntimeException ex) {
                LOGGER.info("Can't addServices master {} {}", master, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return master;
    }

    @Override
    public Master deleteServices(Master master, List<Service> services) {
        LOGGER.debug("DAO deleteServices master,services {},{}", master, services);
        try (SqlSession sqlSession = getSession()) {
            try {
                for (Service service : services) {
                    getServiceMasterMapper(sqlSession).deleteMasterFromService(service, master);
                }
                master = getMasterMapper(sqlSession).getById(master.getUser().getId());
            } catch (RuntimeException ex) {
                LOGGER.info("Can't deleteServices master {} {}", master, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return master;
    }

    @Override
    public List<Master> getBySpecialization(Specialization specialization) {
        LOGGER.debug("DAO getBySpecialization master by specialization {}", specialization);
        try (SqlSession sqlSession = getSession()) {
            return getMasterMapper(sqlSession).getBySpecialization(specialization);
        } catch (RuntimeException ex) {
            LOGGER.info("Can't getBySpecialization master by specialization{}", specialization, ex);
            throw ex;
        }
    }

    @Override
    public Master getByReservation(Reservation reservation) {
        LOGGER.debug("DAO getByReservation master by reservation {}", reservation);
        try (SqlSession sqlSession = getSession()) {
            return getMasterMapper(sqlSession).getByReservation(reservation);
        } catch (RuntimeException ex) {
            LOGGER.info("Can't getByReservation master {}", reservation, ex);
            throw ex;
        }
    }

    @Override
    public List<Integer> getByService(Service service) {
        LOGGER.debug("DAO getByReservation masterIds by service {}", service);
        try (SqlSession sqlSession = getSession()) {
            return getMasterMapper(sqlSession).getByService(service);
        } catch (RuntimeException ex) {
            LOGGER.info("Can't getByReservation masterIds by service {}", service, ex);
            throw ex;
        }
    }

    @Override
    public void deleteAll() {
        LOGGER.debug("DAO Delete All master");
        try (SqlSession sqlSession = getSession()) {
            try {
                getMasterMapper(sqlSession).deleteAll();
            } catch (RuntimeException ex) {
                LOGGER.info("Can't delete all master", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }
}
