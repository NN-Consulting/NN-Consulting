package com.losnn.spacia.presentation.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.losnn.spacia.R;
import com.losnn.spacia.data.remote.response.EventResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventsRecyclerAdapter extends RecyclerView.Adapter<EventsRecyclerAdapter.ViewHolder> {

    List<EventResponse> list;
    Context context;
    public EventsRecyclerAdapter(Context context,List<EventResponse> list){
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EventResponse currentItem = list.get(position);
        holder.txtNameEvent.setText(currentItem.getName());
        holder.txtPeople.setText(""+currentItem.getNum_resources()+" personas");
        holder.txtRoom.setText(currentItem.getRoom().getName());
        holder.txtDate.setText(currentItem.getDate()+" "+currentItem.getHour_from()+" - "+currentItem.getHour_to());
        holder.recyclerView.setAdapter(new ResourcesRecyclerAdapter(context,currentItem.getResources()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_nameevent)
        TextView txtNameEvent;
        @BindView(R.id.txt_room)
        TextView txtRoom;
        @BindView(R.id.txt_date)
        TextView txtDate;
        @BindView(R.id.txt_people)
        TextView txtPeople;
        @BindView(R.id.recyclerView)
        RecyclerView recyclerView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
        }
    }
}
