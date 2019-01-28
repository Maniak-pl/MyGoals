package pl.maniak.mygoals.ui.edit.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import pl.maniak.mygoals.R;
import pl.maniak.mygoals.model.EnumSpinner;

public class EnumAdapter extends ArrayAdapter<EnumSpinner> {

    Context mContext;
    int mLayoutResourceId;
    EnumSpinner[] mItems;

    public EnumAdapter(Context context, int layoutResourceId,
                       EnumSpinner[] data) {
        super(context, layoutResourceId, data);
        this.mLayoutResourceId = layoutResourceId;
        this.mContext = context;
        this.mItems = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        Holder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(mLayoutResourceId, parent, false);

            holder = new Holder();
            holder.txtTitle = (TextView) row
                    .findViewById(R.id.spinnerTextView);
            row.setTag(holder);
        } else {
            holder = (Holder) row.getTag();
        }

        EnumSpinner item = mItems[position];
        holder.txtTitle.setText(item.getLabel());
        holder.txtTitle.setTextColor(item.getColor());
        return row;
    }

    static class Holder {
        TextView txtTitle;
    }
}