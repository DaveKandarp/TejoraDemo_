package kandy.com.locationtracking.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import kandy.com.locationtracking.R;
import kandy.com.locationtracking.pojo.TripModel;

public class CompletedExpandableAdapter extends RecyclerView.Adapter<CompletedExpandableAdapter.ViewHolder> {

    private Context context;
    private ArrayList<TripModel> modelsAll = new ArrayList<>();

    public CompletedExpandableAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CompletedExpandableAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CompletedExpandableAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.completed_expandable_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CompletedExpandableAdapter.ViewHolder holder, final int position) {

        final TripModel model = modelsAll.get(position);
        holder.textView.setText(model.getName());
        holder.tvDesignation.setText(model.getTrip_date_time());

        holder.itemView.setTag(R.string.MODEL, model);
        holder.itemView.setTag(R.string.position, position);

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) holder.rlContent.getLayoutParams();
        layoutParams.setMargins(((int) convertDpToPixel(20, context)) * model.getLevel(), layoutParams.topMargin, layoutParams.rightMargin, layoutParams.bottomMargin);
        switch (model.state) {

            case CLOSED:
                holder.imgArrow.setImageResource(R.drawable.svg_arrow_right_filled);
                break;
            case OPENED:
                holder.imgArrow.setImageResource(R.drawable.svg_arrow_down_filled);
                break;
        }


        if (model.models.isEmpty()) {
            holder.imgArrow.setVisibility(View.INVISIBLE);
            holder.viewDashed.setVisibility(View.VISIBLE);
        } else {
            holder.imgArrow.setVisibility(View.VISIBLE);
            holder.viewDashed.setVisibility(View.INVISIBLE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int position = (int) v.getTag(R.string.position);
                TripModel rootModel = (TripModel) v.getTag(R.string.MODEL);
                if (rootModel.getActivitiesPOJOS() == null) {
                    return;
                }
                switch (rootModel.state) {

                    case CLOSED:
                        modelsAll.addAll(position + 1, rootModel.models);
                        notifyItemRangeInserted(position + 1, rootModel.models.size());
                        notifyItemRangeChanged(position + rootModel.models.size(), modelsAll.size() - (position + rootModel.models.size()));
                        notifyItemRangeChanged(position, modelsAll.size() - position);
                        rootModel.state = TripModel.STATE.OPENED;
                        break;

                    case OPENED:

                        int start = position + 1;
                        int end = modelsAll.size();
                        for (int i = start; i < modelsAll.size(); i++) {
                            TripModel model1 = modelsAll.get(i);
                            if (model1.getLevel() <= rootModel.getLevel()) {
                                end = i;
                                break;
                            } else {
                                if (model1.state == TripModel.STATE.OPENED) {
                                    model1.state = TripModel.STATE.CLOSED;
                                }
                            }
                        }

                        if (end != -1) {
                            modelsAll.subList(start, end).clear();
                            notifyItemRangeRemoved(start, end - start);
                            notifyItemRangeChanged(start, end - start);
                            notifyItemRangeChanged(position, modelsAll.size() - position);
                        }

                        rootModel.state = TripModel.STATE.CLOSED;
                        break;
                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return modelsAll.size();
    }

    public void setData(ArrayList<TripModel> list) {
        modelsAll = list;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout rlContent;
        TextView textView;
        TextView tvDesignation;
        ImageView imgArrow;
        View viewDashed;

        public ViewHolder(View itemView) {

            super(itemView);
            textView = itemView.findViewById(R.id.tvName);
            imgArrow = itemView.findViewById(R.id.imgArrow);
            rlContent = itemView.findViewById(R.id.rlContent);
            viewDashed = itemView.findViewById(R.id.viewDashed);
            tvDesignation = itemView.findViewById(R.id.tvDesignation);
        }
    }


    public static float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px      A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent dp equivalent to px value
     */
    public static float convertPixelsToDp(float px, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return dp;
    }
}
