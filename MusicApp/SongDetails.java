package MusicApp;

import java.io.Serializable;

public class SongDetails implements Serializable
{
    private String songName;
    private String singerName;
    private int duration;
    private static final long serialVersionUID=10023463;

    public SongDetails(String songName, String singerName, int duration)
    {
        this.songName=songName;
        this.singerName=singerName;
        this.duration=duration;
    }

    public int getDuration()
    {
        return this.duration;
    }

    public String getSingerName()
    {
        return this.singerName;
    }

    public String getSongName()
    {
        return this.songName;
    }
}