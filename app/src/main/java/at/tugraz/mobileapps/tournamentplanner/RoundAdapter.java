package at.tugraz.mobileapps.tournamentplanner;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by fiona on 15.06.15.
 */
public class RoundAdapter extends RecyclerView.Adapter<RoundAdapter.ViewHolder>{

    private static final String TAG = "RoundAdapter";

    private ArrayList<Encounter> encounters;

    public RoundAdapter(ArrayList<Encounter> encounters) {
        this.encounters = encounters;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.round_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Encounter encounter = encounters.get(position);
        holder.round_player1.setText(encounter.getPlayer1().getName());
        holder.round_player2.setText(encounter.getPlayer2().getName());

        if (position != 0) {
            holder.round_title.setVisibility(View.GONE);
            holder.round_header.setVisibility(View.GONE);
        }

        if (position != encounters.size() - 1) {
            holder.finish_button.setVisibility(View.GONE);
        }

        holder.score.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int score, boolean b) {
                Log.d(TAG, "Score set: " + score);
                // TODO highlight colour for player
                encounters.get(position).setScore(score);
                AppContext c = AppContext.getInstance();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return encounters.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView round_player1;
        public TextView round_player2;
        public SeekBar score;

        public TextView round_title;
        public LinearLayout round_header;

        public Button finish_button;

        public ViewHolder(View itemView) {
            super(itemView);
            round_player1 = (TextView)itemView.findViewById(R.id.round_player1);
            round_player2 = (TextView)itemView.findViewById(R.id.round_player2);
            score = (SeekBar)itemView.findViewById(R.id.winner_bar);

            round_title = (TextView) itemView.findViewById(R.id.title_round);
            round_header = (LinearLayout) itemView.findViewById(R.id.header_round);

            finish_button = (Button) itemView.findViewById(R.id.finished_button);

        }

    }

    public void setEncounters(ArrayList<Encounter> encounters) {
        this.encounters = encounters;
        notifyDataSetChanged();
    }
}
