package at.tugraz.mobileapps.tournamentplanner;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by fiona on 11.06.15.
 */
public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.ViewHolder> {
    private static final String TAG = "PlayerAdapter";

    private ArrayList<Player> players;
    private PlayerAdapter counterpartAdapter;

    public PlayerAdapter(ArrayList<Player> players) {
        this.players = players;
    }

    public PlayerAdapter(Context c, String filename) {
        // read from JSON
        try {
            InputStream in = c.getAssets().open(filename);
            this.players = readJsonStream(in);
            Collections.sort(this.players);
        } catch (Exception e) {
            e.printStackTrace();
            this.players = new ArrayList<>();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PlayerAdapter.ViewHolder holder, int position) {
        holder.playerName.setText(players.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public void setCounterpartAdapter(PlayerAdapter counterpartAdapter) {
        this.counterpartAdapter = counterpartAdapter;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView playerName;

        public ViewHolder(View itemView) {
            super(itemView);
            playerName = (TextView)itemView.findViewById(R.id.playerName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (counterpartAdapter != null) {
                counterpartAdapter.addPlayer(players.get(getLayoutPosition()));
                players.remove(getLayoutPosition());
                notifyDataSetChanged();
            }
            Log.d(TAG, "on click " + playerName.getText());
        }
    }

    public void addPlayer(Player player) {
        players.add(player);
        Collections.sort(players);
        notifyDataSetChanged();
    }

    private ArrayList<Player> readJsonStream(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            return readMessagesArray(reader);
        }
        finally{
            reader.close();
        }
    }


    private ArrayList<Player> readMessagesArray(JsonReader reader) throws IOException {
        ArrayList<Player> players = new ArrayList();

        reader.beginArray();
        while (reader.hasNext()) {
            players.add(readPlayer(reader));
        }
        reader.endArray();
        return players;
    }

    private Player readPlayer(JsonReader reader) throws IOException {
        String playerName = null;

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("name")) {
                playerName = reader.nextString();
            }
            else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new Player(playerName);
    }
}
