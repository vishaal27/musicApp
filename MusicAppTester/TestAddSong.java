package MusicAppTester;

import MusicApp.Lab7;
import MusicApp.MusicPlaylistParser;
import org.junit.Test;
import java.io.IOException;
import java.util.ArrayList;

import static junit.framework.TestCase.*;

public class TestAddSong
{
    @Test
    public void testAdd() throws IOException
    {
        MusicPlaylistParser playlist= Lab7.deserialize("Playlist1.ser");
        Lab7.add(playlist, "Bolna Aunty Aau Kya", "Omprakash Mishra", 143);
        ArrayList<String> songs=Lab7.show(playlist);
        ArrayList<String> songsToValidate1=new ArrayList<String>();

        songsToValidate1.add("Perfect,Ed Sheeran,240");
        songsToValidate1.add("Photograph,Ed Sheeran,225");
        songsToValidate1.add("One More Light,Linkin Park,320");
        songsToValidate1.add("Hello,Adele,243");
        songsToValidate1.add("Bolna Aunty Aau Kya,Omprakash Mishra,143");
        assertEquals(songs, songsToValidate1);

        Lab7.add(playlist, "Despacito", "Luis Fonsi", 345);
        ArrayList<String> songsToValidate2=new ArrayList<String>();
        songs=Lab7.show(playlist);
        songsToValidate2.add("Perfect,Ed Sheeran,240");
        songsToValidate2.add("Photograph,Ed Sheeran,225");
        songsToValidate2.add("One More Light,Linkin Park,320");
        songsToValidate2.add("Hello,Adele,243");
        songsToValidate2.add("Bolna Aunty Aau Kya,Omprakash Mishra,143");
        songsToValidate2.add("Despacito,Luis Fonsi,345");

        assertEquals(songs, songsToValidate2);
    }
}
