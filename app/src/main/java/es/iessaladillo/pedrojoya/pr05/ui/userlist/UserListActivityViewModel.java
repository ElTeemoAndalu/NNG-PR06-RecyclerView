package es.iessaladillo.pedrojoya.pr05.ui.userlist;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import es.iessaladillo.pedrojoya.pr05.data.local.data.Database;
import es.iessaladillo.pedrojoya.pr05.data.local.model.User;

public class UserListActivityViewModel extends ViewModel {
    private Database database;
    private LiveData<List<User>> users;

    public UserListActivityViewModel(Database database) {
        this.database = database;
        this.users = database.getUsers();
    }

    LiveData<List<User>> getUsers() {
        return users;
    }

    void deleteUser(User user) {
        database.deleteUser(user);
    }

    void editUser(User user) {
        database.editUser(user);
    }
}
