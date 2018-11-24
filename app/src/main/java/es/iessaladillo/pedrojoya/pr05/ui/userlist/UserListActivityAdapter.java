package es.iessaladillo.pedrojoya.pr05.ui.userlist;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import es.iessaladillo.pedrojoya.pr05.R;
import es.iessaladillo.pedrojoya.pr05.data.local.model.User;

public class UserListActivityAdapter extends ListAdapter<User, UserListActivityAdapter.ViewHolder> {


    public UserListActivityAdapter() {
        super(new DiffUtil.ItemCallback<User>() {
            @Override
            public boolean areItemsTheSame(@NonNull User oldItem, @NonNull User newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull User oldItem, @NonNull User newItem) {
                return TextUtils.equals(oldItem.getName(), newItem.getName()) &&
                        oldItem.getId() == newItem.getId();
            }
        });
    }


    @Override
    public User getItem(int position) {
        return super.getItem(position);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.activity_user_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    //VIEWHOLDER
    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView lblName,lblEmail,lblPhoneNumber;
        private final ImageView imgAvatar;

        ViewHolder(View itemView) {
            super(itemView);
            lblName = ViewCompat.requireViewById(itemView, R.id.lblName);
            lblEmail = ViewCompat.requireViewById(itemView, R.id.lblEmail);
            lblPhoneNumber = ViewCompat.requireViewById(itemView, R.id.lblPhone);
            imgAvatar = ViewCompat.requireViewById(itemView, R.id.imgAvatar);
        }

        void bind(User user) {
            lblName.setText(user.getName());
            lblEmail.setText(user.getEmail());
            lblPhoneNumber.setText(user.getPhone());
            imgAvatar.setImageResource(user.getAvatar().getImageResId());

        }
    }
}
