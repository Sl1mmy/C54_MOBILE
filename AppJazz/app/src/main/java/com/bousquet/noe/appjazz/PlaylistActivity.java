package com.bousquet.noe.appjazz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.HashMap;
import java.util.Vector;

public class PlaylistActivity extends AppCompatActivity {

    Playlist playlist1, playlist2, playlist3, playlist4, playlist5, playlist6, playlist7, playlist8;
    Playlist[] playlists;
    String pickedPlaylist;
    SimpleAdapter simpleAdapter;
    Vector<HashMap<String, Object>> hmPlaylists;
    ListView list;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        playlist1 = new Playlist(R.drawable.playlist1, "Jazz Instrumental Classics", "misc.",
                "How does one write something catchy to describe a set of strandards?",
                "63 songs", "6h37m", "spotify:playlist:1dHJUpqHMRbGN3kpMJDw0T");

        playlist2 = new Playlist(R.drawable.playlist2, "Chet Baker Sings", "Chet Baker",
                "The Prince of Cool",
                "14 songs", "44m21s", "spotify:album:5JJ779nrbHx0KB2lBrMMa4");

        playlist3 = new Playlist(R.drawable.playlist3, "Kind of Blue", "Miles Davis",
                "among the most influential and acclaimed figures in the history of jazz",
                "5 songs", "45m45s", "spotify:album:1weenld61qoidwYuZ1GESA");

        playlist4 = new Playlist(R.drawable.playlist4, "Smooth Jazz Piano", "misc.",
                "Whiskey and Jazz",
                "114 songs", "8h39", "spotify:playlist:4Gn6rgEi6D1kbXIn5qKHxV");

        playlist5 = new Playlist(R.drawable.playlist5, "Ghost Song", "Cécile McLorin Salvant",
                "Modern Jazz is good too",
                "12 songs", "46m6s", "spotify:album:69TYdq2UOhXr1OpIN9kdwJ");

        playlist6 = new Playlist(R.drawable.playlist6, "Ella and Louis Again", "Ella Fitzgerald, Louis Armstrong",
                "The combination of these two voices is magic",
                "19 songs", "1h29", "spotify:album:4zWqQOob980K9drUrUGM8M");

        playlist7 = new Playlist(R.drawable.playlist7, "Seychelles", "Masayoshi Takanaka",
                "A masterpiece of Japanese fusion Jazz",
                "8 songs", "40m21s", "spotify:album:7BQPfd39YTObQGamGhDF7g");

        playlist8 = new Playlist(R.drawable.playlist8, "The Sound of Avant-Garde Jazz", "misc.",
                "Pushing Jazz beyond the traditional",
                "260 songs", "30h17", "spotify:playlist:72YNCrIywbtPaFgFSQNI6X");

        playlists = new Playlist[]{playlist1, playlist2, playlist3, playlist4, playlist5, playlist6, playlist7,playlist8};

        hmPlaylists = new Vector<>();
        for (Playlist playlist:playlists) {
            hmPlaylists.add(
                    new HashMap(){{
                            put("cover",playlist.cover);
                            put("name", playlist.name);
                            put("artist", playlist.artist);
                            put("description", playlist.description);
                            put("nbSongs", playlist.nbSongs);
                            put("duration", playlist.duration);
                            put("link", playlist.link);
                    }}
            );
        }

        simpleAdapter = new SimpleAdapter(
                this,
                hmPlaylists,
                R.layout.layout_playlist,
                new String[]{"cover", "name", "artist", "description", "nbSongs", "duration"},
                new int[]{R.id.cover, R.id.nameText, R.id.artistText, R.id.descriptionText, R.id.tracksText, R.id.durationText}
        );

        list = findViewById(R.id.list);
        list.setAdapter(simpleAdapter);
        list.setOnItemClickListener((adapterView, view, pos, l) -> {
            intent = new Intent();
            pickedPlaylist = (String) hmPlaylists.get(pos).get("link");
            intent.putExtra("link", pickedPlaylist);
            setResult(RESULT_OK, intent);
            finish();
        });

    }
}