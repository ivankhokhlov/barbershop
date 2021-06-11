package ru.omsu.imit.khokhlov.barbershop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.omsu.imit.khokhlov.barbershop.dto.request.ServiceRequest;
import ru.omsu.imit.khokhlov.barbershop.dto.request.ServicesRequest;
import ru.omsu.imit.khokhlov.barbershop.dto.response.MasterInfoWithoutScheduleResponse;
import ru.omsu.imit.khokhlov.barbershop.dto.response.ServiceResponse;
import ru.omsu.imit.khokhlov.barbershop.dto.response.ServiceResponseAndMastersId;
import ru.omsu.imit.khokhlov.barbershop.model.user.Cookie;
import ru.omsu.imit.khokhlov.barbershop.model.user.Master;
import ru.omsu.imit.khokhlov.barbershop.model.user.master.Service;
import ru.omsu.imit.khokhlov.barbershop.utils.ErrorCodes;
import ru.omsu.imit.khokhlov.barbershop.utils.ServerException;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class ServiceService extends BaseService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceService.class);

    public List<ServiceResponse> getServicesFromMaster(int masterId, String uuid) {
        LOGGER.debug("Service getServicesFromMaster masterId,uuid {},{}", masterId, uuid);
        try {
            responseProcessor.getCookie(uuid);
            Master master = masterDao.getById(masterId);
            if (master == null) {
                throw new ServerException(ErrorCodes.MASTER_ID_NOT_FOUND);
            }
            List<Service> masterServices = master.getService();

            return responseProcessor.getResponse(masterServices);
        } catch (Exception ex) {
            LOGGER.info("Service can't getServicesFromMaster masterId,uuid {},{}", masterId, uuid, ex);
            throw ex;
        }
    }

    public MasterInfoWithoutScheduleResponse deleteServices(int masterId, ServicesRequest servicesRequest, String uuid) {
        LOGGER.debug("Service deleteServices masterId,updateScheduleRequest,uuid {},{},{}", masterId, servicesRequest, uuid);
        try {
            Cookie cookie = responseProcessor.getCookie(uuid);
            responseProcessor.checkAdminPermission(cookie.getUser());
            Master master = masterDao.getById(masterId);
            if (master == null) {
                throw new ServerException(ErrorCodes.MASTER_ID_NOT_FOUND);
            }
            List<ServiceRequest> serviceRequests = servicesRequest.getServiceRequests();
            List<Service> services = new ArrayList<>();
            List<Service> masterServices = master.getService();
            for (ServiceRequest serviceRequest : serviceRequests) {
                Service service = serviceDao.getByName(serviceRequest.getName());
                if (service == null) {
                    service = new Service(serviceRequest.getName(), serviceRequest.getPrice(), serviceRequest.getDuration());
                    service = serviceDao.insert(service);
                }
                if (!masterServices.contains(service)) {
                    services.add(service);
                }
            }
            Master updateMaster = masterDao.deleteServices(master, services);
            for (Service service : services) {
                List<Integer> masterIds = masterDao.getByService(service);
                if (masterIds == null || masterIds.isEmpty()) {
                    serviceDao.deleteService(service);
                }
            }
            return responseProcessor.getResponse(updateMaster, cookie.getUser());
        } catch (Exception ex) {
            LOGGER.info("Service can't addService {},{},{}", masterId, servicesRequest, uuid, ex);
            throw ex;
        }
    }

    public List<ServiceResponseAndMastersId> getServices(String uuid) {
        LOGGER.debug("Service getServices uuid {}", uuid);
        try {
            responseProcessor.getCookie(uuid);
            List<Service> services = serviceDao.getAll();
            List<ServiceResponseAndMastersId> serviceResponses = new ArrayList<>(services.size());
            for (Service service : services) {
                List<Integer> masterIds = masterDao.getByService(service);
                serviceResponses.add(responseProcessor.getResponse(service, masterIds));
            }
            return serviceResponses;
        } catch (Exception ex) {
            LOGGER.info("Service can't getServices uuid {}", uuid, ex);
            throw ex;
        }
    }

}
