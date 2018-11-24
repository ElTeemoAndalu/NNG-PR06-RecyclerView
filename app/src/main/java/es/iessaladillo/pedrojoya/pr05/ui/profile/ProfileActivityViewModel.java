package es.iessaladillo.pedrojoya.pr05.ui.profile;

import androidx.lifecycle.ViewModel;
import es.iessaladillo.pedrojoya.pr05.data.local.data.Database;
import es.iessaladillo.pedrojoya.pr05.data.local.model.Avatar;

class ProfileActivityViewModel extends ViewModel {
    private Database database;
    private Avatar profileAvatar;
    private boolean firstLaunch = false;

    public Avatar getProfileAvatar() {
        return profileAvatar;
    }

    public void setProfileAvatar(Avatar profileAvatar) {
        this.profileAvatar = profileAvatar;
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
