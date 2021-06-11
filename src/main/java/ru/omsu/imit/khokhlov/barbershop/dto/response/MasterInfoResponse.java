package ru.omsu.imit.khokhlov.barbershop.dto.response;

import java.util.List;


public class MasterInfoResponse extends MasterInfoWithoutScheduleResponse{

    private List<DayScheduleResponse> dayScheduleResponses;

    public MasterInfoResponse() {
    }

    public MasterInfoResponse(Integer id, String firstName, String lastName, String patronymic, SpecializationResponse specialization,
                              List<ServiceResponse> services, List<DayScheduleResponse> dayScheduleResponses) {
        super(id, firstName, lastName, patronymic, specialization, services);
        this.dayScheduleResponses = dayScheduleResponses;
    }

    public List<DayScheduleResponse> getDayScheduleResponses() {
        return dayScheduleResponses;
    }

    public void setDayScheduleResponses(List<DayScheduleResponse> dayScheduleResponses) {
        this.dayScheduleResponses = dayScheduleResponses;
    }
}
