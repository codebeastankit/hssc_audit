package com.audit.health.ssc.webservices;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import com.audit.health.ssc.BuildConfig;
import com.audit.health.ssc.database.BatchTable;
import com.audit.health.ssc.database.LoginTable;
import com.audit.health.ssc.database.QuestionListTable;
import com.audit.health.ssc.webservices.request.CredRequest;
import com.audit.health.ssc.webservices.request.UploadRequest;
import com.audit.health.ssc.webservices.response.LoginResponse;
import com.audit.health.ssc.webservices.response.Questionare;
import com.audit.health.ssc.webservices.response.UploadResopnse;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiInterface {


//    @POST("login/auditor/{version}")
//    Call<LoginResponse> loginUser(@Path("version") String postfix,@Body CredRequest credRequest);

    @GET
    Call<LoginTable> loginUser(@Url String url);
     @GET
    Call<List<BatchTable>> batchUser(@Url String url);
    @GET
    Call<List<QuestionListTable>> questionUser(@Url String url);

    @POST("equipment/lab_specification")
    Call<UploadResopnse> uploadJobroles(@Body JsonObject credRequest);


    @POST("audit/questionsave")
    Call<UploadResopnse> uploadData(@Body JsonArray credRequest);

    @POST("equipment/save")
    Call<UploadResopnse> uploadEquipment(@Body JsonArray credRequest);

//    uploadData(@Body List<UploadRequest> friendModel, Callback<List<UploadResopnse>> response);

    @GET("audit/question/{auditId}")
    Call<JsonObject> questionare(@Path("auditId") String auditId);

    @Multipart
    @POST("audit/save_image")
    Call<ResponseBody> uploadMultiple(
            @Part("auditor_id") RequestBody auditor_id,
            @Part("audit_id") RequestBody audit_id,
            @Part("question_id") RequestBody question_id,
            @Part List<MultipartBody.Part> files);

}
