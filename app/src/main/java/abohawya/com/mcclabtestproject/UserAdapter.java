package abohawya.com.mcclabtestproject;


import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by USER15 on 1/11/2018.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private List<User> userList;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView user_name, age, email, phone;
        ImageView image;

        public MyViewHolder(View view) {
            super(view);
            user_name = (TextView) view.findViewById(R.id.user_name);
            age = (TextView) view.findViewById(R.id.age);
            email = (TextView) view.findViewById(R.id.email);
            phone = (TextView) view.findViewById(R.id.phone);
        }
    }

    public UserAdapter(List<User> userList) {
        this.userList = userList;
        Log.d("MyAdapter", userList.size()+"");
    }

    @Override
    public UserAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_user_list, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(UserAdapter.MyViewHolder holder, int position) {
        User user = userList.get(position);
        holder.user_name.setText(user.getName());
        holder.age.setText(user.getAge());
        holder.email.setText(user.getEmail());
        holder.phone.setText(user.getPhone());
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return userList.size();
    }
}
