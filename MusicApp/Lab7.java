package MusicApp;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;


class Reader
{
    static BufferedReader reader;
    static StringTokenizer tokenizer;

    static void init(InputStream input) {
        reader = new BufferedReader(
                new InputStreamReader(input) );
        tokenizer = new StringTokenizer("");
    }


    static String next() throws IOException {
        while ( ! tokenizer.hasMoreTokens() ) {

            tokenizer = new StringTokenizer(
                    reader.readLine() );
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt( next() );
    }

    static double nextDouble() throws IOException {
        return Double.parseDouble( next() );
    }
}

public class Lab7
{
    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        System.out.println("All the playlists available are:");
        System.out.println();
        showAllPlaylists();
        System.out.println("Enter the playlist name to play:");
        Reader.init(System.in);
        String playlist=Reader.next();
        MusicPlaylistParser musicPlaylistParser=new MusicPlaylistParser();

        musicPlaylistParser.setPlaylist(deserialize(playlist+".ser").getPlaylist());
        System.out.println("There are "+musicPlaylistParser.getPlaylist().size()+" songs in your playlist.");
        String opt="Y";
        int ch;
        do
        {
            System.out.println("Menu for playlist-"+playlist);
            System.out.println("1. Add a song");
            System.out.println("2. Delete a song");
            System.out.println("3. Search for a song");
            System.out.println("4. Show all songs");
            System.out.println("5. Back to Menu");
            System.out.println("6. Exit");
            System.out.println("Enter your choice:");
            ch=Reader.nextInt();

            switch(ch)
            {
                case 1: System.out.println("Enter name of the song, singer and duration of song(each in a newline).");
                        String addSongName=Reader.reader.readLine();
                        String addSingerName=Reader.reader.readLine();
                        int addDuration=Integer.parseInt(Reader.next());
                        add(musicPlaylistParser, addSongName, addSingerName, addDuration);
                        System.out.println("There are "+musicPlaylistParser.getPlaylist().size()+" songs in your playlist.");
                        break;

                case 2: System.out.println("Enter name of song to delete.");
                        String toDelete=Reader.reader.readLine();
                        int f=delete(musicPlaylistParser, toDelete);
                        if(f==0)
                            System.out.println("No song of given name exists.");
                        else
                            System.out.println("There are "+musicPlaylistParser.getPlaylist().size()+" songs in your playlist.");
                        break;

                case 3: System.out.println("Enter name of song to search.");
                        String toSearch=Reader.reader.readLine();
                        System.out.println(search(musicPlaylistParser, toSearch));
                        break;

                case 4: if(!(show(musicPlaylistParser)==null))
                        {
                            for(String s: show(musicPlaylistParser))
                            {
                                int positionOfFirstComma=s.indexOf(",");
                                String songName=s.substring(0,positionOfFirstComma);
                                int positionOfSecondComma=s.indexOf(",", positionOfFirstComma+1);
                                String singerName=s.substring(positionOfFirstComma+1, positionOfSecondComma);
                                String duration=s.substring(positionOfSecondComma+1, s.length());
                                System.out.println("Song name: "+songName+",Singer name: "+singerName+",Duration: "+duration+" seconds.");
                            }
                        }
                        break;

                case 5: System.out.println("All the playlists available are:");
                        System.out.println();
                        showAllPlaylists();
                        System.out.println("Enter the playlist name to play:");
                        playlist=Reader.next();
                        musicPlaylistParser.setPlaylist(deserialize(playlist+".ser").getPlaylist());
                        System.out.println("There are "+musicPlaylistParser.getPlaylist().size()+" songs in your playlist.");
                        break;

                case 6: System.out.println("Exiting program...");
                        serialize(musicPlaylistParser, playlist+".ser");
                        System.exit(0);
            }

            System.out.println("Do you want to continue?(y/n)");
            opt=Reader.next();

        }while(opt.equals("Y")||opt.equals("y"));
        serialize(musicPlaylistParser, playlist+".ser");
    }

    public static void add(MusicPlaylistParser musicPlaylistParser, String song, String singer, int duration) throws IOException
    {
        musicPlaylistParser.getPlaylist().add(new SongDetails(song, singer, duration));
    }

    public static int delete(MusicPlaylistParser musicPlaylistParser, String toDelete) throws IOException
    {
        int f=0;
        for(SongDetails s: musicPlaylistParser.getPlaylist())
        {
            if(toDelete.equals(s.getSongName()))
            {
                f=1;
                musicPlaylistParser.getPlaylist().remove(s);
                break;
            }
        }
        return f;
    }

    public static String search(MusicPlaylistParser musicPlaylistParser, String toSearch) throws IOException
    {
        int f=0;
        int index=0;
        for(int i=0;i<musicPlaylistParser.getPlaylist().size();i++)
        {

            if(toSearch.equals(musicPlaylistParser.getPlaylist().get(i).getSongName()))
            {
                f=1;
                index=i;
                break;
            }
        }

        if(f==0)
            return "No song of given name exists.";
        else
            return "Song name: "+musicPlaylistParser.getPlaylist().get(index).getSongName()+" ,Singer: "+musicPlaylistParser.getPlaylist().get(index).getSingerName()+" ,Duration: "+musicPlaylistParser.getPlaylist().get(index).getDuration()+" seconds.";
    }

    public static ArrayList<String> show(MusicPlaylistParser musicPlaylistParser) throws FileNotFoundException
    {
        if(musicPlaylistParser.getPlaylist().size()<=0)
        {
            return null;
        }
        else
        {
            ArrayList<String> returnList=new ArrayList<String>();
            for(SongDetails s: musicPlaylistParser.getPlaylist())
            {
                returnList.add(s.getSongName()+","+s.getSingerName()+","+s.getDuration());
            }
            return returnList;
        }
    }

    public static void showAllPlaylists()
    {
        String[] paths=(new	File(".")).list();

        for(String	path:	paths)
        {
            if(path.endsWith(".ser"))
            {
                System.out.println(path.substring(0, path.indexOf(".")));
            }
        }
    }

    public static void serialize(MusicPlaylistParser playlist, String file)
    {
        ObjectOutputStream out=null;
        try
        {
            out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(playlist);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                out.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static MusicPlaylistParser deserialize(String file)
    {
        ObjectInputStream in=null;
        MusicPlaylistParser playlist=null;
        try
        {
            in=new ObjectInputStream(new FileInputStream(file));
            playlist=(MusicPlaylistParser) in.readObject();
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                in.close();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }

        return playlist;
    }
}

