package br.edu.ifspsaocarlos.minhasfinancas.adapter;

import android.content.Context;
import android.net.sip.SipSession;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.edu.ifspsaocarlos.minhasfinancas.R;
import br.edu.ifspsaocarlos.minhasfinancas.model.Movimentacao;

public class AdapterMovimentacao extends RecyclerView.Adapter<AdapterMovimentacao.MyViewHolder> {
    public interface MovimentacaoListener{
        void onClick (Movimentacao movimentacao);
    }
    MovimentacaoListener listener;

    List<Movimentacao> movimentacoes;
    Context context;

    public AdapterMovimentacao(List<Movimentacao> movimentacoes, Context context, MovimentacaoListener listener) {
        this.movimentacoes = movimentacoes;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_movimentacao, parent, false);
        return new MyViewHolder(itemLista);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Movimentacao movimentacao = movimentacoes.get(position);

        holder.titulo.setText(movimentacao.getDescricao());
        holder.valor.setText(String.valueOf(movimentacao.getValor()));
        holder.categoria.setText(movimentacao.getCategoria());
        holder.valor.setTextColor(context.getResources().getColor(R.color.colorAccentReceita));

        if (movimentacao.getTipo().equals("d")) {
            holder.valor.setTextColor(context.getResources().getColor(R.color.colorAccent));
            holder.valor.setText("-" + movimentacao.getValor());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(movimentacao);
            }
        });
    }


    @Override
    public int getItemCount() {
        return movimentacoes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titulo, valor, categoria;

        public MyViewHolder(View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.textAdapterTitulo);
            valor = itemView.findViewById(R.id.textAdapterValor);
            categoria = itemView.findViewById(R.id.textAdapterCategoria);
        }

    }

}

