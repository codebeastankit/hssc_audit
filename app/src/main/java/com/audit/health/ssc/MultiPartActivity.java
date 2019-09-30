//package com.audit.health.ssc;
//
//import android.support.annotation.NonNull;
//import android.support.design.widget.Snackbar;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.widget.Toast;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import com.audit.health.ssc.webservices.ApiClient;
//import okhttp3.MultipartBody;
//import okhttp3.RequestBody;
//import okhttp3.ResponseBody;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class MultiPartActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_multi_part);
//
//
//        uploadImagesToServer();
//
//
//    }
//    private void uploadImagesToServer() {
//
//
//            // create list of file parts (photo, video, ...)
//            List<MultipartBody.Part> parts = new ArrayList<>();
//
//            // create upload service client
//            ApiClient service = ApiClient.getClient().create(ApiClient.class);
//
//            if (arrayList != null) {
//                // create part for file (photo, video, ...)
//                for (int i = 0; i < arrayList.size(); i++) {
//                    parts.add(prepareFilePart("image"+i, arrayList.get(i)));
//                }
//            }
//
//            // create a map of data to pass along
//            RequestBody description = createPartFromString("www.androidlearning.com");
//            RequestBody size = createPartFromString(""+parts.size());
//
//            // finally, execute the request
//            Call<ResponseBody> call = service(description, size, parts);
//
//            call.enqueue(new Callback<ResponseBody>() {
//                @Override
//                public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
//                    hideProgress();
//                    if(response.isSuccessful()) {
//                        Toast.makeText(MainActivity.this,
//                                "Images successfully uploaded!", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Snackbar.make(parentView, R.string.string_some_thing_wrong, Snackbar.LENGTH_LONG).show();
//                    }
//                }
//
//                @Override
//                public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
//                    hideProgress();
//                    Snackbar.make(parentView, t.getMessage(), Snackbar.LENGTH_LONG).show();
//                }
//            });
//
//
//    }
//
//}
