package com.animrift.app.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AnimeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(AnimeEntity anime);

    @Delete
    void delete(AnimeEntity anime);

    @Query("SELECT * FROM anime_library ORDER BY id DESC")
    List<AnimeEntity> getAll();

    @Query("SELECT * FROM anime_library WHERE favorite = 1")
    List<AnimeEntity> getFavorites();

    @Query("DELETE FROM anime_library")
    void clearAll();
}
