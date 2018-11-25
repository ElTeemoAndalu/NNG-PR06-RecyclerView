package es.iessaladillo.pedrojoya.pr05.ui.profile;

import androidx.lifecycle.ViewModel;
import es.iessaladillo.pedrojoya.pr05.data.local.data.Database;
import es.iessaladillo.pedrojoya.pr05.data.local.model.Avatar;
import es.iessaladillo.pedrojoya.pr05.data.local.model.User;

class ProfileActivityViewModel extends ViewModel {
    private Database database;
    private boolean firstLaunch = false;
    private User profileUser;

    public User getProfileUser() {
        return profileUser;
    }

    public void setProfileUser(User profileUser) {
        this.profileUser = profileUser;
    }

    public Avatar getProfileAvatar() {
        return profileUser.getAvatar();
    }

    public void setProfileAvatar(Avatar avatar) {
        profileUser.setAvatar(avatar);
    }

    private Database getDatabase() {
        if (database == null) {
            database = Database.getInstance();
        }
        return database;
    }

    //Sets the avatar to the default one (One time use)
    public void setDefaultAvatar() {
        if (!firstLaunch) {
            setProfileAvatar(getDatabase().getDefaultAvatar());
            firstLaunch = true;
        }


    }


}
