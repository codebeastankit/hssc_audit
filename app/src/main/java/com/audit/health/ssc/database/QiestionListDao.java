package com.audit.health.ssc.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface QiestionListDao {
    @Query("SELECT * FROM QuestionListTable")
    List<QuestionListTable> getAll();

    @Query("delete from QuestionListTable where batchId=:bachId")
    void deleteQuestionList(String bachId);



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
//  @Query("UPDATE LoginTable SET user_status=:setStatusId WHERE auditor_id =:auditorId")
//    void updateStatus(String auditorId,String setStatusId);




    @Insert
    void insertAll(List<QuestionListTable> questionListTable);

    @Update
    void update(QuestionListTable questionListTable);

    @Delete
    void delete(QuestionListTable questionListTable);
}
