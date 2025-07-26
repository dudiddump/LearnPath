package learnpath.DAO;

import learnpath.model.Subject;
import java.util.List;

public interface SubjectDAO {
    Subject getById(String subjectId);
    List<Subject> getByRoomId(String roomId);
    void insert(Subject subject);
    void update(Subject subject);
    void delete(String subjectId);
}
