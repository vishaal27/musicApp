package MusicAppTester;

import MusicApp.Lab7;
import MusicApp.MusicPlaylistParser;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TestSearchSong
{
    @Test
    public void testSearch() throws IOException
    {
        MusicPlaylistParser playlist= Lab7.deserialize("Playlist1.ser");
        String toCheck=Lab7.search(playlist,"thug");
        String toValidate="No song of given name exists.";
        assertEquals(toCheck, toValidate);

        toCheck=Lab7.search(playlist, "Hello");
        toValidate="Song name: Hello ,Singer: Adele ,Duration: 243 seconds.";
        assertEquals(toCheck, toValidate);
    }
}
