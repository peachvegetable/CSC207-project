package data_access;


// These two imports are for accessing albums through the Spotify API

import entity.album.IAlbumFull;
import entity.album.AlbumSimple;

import entity.artist.IArtistFull;
import entity.artist.IArtistSimple;
import entity.song.ISongFull;
import entity.song.ISongSimple;

import spotify.SpotifyEndpoint;
import spotify.models.AlbumSimpleModel;
import use_case.search.SearchDataAccessInterface;
import use_case.get_by_id.GetByIdDataAccessInterface;

import java.util.List;

/**
 * A DataAccessObject used to get data from the Spotify API
 */
public class SpotifyDataAccessObject implements SearchDataAccessInterface, GetByIdDataAccessInterface {
    private final SpotifyEndpoint spotifyApi;

    public SpotifyDataAccessObject(SpotifyEndpoint spotifyApi) {
        this.spotifyApi = spotifyApi;
    }


    @Override
    public List<AlbumSimple> searchAlbumsByName(String albumName) {
        List<AlbumSimpleModel> spotifyAlbums = this.spotifyApi.requestSearchAlbum(albumName);
        return spotifyAlbums.stream().map(AlbumSimple::new).toList();
    }

    @Override
    public List<IArtistSimple> searchArtistByName(String artistName) {
        return List.of();
    }

    @Override
    public List<ISongSimple> searchSongByName(String songName) {
        return List.of();
    }

    @Override
    public IAlbumFull getAlbumById(String albumId) {
        return null;
    }

    @Override
    public IArtistFull getArtistById(String artistId) {
        return null;
    }

    @Override
    public ISongFull getSongById(String songId) {
        return null;
    }
}
