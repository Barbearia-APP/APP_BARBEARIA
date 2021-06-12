package io.osvaldocabral.appbarbearia;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EstablishmentAdapter extends RecyclerView.Adapter<EstablishmentAdapter.ViewHolder> {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.establishment_row_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView_establishment_row_item.setImageResource(R.drawable.ic_launcher_background);

        holder.textView_name_establishment_row_item.setText("NOSTRINKS CABELEREIRO");
        holder.textView_address_establishment_row_item.setText("Rua do Barbeiro, 1345");
        holder.textView_phone_establishment_row_item.setText("(41) 9 8888-8888");
    }


    @Override
    public int getItemCount() {
        return 0;
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
