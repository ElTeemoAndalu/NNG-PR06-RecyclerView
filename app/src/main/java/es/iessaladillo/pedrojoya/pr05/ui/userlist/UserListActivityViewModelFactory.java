package es.iessaladillo.pedrojoya.pr05.ui.userlist;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import es.iessaladillo.pedrojoya.pr05.data.local.data.Database;

public class UserListActivityViewModelFactory implements ViewModelProvider.Factory {

    private final Database database;

    UserListActivityViewModelFactory(Database database) {
        this.database = database;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new UserListActivityViewModel(database);
    }
}
