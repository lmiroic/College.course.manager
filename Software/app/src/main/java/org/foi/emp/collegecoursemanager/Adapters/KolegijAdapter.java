package org.foi.emp.collegecoursemanager.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import org.foi.emp.collegecoursemanager.DodavanjeBodovaKolegiju;
import org.foi.emp.collegecoursemanager.R;
import org.foi.emp.core.Database.Database;
import org.foi.emp.core.Entities.ElementModelaPracenja;
import org.foi.emp.core.Entities.ElementiNaModeluPracenja;
import org.foi.emp.core.Entities.Kolegij;

import java.util.ArrayList;
import java.util.List;

public class KolegijAdapter extends RecyclerView.Adapter<KolegijAdapter.KolegijHolder> {
    private List<Kolegij> sviKolegiji=new ArrayList<>();
    private Context context;

    public KolegijAdapter(Context context) {
        this.context = context;
    }

    public void setKolegiji(List<Kolegij> sviProslijedeniKolegiji) {
        sviKolegiji = sviProslijedeniKolegiji;
    }

    @NonNull
    @Override
    public KolegijAdapter.KolegijHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kolegij, parent, false);
        return new KolegijAdapter.KolegijHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull KolegijAdapter.KolegijHolder holder, final int position) {
        final Kolegij trenutniKolegij = sviKolegiji.get(position);
        String brojBodovaOd100="";
        int brojBodova = Database.getInstance(context).getModelPracenjaDAO().dohvati(trenutniKolegij.getModelPracenja())
                .stream()
                .map(elementiNaModeluPracenja -> elementiNaModeluPracenja.getElementModelaPracenja())
                .map(emnp -> {return Database.getInstance(context).getModelPracenjaDAO().DohvatiElementModelaPracenja(emnp);
        }).mapToInt(bod -> bod.getOstvareniBodovi()).sum();
        brojBodovaOd100=brojBodova+" / 100";
        holder.tvBrojBodova.setText(brojBodovaOd100);
        try{
            if(brojBodova<50){
                holder.tvOcjena.setText("1");
            }
            if(brojBodova>=50&&brojBodova<=60){
                holder.tvOcjena.setText("2");
            }
            if(brojBodova>=61&&brojBodova<=75){
                holder.tvOcjena.setText("3");
            }
            if(brojBodova>=76&&brojBodova<=90){
                holder.tvOcjena.setText("4");
            }
            if(brojBodova>=90) {
                holder.tvOcjena.setText("5");
            }
        }catch(ArithmeticException e){
            Log.e("Exception",e.getMessage());
        }
        holder.tvNazivKolegija.setText(trenutniKolegij.getNazivKolegija());
    }

    @Override
    public int getItemCount() {
        return sviKolegiji.size();
    }

    public class KolegijHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvOcjena;
        private TextView tvBrojBodova;
        private TextView tvNazivKolegija;
        private View view;
        public KolegijHolder(@NonNull View itemView) {
            super(itemView);
            view=itemView;
            tvNazivKolegija=view.findViewById(R.id.tvNazivKolegija);
            tvOcjena=view.findViewById(R.id.tvOcjena);
            tvBrojBodova=view.findViewById(R.id.tvBrojBodova);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(context,DodavanjeBodovaKolegiju.class);
                    i.putExtra("ID_kolegija",sviKolegiji.get(getPosition()).getId());
                    context.startActivity(i);
                }
            });
        }

        @Override
        public void onClick(View v) {


        }
    }
}
