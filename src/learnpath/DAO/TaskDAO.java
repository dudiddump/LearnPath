package learnpath.DAO;

import learnpath.model.Task;
import java.util.List;

public interface TaskDAO {
    Task getById(String taskId);
    List<Task> getByUserId(String userId);
    List<Task> getBySubjectId(String subjectId);
    List<Task> getByRoomId(String roomId);
    void insert(Task task);
    void update(Task task);
    void delete(String taskId);
}
