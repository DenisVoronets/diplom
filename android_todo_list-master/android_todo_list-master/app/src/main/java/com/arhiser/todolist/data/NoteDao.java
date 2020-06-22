package com.arhiser.todolist.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.arhiser.todolist.model.MyUserData;
import com.arhiser.todolist.model.Note;

import java.util.List;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM Note")
    List<Note> getAll();

    @Query("SELECT * FROM Note WHERE tag = :myTag")
    List<Note> getTag(String myTag);

    @Query("SELECT * FROM Note")
    LiveData<List<Note>> getAllLiveData();

    @Query("SELECT * FROM Note WHERE uid IN (:noteIds)")
    List<Note> loadAllByIds(int[] noteIds);

    @Query("SELECT * FROM Note WHERE uid = :uid LIMIT 1")
    Note findById(int uid);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("SELECT * FROM MyUserData WHERE uid IN (:personalsIds)")
    List<MyUserData> loadAllByIdsPersonal(int[] personalsIds);

    @Query("SELECT * FROM MyUserData WHERE uid = :uid LIMIT 1")
    MyUserData findByIdPersonal(int uid);

    @Query("SELECT * FROM MyUserData")
    List<MyUserData> getAllPepsonal();

    @Query("SELECT * FROM MyUserData WHERE mail = :myEmail")
    List<MyUserData> getEmail(String myEmail);

    @Query("SELECT * FROM MyUserData WHERE password = :myPassword")
    List<MyUserData> getPassword(String myPassword);

    @Query("SELECT * FROM MyUserData")
    LiveData<List<MyUserData>> getAllLiveDataPersonal();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MyUserData personalData);

    @Update
    void update(MyUserData personalData);

    @Delete
    void delete(MyUserData personalData);

}
