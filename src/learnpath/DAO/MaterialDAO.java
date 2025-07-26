package learnpath.DAO;

import learnpath.model.Material;
import java.util.List;

public interface MaterialDAO {
    Material getById(String fileId);
    List<Material> getByUserId(String userId);
    List<Material> getBySubjectId(String subjectId);
    List<Material> getByRoomId(String roomId);
    List<Material> getAll();
    void insert(Material material);
    void update(Material material);
    void delete(String fileId);
}
