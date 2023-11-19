package app;

import data_access.SpotifyDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.search_album.SearchAlbumController;
import interface_adapter.search_album.SearchAlbumPresenter;
import interface_adapter.search_album.SearchAlbumViewModel;
import use_case.search.SearchDataAccessInterface;
import use_case.search.search_album.SearchAlbumInputBoundary;
import use_case.search.search_album.SearchAlbumInteractor;
import use_case.search.search_album.SearchAlbumOutputBoundary;
import use_case.search.search_album.SearchAlbumOutputData;
import view.SearchView;


public class SearchAlbumUseCaseFactory {
    private SearchAlbumUseCaseFactory() {}

    public static SearchView create(ViewManagerModel viewManagerModel,
                                    SearchAlbumViewModel searchAlbumViewModel,
                                    SearchDataAccessInterface dataAccessInterface) {

        SearchAlbumOutputBoundary outputBoundary = new SearchAlbumPresenter(searchAlbumViewModel);
        SearchAlbumInputBoundary inputBoundary = new SearchAlbumInteractor(dataAccessInterface, outputBoundary);
        SearchAlbumController controller = new SearchAlbumController(inputBoundary);
        return new SearchView(controller, searchAlbumViewModel);
    }
}