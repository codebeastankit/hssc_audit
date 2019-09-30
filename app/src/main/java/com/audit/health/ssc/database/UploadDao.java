package com.audit.health.ssc.database;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface UploadDao {
    @Query("SELECT * FROM UploadTable")
    List<UploadTable> getAll();

    @Query("select * from UploadTable where position=:post")
      List<UploadTable> getAllImage(int post);

    @Query("delete from UploadTable where batch_id=:bachId")
     void deleteUpload(String bachId);

//    @Query("delete from uploadtable")
//     void delettable();
//
//    @Query("select * from LoginTable where user_status=:user_status")
//    List<LoginTable> getDetailByStatus(String user_status);
//
//    @Query("select * from LoginTable where username=:username and password=:password")
//    List<LoginTable> getAuditorDetail(String username,String password);
//
//  @Query("UPDATE UploadTable SET status=:status WHERE questId =:positionId")
//    void uploadPic(String positionId,String status);




    @Insert
    void insertAll(UploadTable loginTable);

    @Update
    void update(UploadTable loginTable);

    @Delete
    void delete(UploadTable loginTable);
}
