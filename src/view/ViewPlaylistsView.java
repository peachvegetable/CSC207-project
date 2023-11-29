package view;

import app.GUI_View_Playlists;
import interface_adapter.create_playlist.CreatePlaylistController;
import interface_adapter.delete_playlist.DeletePlaylistController;
import interface_adapter.view_playlists.ViewPlaylistsViewModel;
import interface_adapter.view_playlists.ViewPlaylistsController;
import interface_adapter.view_song.ViewSongController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.Vector;

public class ViewPlaylistsView extends JPanel implements PropertyChangeListener, ActionListener {
    private final ViewPlaylistsViewModel viewModel;
    private final ViewPlaylistsController playlistsController;
    private final ViewSongController viewSongController;
    private final DeletePlaylistController deletePlaylistController;

    private final JList<String> playlistsList;
    private final JButton refreshButton;
    private final JButton createPlaylistButton;
    private final JButton deletePlaylistButton;

    public ViewPlaylistsView(ViewPlaylistsViewModel viewModel,
                             ViewPlaylistsController playlistsController,
                             ViewSongController viewSongController,
                             DeletePlaylistController deletePlaylistController) {
        this.viewModel = viewModel;
        this.playlistsController = playlistsController;
        this.viewSongController = viewSongController;
        this.deletePlaylistController = deletePlaylistController;

        this.playlistsList = new JList<>();
        this.refreshButton = new JButton("Refresh Playlists");
        this.createPlaylistButton = new JButton("Create Playlist");
        this.deletePlaylistButton = new JButton("Delete Playlist");

        viewModel.addPropertyChangeListener(this);
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());
        add(new JScrollPane(playlistsList), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(createPlaylistButton);
        buttonPanel.add(deletePlaylistButton);
        buttonPanel.add(refreshButton);
        add(buttonPanel, BorderLayout.SOUTH);

        createPlaylistButton.addActionListener(this);
        deletePlaylistButton.addActionListener(this);
        refreshButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createPlaylistButton){
            GUI_View_Playlists.switchToCreateView();
        }
        if (e.getSource() == deletePlaylistButton) {
            String selectedPlaylist = playlistsList.getSelectedValue();
            if (selectedPlaylist != null && !selectedPlaylist.isEmpty()) {
                try {
                    deletePlaylistController.deletePlaylist(selectedPlaylist);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a playlist to delete.", "No Selection", JOptionPane.WARNING_MESSAGE);
            }
        } else if (e.getSource() == refreshButton) {
            try {
                playlistsController.execute("Alice"); // Example username
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            updatePlaylistsList(viewModel.getState().getPlaylists());
        }
    }

    private void updatePlaylistsList(java.util.List<String> playlists) {
        playlistsList.setListData(new Vector<>(playlists));
    }
}
