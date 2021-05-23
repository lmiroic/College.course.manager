package org.foi.emp.collegecoursemanager.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import org.foi.emp.collegecoursemanager.Activities.DodavanjeBodovaKolegiju;
import org.foi.emp.collegecoursemanager.R;
import org.foi.emp.core.Database.Database;
import org.foi.emp.core.Entities.ElementModelaPracenja;
import org.foi.emp.core.Entities.Kolegij;

import java.util.ArrayList;
import java.util.List;

public class KolegijAdapter extends RecyclerView.Adapter<KolegijAdapter.KolegijHolder> {
    private List<Kolegij> sviKolegiji = new ArrayList<>();
    private Context context;
    private AppCompatActivity activity;
    private int brojBodova;
    private String brojBodovaOd100;

    public KolegijAdapter(Context context, AppCompatActivity activity) {
        this.context = context;
        this.activity = activity;
    }

    public void postaviKolegije(List<Kolegij> sviProslijedeniKolegiji) {
        this.sviKolegiji = sviProslijedeniKolegiji;
    }

    public Kolegij getKolegijAtPosition(final int position) {
        return sviKolegiji.get(position);
    }

    public void removeKolegijAtPosition(final int position) {
        sviKolegiji.remove(position);
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
        brojBodova = Database.getInstance(context).getModelPracenjaDAO().dohvatiElementeModelaPracenja(trenutniKolegij.getModelPracenja())
                .stream()
                .map(elementiNaModeluPracenja -> elementiNaModeluPracenja.getElementModelaPracenja())
                .map(emnp -> {
                    return Database.getInstance(context).getModelPracenjaDAO().dohvatiElementModelaPracenja(emnp);
                }).mapToInt(bod -> bod.getOstvareniBodovi()).sum();
        Database.getInstance(context).getModelPracenjaDAO().dohvatiElementeModelaLIVE(trenutniKolegij.getModelPracenja()).observe(activity, new Observer<List<ElementModelaPracenja>>() {
            @Override
            public void onChanged(List<ElementModelaPracenja> elementModelaPracenjas) {
                brojBodova = elementModelaPracenjas
                        .stream()
                        .mapToInt(bod -> bod.getOstvareniBodovi()).sum();
                brojBodovaOd100 = brojBodova + " / 100";
                holder.tvBrojBodova.setText(brojBodovaOd100);
                holder.tvOcjena.setText(getGradeFromPoints(brojBodova));
            }
        });
        brojBodovaOd100 = brojBodova + " / 100";
        holder.tvBrojBodova.setText(brojBodovaOd100);
        holder.tvOcjena.setText(this.getGradeFromPoints(brojBodova));
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
            this.view = itemView;
            this.tvNazivKolegija = view.findViewById(R.id.tvNazivKolegija);
            this.tvOcjena = view.findViewById(R.id.tvOcjena);
            this.tvBrojBodova = view.findViewById(R.id.tvBrojBodova);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, DodavanjeBodovaKolegiju.class);
                    i.putExtra("ID_kolegija", sviKolegiji.get(getPosition()).getId());
                    context.startActivity(i);
                }
            });
        }

        @Override
        public void onClick(View v) {
        }
    }

    public String getGradeFromPoints(final int brojBodova) {
        try {
            if (brojBodova < 50) {
                return "1";
            }
            if (brojBodova >= 50 && brojBodova <= 60) {
                return "2";
            }
            if (brojBodova >= 61 && brojBodova <= 75) {
                return "3";
            }
            if (brojBodova >= 76 && brojBodova <= 90) {
                return "4";
            }
            if (brojBodova >= 90) {
                return "5";
            }
        } catch (final ArithmeticException e) {
            Log.e("Exception", e.getMessage());
        }
        return "";
    }
}
