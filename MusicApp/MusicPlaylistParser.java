package MusicApp;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;

public class MusicPlaylistParser implements Serializable
{
    private ArrayList<SongDetails> playlist;
    private static final long serialVersionUID=102323459;

    public MusicPlaylistParser()
    {
        playlist=new ArrayList<SongDetails>();
    }

    public ArrayList<SongDetails> getPlaylist() throws FileNotFoundException
    {
        return this.playlist;
    }

    public void setPlaylist(ArrayList<SongDetails> playlist)
    {
        this.playlist=playlist;
    }
}