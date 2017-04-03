package io.guo.demoapplication.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import io.reactivex.Observable;

public class LocalMusicManager {

    static final String[] PROJECTION_AUDIO = new String[]{MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE, MediaStore.Audio.Media.DATA, MediaStore.Audio.Media
            .DURATION, MediaStore.Audio.Media.ALBUM, MediaStore.Audio.Media.ARTIST, MediaStore
            .Audio.Media.ALBUM_ID, MediaStore.Audio.Media.MIME_TYPE, MediaStore.Audio.Media.SIZE,
            MediaStore.Audio.Media.DATE_ADDED};

    private final ContentResolver resolver;
    private static LocalMusicManager instance;

    private LocalMusicManager(Context context) {
        resolver = context.getContentResolver();
    }

    public static LocalMusicManager getInstance(Context context) {
        if (instance == null) {
            instance = new LocalMusicManager(context);
        }
        return instance;
    }

    public Cursor discoverSongs(){
        String sortOrder = "title ASC";
        return resolver
                .query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, PROJECTION_AUDIO,
                        null, null, sortOrder);
    }

    public Observable<Cursor> discoverSongsObservable(){
        String sortOrder = "title ASC";

        return Observable.create(new OnSubscribeQuery(resolver, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, PROJECTION_AUDIO,
                null, null, sortOrder));
    }

}
