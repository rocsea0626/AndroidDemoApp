package io.guo.demoapplication.model;

import android.database.Cursor;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

import io.guo.demoapplication.data.AudioInfo;
import io.guo.demoapplication.utils.LocalMusicManager;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import timber.log.Timber;

public class TabTwoModelImpl implements TabTwoModel {

    private LocalMusicManager localMusicManager;

    public TabTwoModelImpl(LocalMusicManager localMusicManager) {
        this.localMusicManager = localMusicManager;
    }

    @Override
    public List<AudioInfo> dicoverLocalSongs() {

        Cursor cursor = localMusicManager.discoverSongs();
        List<AudioInfo> list = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                long size = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE));
                int id = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
                String uri = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                long duration = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media
                        .DURATION));
                String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media
                        .ARTIST));
                String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media
                        .TITLE));

                AudioInfo audioInfo = new AudioInfo(id, size, uri, duration, artist, title);
                Timber.d("audioInfo=%s", audioInfo);
                list.add(audioInfo);
            } while (cursor.moveToNext());
            Timber.d("song discovery completed, cursor.getCount()=%d", cursor.getCount());
        }
        return list;
    }

    @Override
    public Observable<AudioInfo> dicoverLocalSongsObservable() {

        return localMusicManager.discoverSongsObservable().map(new Function<Cursor, AudioInfo>() {
            @Override
            public AudioInfo apply(Cursor cursor) throws Exception {
                long size = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE));
                int id = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
                String uri = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                long duration = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media
                        .DURATION));
                String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media
                        .ARTIST));
                String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media
                        .TITLE));

                return new AudioInfo(id, size, uri, duration, artist, title);
            }
        });
    }
}
