package learnpath.DAO;

import learnpath.model.Schedule;
import java.util.List;

public interface ScheduleDAO {
    Schedule getById(String scheduleId);
    List<Schedule> getByUserId(String userId);
    List<Schedule> getAll();
    List<Schedule> getBySubjectId(String subjectId);
    List<Schedule> getByRoomId(String roomId);
    void insert(Schedule schedule);
    void update(Schedule schedule);
    void delete(String scheduleId);
}
