package io.guo.demoapplication.utils;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;


public class OnSubscribeQuery implements ObservableOnSubscribe<Cursor> {


    private final ContentResolver contentResolver;
    private final Uri uri;
    private final String[] projection;
    private final String selection;
    private final String[] selectionArgs;
    private final String orderBy;

    OnSubscribeQuery(ContentResolver contentResolver, Uri uri, String[] projection,
                     String selection, String[] selectionArgs, String orderBy) {
        this.contentResolver = contentResolver;
        this.uri = uri;
        this.projection = projection;
        this.selection = selection;
        this.selectionArgs = selectionArgs;
        this.orderBy = orderBy;
    }

    @Override
    public void subscribe(ObservableEmitter<Cursor> e) throws Exception {
        Cursor cursor = contentResolver.query(uri, projection, selection, selectionArgs, orderBy);
        if(!e.isDisposed() && !cursor.moveToFirst()){
            e.onComplete();
            cursor.close();
        }
        if (!e.isDisposed() && cursor.moveToFirst()) {
            do {
                e.onNext(cursor);
            } while (cursor.moveToNext());
        }

        cursor.close();
        if(!e.isDisposed()){
            e.onComplete();
        }
    }
}
