package com.losnn.spacia.presentation.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.losnn.spacia.R;
import com.losnn.spacia.data.remote.response.EventResponse;

import java.util.List;

import butterknife.BindView;

public class ResourcesRecyclerAdapter extends RecyclerView.Adapter<ResourcesRecyclerAdapter.ViewHolder> {

    Context context;
    List<EventResponse.Resource> list;

    public ResourcesRecyclerAdapter(Context context, List<EventResponse.Resource> list){
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_resource, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EventResponse.Resource currentItem = list.get(position);
        switch (currentItem.getResource_id()) {
            case 1:
                holder.ivResource.setImageResource(R.drawable.proyector);
                break;
            case 2:
                holder.ivResource.setImageResource(R.drawable.meat);
                break;
            case 3:
                holder.ivResource.setImageResource(R.drawable.cofee);
                break;
            case 4:
                holder.ivResource.setImageResource(R.drawable.book);
                break;
            case 5:
                holder.ivResource.setImageResource(R.drawable.tv);
                break;
            case 6:
                holder.ivResource.setImageResource(R.drawable.proyector);
                break;
            case 7:
                holder.ivResource.setImageResource(R.drawable.laptop);
                break;

                default:
                    holder.ivResource.setImageResource(R.drawable.book);
                    break;

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_resources)
        ImageView ivResource;
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
