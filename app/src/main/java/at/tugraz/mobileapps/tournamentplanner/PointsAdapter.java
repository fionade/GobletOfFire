package at.tugraz.mobileapps.tournamentplanner;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by fiona on 16.06.15.
 */
public class PointsAdapter extends RecyclerView.Adapter<PointsAdapter.ViewHolder>{

    private static final String TAG = "PointsAdapter";

    private ArrayList<Player> players;
    private int gameId;

    public PointsAdapter(int gameId){
        this.gameId = gameId;
        AppContext c = AppContext.getInstance();
        this.players = c.getGames().get(gameId).getPlayers();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.points_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.player_name.setText(players.get(position).getName());
        holder.points.setText(Integer.toString(players.get(position).getScoreInGame(gameId)));
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView player_name;
        public TextView points;

        public ViewHolder(View itemView) {
            super(itemView);
            player_name = (TextView)itemView.findViewById(R.id.points_name);
            points = (TextView) itemView.findViewById(R.id.points_number);

        }

    }
}
