package com.audit.health.ssc.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface BatchDao {
    @Query("SELECT * FROM BatchTable")
    List<BatchTable> getAll();

//    @Query("select * from LoginTable where auditor_id=:auditorId")
//    List<LoginTable> getAllAuditorId(String auditorId);
//
//    @Query("delete from LoginTable where auditor_id=:auditorId")
//   void deleteAuditor(String auditorId);
//
//    @Query("select * from LoginTable where user_status=:user_status")
//    List<LoginTable> getDetailByStatus(String user_status);
//
//    @Query("select * from LoginTable where username=:username and password=:password")
//    List<LoginTable> getAuditorDetail(String username,String password);
//
  @Query("UPDATE BatchTable SET status=:setStatusId WHERE batch_id =:batchId")
    void updateStatus(String batchId,String setStatusId);




    @Insert
    void insertAll(List<BatchTable> batchTables);

    @Update
    void update(BatchTable batchTables);

    @Delete
    void delete(BatchTable batchTables);
}

