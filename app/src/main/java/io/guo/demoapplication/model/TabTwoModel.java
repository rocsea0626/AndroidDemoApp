package io.guo.demoapplication.model;

import java.util.List;

import io.guo.demoapplication.data.AudioInfo;
import io.reactivex.Observable;

public interface TabTwoModel {
    List<AudioInfo> dicoverLocalSongs();

    Observable<AudioInfo> dicoverLocalSongsObservable();

}
