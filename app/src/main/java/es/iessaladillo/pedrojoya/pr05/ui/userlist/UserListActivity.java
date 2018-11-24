package es.iessaladillo.pedrojoya.pr05.ui.userlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import es.iessaladillo.pedrojoya.pr05.R;
import es.iessaladillo.pedrojoya.pr05.data.local.data.Database;
import es.iessaladillo.pedrojoya.pr05.data.local.model.User;
import es.iessaladillo.pedrojoya.pr05.databinding.ActivityUserListBinding;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class UserListActivity extends AppCompatActivity {

    private ActivityUserListBinding db;
    private UserListActivityViewModel vm;
    private UserListActivityAdapter ulistAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        db = DataBindingUtil.setContentView(this,R.layout.activity_user_list);
        vm = ViewModelProviders.of(this, new UserListActivityViewModelFactory(Database.getInstance())).get(UserListActivityViewModel.class);
        setupRecyclerView();
        observeUsers();
    }

    private void setupRecyclerView() {
        ulistAdapter = new UserListActivityAdapter();
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

    private void showStudent(User user) {
        Toast.makeText(this, user.getName(), Toast.LENGTH_SHORT).show();
    }


}
