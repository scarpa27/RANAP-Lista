package hr.tvz.android.listacizmic.links;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hr.tvz.android.listacizmic.R;
import hr.tvz.android.listacizmic.models.Transaction;
import hr.tvz.android.listacizmic.models.TransactionConverter;
import hr.tvz.android.listacizmic.utils.Util;

public class TransactionsAdapter extends RecyclerView.Adapter<TransactionsAdapter.MyViewHolder> {

    private List<Transaction> transactions;
    private TransactionClickHandler mListener;

    public interface TransactionClickHandler {
        void onTransactionClick(int pos);
    }

    public void setTransactionClickHandler (TransactionClickHandler _tch) {
        this.mListener = _tch;
    }


    public TransactionsAdapter(ArrayList<Transaction> _l) {
        this.transactions = _l;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @NonNull
    @Override
    public TransactionsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View transactionsView = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_item, parent, false);

        return new MyViewHolder(transactionsView, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionsAdapter.MyViewHolder holder, int position) {
        Transaction t = transactions.get(position);
        holder.tvAmount.setText(t.getAmount().toString());
        holder.tvDate.setText(TransactionConverter.localDateToString(t.getDate()));
        holder.tvImage.setImageURI(t.getImg());
//        holder.item.setOnLongClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }


    protected static class MyViewHolder extends RecyclerView.ViewHolder{
        private final TextView tvAmount;
        private final TextView tvDate;
        private final ImageView tvImage;

        public MyViewHolder(final View itemView, TransactionClickHandler handler) {
            super(itemView);
            tvAmount = itemView.findViewById(R.id.tv_amount);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvImage = itemView.findViewById(R.id.item_image);
            itemView.setOnLongClickListener(view -> {
                if (handler != null) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION)
                        handler.onTransactionClick(pos);
                }
                return true;
            });
        }
    }
}
