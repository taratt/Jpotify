package ClientPackage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import GUI.Essentials;
import Listeners.*;
import Lists.*;
import Music.Song;
import Serialization.Serializer;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Library implements AddPlaylistListener, ChooseSongListener, ListGUIListener {
    private ArrayList<List> lists;
    private ArrayList<Album> albums;
    private SharedPlaylist sharedPlaylist;
    private FavouriteSongs favouriteSongs;
    private LibrarySong songs;
    private LibraryListenerToPlaylistBar libraryListenerToPlaylistBar;
    private List currentList;
    private LibraryChangeListListener libraryChangeListListener;

    //    private ArrayList<Song> songs;
    public Library(ArrayList<List> lists) {
        if (lists == null) {//1.No such a file 2.Empty file
            this.lists = new ArrayList<>();
            songs = new LibrarySong("Songs", "All songs", imageConverterToByteCode(".\\pics\\Music.png"));
            favouriteSongs = new FavouriteSongs("Favourite Songs", "", imageConverterToByteCode(".\\pics\\Favourite2.png"));
            sharedPlaylist = new SharedPlaylist("Shared playlist", "", imageConverterToByteCode(".\\pics\\Shared.png"));
            this.lists.add(songs);
            this.lists.add(favouriteSongs);
            this.lists.add(sharedPlaylist);
//            Serializer.writeToFile(this.lists,".\\SaveFiles\\saved.bin");

        } else {//restore from file
            songs = (LibrarySong) lists.get(0);
            favouriteSongs = (FavouriteSongs) lists.get(1);
            sharedPlaylist = (SharedPlaylist) lists.get(2);
            for (int i = 3; i < lists.size(); i++)
                this.lists.add(lists.get(i));
        }
        currentList = songs;
        albums = songs.buildAlbums();//EBI: check it!
    }

    public void addList(List list) {
        lists.add(list);
    }

    @Override
    public void makePlaylist(String name, String description, String artworkPath) {

        Playlist playlist = new Playlist(name, description, imageConverterToByteCode(artworkPath));
        if (!lists.contains(playlist)) {
            addList(playlist);
//            Serializer.writeToFile(lists, ".\\SaveFiles\\saved.bin");
            libraryListenerToPlaylistBar.addNewPlaylist(playlist);
        }

    }

    @Override
    public void addSongToLibrary(String songPath) {
        if (!songs.getSongsPaths().contains(songPath)) {
            songs.addSong(songPath);
//            Serializer.writeToFile(lists, ".\\SaveFiles\\saved.bin");
        }
//        System.out.println(songPath);
    }

    public void setLibraryListenerToPlaylistBar(LibraryListenerToPlaylistBar listener) {
        libraryListenerToPlaylistBar = listener;
    }

    private byte[] imageConverterToByteCode(String path) {
        File artworkFile = new File(path);
        byte[] artworkByteCode;
        try {
            artworkByteCode = Files.readAllBytes(artworkFile.toPath());
            return artworkByteCode;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void listClicked(ListType listType, String listName) {
        switch (listType) {
            case LibrarySong://Songs
                currentList = songs;
                break;
            case FavouriteSong://Favourite Songs
                currentList = favouriteSongs;
                break;
            case SharedPlaylist://Shared playlist
                System.out.println(":||||||");
                currentList = sharedPlaylist;
                break;
            case Album://Albums
                //code here
                break;
            case Playlist:
//                System.out.println("~~~~~~~~~");
                if (searchForPlaylist(listName) != null) {
                    currentList = searchForPlaylist(listName);
//                    System.out.println("][][][][][][");
                }
                break;
        }
        libraryChangeListListener.updateCenter(currentList);
    }

    private Playlist searchForPlaylist(String playlistName) {
        System.out.println("PlaylistName:" + playlistName);
        for (List list : lists) {
            System.out.println("N:" + list.getName());
            if (list instanceof Playlist) {
                if ((list).getName().equals(playlistName))
                    if (((Playlist) list).isRemovable())//if removable is true then it's not Fav and Shared playlist.
                    {
//                        System.out.println("000000" + list.getName());
                        return (Playlist) list;
                    }
            }
        }
        return null;
    }

    public void setLibraryChangeListListener(LibraryChangeListListener listener) {
        libraryChangeListListener = listener;
    }

    public void refresh(String path) {
        songs.changeOrder(path, 1);
        Album albumFound = null;
        Album arrayOfAlbum[] = (Album[]) albums.toArray();
        int index = 0;
        for (Album album : albums) {
            if (album.getSongsPaths().contains(path)) {
                albumFound = album;
                index = albums.indexOf(album);
            }
            break;
        }

        for (int i = index; i > 0; i--) {
            arrayOfAlbum[i] = arrayOfAlbum[i - 1];
        }
        albums = new ArrayList<>();
        arrayOfAlbum[0] = albumFound;
        for (int i = 0; i < arrayOfAlbum.length; i++) {
            albums.add(arrayOfAlbum[i]);
        }

    }

    public LibrarySong getSongs() {
        return songs;
    }
}
