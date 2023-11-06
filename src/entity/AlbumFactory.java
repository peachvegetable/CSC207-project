package entity;

public interface AlbumFactory {
    Album create(String id,
                 String name,
                 String releaseDate,
                 String[] genres,
                 Artist[] artists);
}
