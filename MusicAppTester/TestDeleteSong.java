package MusicAppTester;

import MusicApp.Lab7;
import MusicApp.MusicPlaylistParser;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

public class TestDeleteSong
{
    @Test
    public void testDelete() throws IOException
    {
        MusicPlaylistParser playlist= Lab7.deserialize("Playlist2.ser");
        Lab7.delete(playlist, "Heavy");
        ArrayList<String> songs=Lab7.show(playlist);
        ArrayList<String> songsToValidate=new ArrayList<String>();

        songsToValidate.add("Way too Good At Goodbyes,Sam Smith,301");
        songsToValidate.add("Your Song,Rita Ora,320");
        songsToValidate.add("Silence,Khaled,423");
        songsToValidate.add("Thunder,Imagine Dragons,234");
        assertEquals(songs, songsToValidate);

        songsToValidate.clear();

        Lab7.delete(playlist, "Your Song");
        songs=Lab7.show(playlist);
        songsToValidate.add("Way too Good At Goodbyes,Sam Smith,301");
        songsToValidate.add("Silence,Khaled,423");
        songsToValidate.add("Thunder,Imagine Dragons,234");
        assertEquals(songs, songsToValidate);
    }
}
