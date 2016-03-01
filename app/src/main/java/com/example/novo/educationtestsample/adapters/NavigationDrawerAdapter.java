package com.example.novo.educationtestsample.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.novo.educationtestsample.R;
import com.example.novo.educationtestsample.activities.MainActivity;
import com.example.novo.educationtestsample.fragments.FragmentDrawer;
import com.example.novo.educationtestsample.interfaces.ClickListener;
import com.example.novo.educationtestsample.models.NavDrawerItem;

import java.util.Collections;
import java.util.List;

/**
 * Created by Varun Ajay Gupta on 28/2/16.
 */
public class NavigationDrawerAdapter extends RecyclerView.Adapter<NavigationDrawerAdapter.NavRowViewHolder> {

    List<NavDrawerItem> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;
    private ClickListener clickListener;


    public NavigationDrawerAdapter(Context context, List<NavDrawerItem> data, ClickListener clickListener) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.clickListener=clickListener;
    }

    public void delete(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }
    @Override
    public NavigationDrawerAdapter.NavRowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= inflater.inflate(R.layout.nav_drawer_row,parent,false);
        NavRowViewHolder navRowViewHolder= new NavRowViewHolder(view);
        return navRowViewHolder;
    }

    @Override
    public void onBindViewHolder(NavigationDrawerAdapter.NavRowViewHolder holder, final int position) {
     holder.title.setText(data.get(position).getTitle());
     holder.itemView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             clickListener.onClick(position);


         }
     });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class NavRowViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        public NavRowViewHolder(View itemView) {
        super(itemView);
        title=(TextView)itemView.findViewById(R.id.row_title);
        }
    }
}
