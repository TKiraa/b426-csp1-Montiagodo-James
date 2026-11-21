package com.joysistvi.recordingapp;

import com.joysistvi.recordingapp.config.DbConnection;
import com.joysistvi.recordingapp.repository.*;
import com.joysistvi.recordingapp.service.*;
import com.joysistvi.recordingapp.view.AlbumView;
import com.joysistvi.recordingapp.view.ArtistView;
import com.joysistvi.recordingapp.view.UserView;

import java.util.Scanner;

// Entry Point
public class App {

    public static void main(String[] args) {
        DbConnection dbConnection = new DbConnection();
        Scanner scanner = new Scanner(System.in);
        // Wiring
        ArtistRepository artistRepository = new ArtistRepositoryImpl(dbConnection);
        ArtistService artistService = new ArtistServiceImpl(artistRepository);
        ArtistView artistView = new ArtistView(artistService, scanner);

        AlbumRepository albumRepository = new AlbumRepositoryImpl(dbConnection);
        AlbumService albumService = new AlbumServiceImpl(albumRepository);
        AlbumView albumView = new AlbumView(albumService, scanner);

        UserRepository userRepository = new UserRepositoryImpl(dbConnection);
        UserService userService = new UserServiceImpl(userRepository);
        UserView userView = new UserView(userService, scanner);


        // Artist flow: Read, Create, Search, Update, Archive, Restore, Delete
//        artistView.displayAllArtists();
//        artistView.promptCreateArtist();
//        artistView.promptSearchArtists();
//        artistView.promptUpdateArtist();
//        artistView.promptArchiveArtist();
//        artistView.promptRestoreArtist();
//        artistView.promptDeleteArtist();
//
//        // Album flow: Read, Create, Search, Update, Archive, Restore, Delete
        albumView.displayAllAlbums();
//        albumView.promptCreateAlbum();
//        albumView.promptSearchAlbums();
//        albumView.promptUpdateAlbum();
//        albumView.promptArchiveAlbum();
//        albumView.promptRestoreAlbum();
//        albumView.promptDeleteAlbum();
//
//        // User flow: Register/Create, Login, Read, Search, Update, Archive, Restore, Delete
//        userView.promptCreateUser();
//        userView.promptLogin();
        userView.displayAllUsers();
//        userView.promptSearchUsers();
//        userView.promptUpdateUser();
//        userView.promptArchiveUser();
//        userView.promptRestoreUser();
//        userView.promptDeleteUser();

        scanner.close();
    }
}



/*
// APPLICATION FLOW
    Repository
     │
     ▼
    Service
     │
     ▼
    View
     │
     ▼
    Main Application


Dependency Wiring:
Repository --> Service --> View --> main()

Example:
ArtistRepositoryImpl
        │
        ▼
ArtistServiceImpl
        │
        ▼
ArtistView
        │
        ▼
App.main()
 */
