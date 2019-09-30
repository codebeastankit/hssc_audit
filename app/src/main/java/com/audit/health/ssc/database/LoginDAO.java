package com.audit.health.ssc.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface LoginDAO {
    @Query("SELECT * FROM LoginTable")
    List<LoginTable> getAll();

    @Query("select * from LoginTable where username=:username")
    List<LoginTable> getAuditorUsername(String username);
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
//  @Query("UPDATE LoginTable SET user_status=:setStatusId WHERE auditor_id =:auditorId")
//    void updateStatus(String auditorId,String setStatusId);




    @Insert
    void insertAll(List<LoginTable> loginTable);

    @Update
    void update(LoginTable loginTable);

    @Delete
    void delete(LoginTable loginTable);
}
