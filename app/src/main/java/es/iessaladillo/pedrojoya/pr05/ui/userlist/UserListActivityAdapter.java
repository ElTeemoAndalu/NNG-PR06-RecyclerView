package es.iessaladillo.pedrojoya.pr05.ui.userlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

    private final OnDeleteClickListener deleteListener;
    private final OnEditClickListener editListener;

    public UserListActivityAdapter(OnEditClickListener editListener, OnDeleteClickListener deleteListener) {
        super(new DiffUtil.ItemCallback<User>() {
            @Override
            public boolean areItemsTheSame(@NonNull User oldItem, @NonNull User newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull User oldItem, @NonNull User newItem) {
                return oldItem.equals(newItem);
            }
        });

        this.editListener = editListener;
        this.deleteListener = deleteListener;
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
                        .inflate(R.layout.activity_user_list_item, parent, false), editListener, deleteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    //VIEWHOLDER
    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView lblName,lblEmail,lblPhoneNumber;
        private final ImageView imgAvatar;
        private final Button btnEdit,btnDelete;

        ViewHolder(View itemView,OnEditClickListener editListener, OnDeleteClickListener deleteListener) {
            super(itemView);
            lblName = ViewCompat.requireViewById(itemView, R.id.lblName);
            lblEmail = ViewCompat.requireViewById(itemView, R.id.lblEmail);
            lblPhoneNumber = ViewCompat.requireViewById(itemView, R.id.lblPhone);
            imgAvatar = ViewCompat.requireViewById(itemView, R.id.imgAvatar);
            btnEdit = ViewCompat.requireViewById(itemView, R.id.btnEdit);
            btnDelete = ViewCompat.requireViewById(itemView, R.id.btnDelete);

            btnEdit.setOnClickListener(v -> editListener.onEditClick(getAdapterPosition()));
            btnDelete.setOnClickListener(v -> deleteListener.onDeleteClick(getAdapterPosition()));
        }

        void bind(User user) {
            lblName.setText(user.getName());
            lblEmail.setText(user.getEmail());
            lblPhoneNumber.setText(user.getPhone());
            imgAvatar.setImageResource(user.getAvatar().getImageResId());

        }
    }
}
