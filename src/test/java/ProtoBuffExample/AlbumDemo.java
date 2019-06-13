package ProtoBuffExample;

import com.google.protobuf.InvalidProtocolBufferException;
import generatedGoogleFormatMessages.AlbumProtos;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;

/**
 * Demonstrates using Protocol Buffers with Album.
 */
public class AlbumDemo {
    /**
     * Generates instance of Album to be used in demonstration.
     *
     * @return Instance of Album to be used in demonstration.
     */
    private Album generateAlbum() {
        return new Album.Builder("Songs from the Big Chair", 1985)
                .artist("Tears For Fears")
                .artist("Someone else")
                .songTitle("Shout")
                .songTitle("The Working Hour")
                .songTitle("Everybody Wants to Rule the World")
                .songTitle("Mothers Talk")
                .songTitle("I Believe")
                .songTitle("Broken")
                .songTitle("Head Over Heels")
                .songTitle("Listen")
                .build();
    }

    /**
     * For practice use data entered by user
     *
     * @return new album
     */
    public Album enterCostomiseAlbum(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Album title.");
        String aTitle = scan.nextLine();
        System.out.println("Enter thr year");
        int year = Integer.parseInt(scan.nextLine());
        System.out.println("Enter Autors");
        String autors = scan.nextLine();
        System.out.println("Enter songs");
        String songs = scan.nextLine();

        return new Album.Builder(aTitle, year)
                .artist(autors)
                .songTitle(songs)
                .build();
    }

    /**
     * Generates an instance of Album based on the provided
     * bytes array.
     *
     * @param binaryAlbum Bytes array that should represent an
     *                    AlbumProtos.Album based on Google Protocol Buffers
     *                    binary format.
     * @return Instance of Album based on the provided binary form
     * of an Album; may be {@code null} if an error is encountered
     * while trying to process the provided binary data.
     */
    private Album instantiateAlbumFromBinary(final byte[] binaryAlbum) {
        Album album = null;
        try {
            final AlbumProtos.Album copiedAlbumProtos = AlbumProtos.Album.parseFrom(binaryAlbum);
            final List<String> copiedArtists = copiedAlbumProtos.getArtistList();
            final List<String> copiedSongsTitles = copiedAlbumProtos.getSongTitleList();
            album = new Album.Builder(
                    copiedAlbumProtos.getTitle(), copiedAlbumProtos.getReleaseYear())
                    .artists(copiedArtists)
                    .songsTitles(copiedSongsTitles)
                    .build();
        } catch (InvalidProtocolBufferException ipbe) {
            out.println("ERROR: Unable to instantiate AlbumProtos.Album instance from provided binary data - "
                    + ipbe);
        }
        return album;
    }

    /**
     * Demonstrates use of Google Protocol Buffers to write an
     * {@code Album} to a Google Protocol Buffers's binary form
     * and to instantiate an {@code Album} from that binary form.
     *
     * @param arguments Command-line arguments: none expected.
     */
    public static void main(final String[] arguments) {
        final AlbumDemo instance = new AlbumDemo();
        Album album = instance.generateAlbum();
        //album = instance.enterCostomiseAlbum();
        final AlbumProtos.Album albumMessage
                = AlbumProtos.Album.newBuilder()
                .setTitle(album.getTitle())
                .addAllArtist(album.getArtists())
                .setReleaseYear(album.getReleaseYear())
                .addAllSongTitle(album.getSongsTitles())
                .build();
        final byte[] binaryAlbum = albumMessage.toByteArray();
        final Album copiedAlbum = instance.instantiateAlbumFromBinary(binaryAlbum);
        out.println("Binary array: "+Arrays.toString(binaryAlbum));
        out.println("BEFORE Album's hash code (" + System.identityHashCode(album) + "): " + album);
        out.println(" AFTER Album's hash code (" + System.identityHashCode(copiedAlbum) + "): " + copiedAlbum);
    }

    /**
     * Music album, example application for demonstration of Protocol Format
     */
    public static class Album {
        private final String title;
        private final List < String > artists;
        private final int releaseYear;
        private final List < String > songsTitles;
        private Album(final String newTitle, final List < String > newArtists,
                      final int newYear, final List < String > newSongsTitles) {
            title = newTitle;
            artists = newArtists;
            releaseYear = newYear;
            songsTitles = newSongsTitles;
        }
        public String getTitle() {
            return title;
        }
        public List < String > getArtists() {
            return artists;
        }
        public int getReleaseYear() {
            return releaseYear;
        }
        public List < String > getSongsTitles() {
            return songsTitles;
        }
        @Override
        public String toString() {
            return "'" + title + "' (" + releaseYear + ") by " + artists + " features songs " + songsTitles;
        }
        /**
         * Builder class for instantiating an instance of
         * enclosing Album class.
         */
        public static class Builder {
            private String title;
            private ArrayList< String > artists = new ArrayList<String>();
            private int releaseYear;
            private ArrayList < String > songsTitles = new ArrayList<String>();
            public Builder(final String newTitle, final int newReleaseYear) {
                title = newTitle;
                releaseYear = newReleaseYear;
            }
            public Builder songTitle(final String newSongTitle) {
                songsTitles.add(newSongTitle);
                return this;
            }
            public Builder songsTitles(final List < String > newSongsTitles) {
                songsTitles.addAll(newSongsTitles);
                return this;
            }
            public Builder artist(final String newArtist) {
                artists.add(newArtist);
                return this;
            }
            public Builder artists(final List < String > newArtists) {
                artists.addAll(newArtists);
                return this;
            }
            public Album build() {
                return new Album(title, artists, releaseYear, songsTitles);
            }
        }
    }
}