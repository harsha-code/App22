package com.example.harsha.app2;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class UserRecyclerViewAdapter extends RecyclerView.Adapter<UserRecyclerViewAdapter.UserViewHolder> {
    List<User> users = Collections.emptyList();
    LayoutInflater layoutInflater;
    Context context;

    public UserRecyclerViewAdapter(Context context, List<User> users){
        this.context = context;
        this.users = users;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = layoutInflater.inflate(R.layout.single_user_row, parent, false);
        UserViewHolder holder = new UserViewHolder(row);

        return holder;
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        User thisUser = users.get(position);

        holder.userName.setText(thisUser.name);

        Picasso picasso = Picasso.with(context);
        /*
        * Enable Debug indicator which indicates the image source from memory, disk or Internet
        *
        * Green -> from Memory (Cached)
        * Blue -> from Disk (Cached)
        * Red -> Not cached, loaded from Network
        * */
        picasso.setIndicatorsEnabled(true);
        picasso.load(thisUser.image)
                // .resize(96, 96) Use it to better fit into layouts and to reduce memory size
                .placeholder(R.drawable.ic_action_name) // display this image while loading images
                .error(R.drawable.ic_action_name) // Loaded if URL is wrong or failed to load resources
                .into(holder.userImage);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        private TextView userName;
        private ImageView userImage;

        public UserViewHolder(View itemView) {
            super(itemView);

            userName = (TextView) itemView.findViewById(R.id.user_name);
            userImage = (ImageView) itemView.findViewById(R.id.user_image);
        }
    }
}