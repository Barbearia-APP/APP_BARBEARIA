package io.osvaldocabral.appbarbearia.Components;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;

import io.osvaldocabral.appbarbearia.DataSingleton;
import io.osvaldocabral.appbarbearia.Model.Establishment;
import io.osvaldocabral.appbarbearia.R;

public class EstablishmentAdapter extends RecyclerView.Adapter<EstablishmentAdapter.ViewHolder> {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(R.layout.establishment_row_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Establishment establishment = DataSingleton.getInstance().listEstablishment.get(position);

        File file = new File(establishment.getCoverPicturePath());
        holder.imageView_establishment_row_item.setImageURI(Uri.fromFile(file));
        holder.textView_name_establishment_row_item.setText(establishment.getName());
        holder.textView_address_establishment_row_item.setText(establishment.getAddress());
        holder.textView_phone_establishment_row_item.setText(establishment.getPhone());
    }


    @Override
    public int getItemCount() {
        return DataSingleton.getInstance().listEstablishment.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView_establishment_row_item;
        TextView textView_name_establishment_row_item,
                textView_address_establishment_row_item,
                textView_phone_establishment_row_item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView_establishment_row_item = itemView.findViewById(R.id.imageView_establishment_row_item);
            textView_name_establishment_row_item = itemView.findViewById(R.id.textView_name_establishment_row_item);
            textView_address_establishment_row_item = itemView.findViewById(R.id.textView_address_establishment_row_item);
            textView_phone_establishment_row_item = itemView.findViewById(R.id.textView_phone_establishment_row_item);
        }
    }
}
