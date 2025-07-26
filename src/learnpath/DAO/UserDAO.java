package learnpath.DAO;

import learnpath.model.User;
import java.util.List;

public interface UserDAO {
    User getById(String userId);
    User getByUsername(String username);
    User getByEmail(String email);
    User getByUsernameAndPassword(String email, String password) throws Exception;
    boolean insertUser(User user) throws Exception;
    List<User> getAll();
    void insert(User user);
    void update(User user);
    void delete(String userId);
}
