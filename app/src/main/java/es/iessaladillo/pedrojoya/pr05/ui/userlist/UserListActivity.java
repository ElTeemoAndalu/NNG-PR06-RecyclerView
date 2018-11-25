package es.iessaladillo.pedrojoya.pr05.ui.userlist;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import es.iessaladillo.pedrojoya.pr05.R;
import es.iessaladillo.pedrojoya.pr05.data.local.data.Database;
import es.iessaladillo.pedrojoya.pr05.data.local.model.User;
import es.iessaladillo.pedrojoya.pr05.databinding.ActivityUserListBinding;
import es.iessaladillo.pedrojoya.pr05.ui.profile.ProfileActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UserListActivity extends AppCompatActivity {

    private static final int RC_PROFILE_EDIT = 9999;
    private static final int RC_PROFILE_ADD = 3000;
    private ActivityUserListBinding db;
    private UserListActivityViewModel vm;
    private UserListActivityAdapter ulistAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        db = DataBindingUtil.setContentView(this, R.layout.activity_user_list);
        vm = ViewModelProviders.of(this, new UserListActivityViewModelFactory(Database.getInstance())).get(UserListActivityViewModel.class);
        setupViews();
        observeUsers();
    }

    private void setupViews() {
        setupRecyclerView();
        db.lblEmptyView.setOnClickListener(v -> goToProfile(null, RC_PROFILE_ADD));
        db.floatingActionButton2.setOnClickListener(v -> goToProfile(null, RC_PROFILE_ADD));
    }

    private void setupRecyclerView() {
        ulistAdapter = new UserListActivityAdapter(
                position -> goToProfile(ulistAdapter.getItem(position), RC_PROFILE_EDIT),
                position2 -> deleteUser(ulistAdapter.getItem(position2))
        );
        db.lstUser.setHasFixedSize(true);
        db.lstUser.setLayoutManager(new GridLayoutManager(this,
                getResources().getInteger(R.integer.main_lstUser_colums)));
        db.lstUser.setItemAnimator(new DefaultItemAnimator());
        db.lstUser.setAdapter(ulistAdapter);
    }

    private void observeUsers() {
        vm.getUsers().observe(this, users -> {
            ulistAdapter.submitList(users);
            db.lblEmptyView.setVisibility(users.size() == 0 ? View.VISIBLE : View.INVISIBLE);
        });
    }

    private void editUser(User user) {
        vm.editUser(user);
    }

    private void deleteUser(User user) {
        vm.deleteUser(user);
    }

    private void addUser(User user) {
        vm.addUser(user);
    }

    //---------------------------------------METHODS TO START ACTIVITIES---------------------------
    private void goToProfile(User user, int requestCode) {
        ProfileActivity.startForResult(this, requestCode, user);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            getReturnedUser(data);
            if (requestCode == RC_PROFILE_EDIT) {
                editUser(vm.getReturnedUser());
            } else if (requestCode == RC_PROFILE_ADD) {
                addUser(vm.getReturnedUser());
            }
        }
    }

    private void getReturnedUser(Intent receivedIntent) {
        if (receivedIntent != null && receivedIntent.hasExtra(ProfileActivity.EXTRA_PROFILE)) {
            vm.setReturnedUser(receivedIntent.getParcelableExtra(ProfileActivity.EXTRA_PROFILE));
        }
    }

    //----------------------------------------------------------------------------------------------

}
