package com.rhmt.testandroid.model;

import android.util.Log;

import com.rhmt.testandroid.api.ApiClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class MainViewModel extends ViewModel {
    private MutableLiveData<ArrayList<ListUser>> listMutableLiveData = new MutableLiveData<>();

    public void getData(){
        final ArrayList<ListUser> listItemUsers = new ArrayList<>();
        GetEndpointUser getEndpointUser = ApiClient.getClient().create(GetEndpointUser.class);
        Call<ResponseBody> call = getEndpointUser.getUsers();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String result = response.body().string();
                    JSONArray jsonArray = new JSONArray(result);

                    for (int i = 0; i< jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                            int user_id = jsonObject.getInt("userId");
                            int id = jsonObject.getInt("id");
                            String title = jsonObject.getString("title");
                            Boolean completed = jsonObject.getBoolean("completed");

                        ListUser listUser = new ListUser(user_id, id, title, completed);
                        listItemUsers.add(listUser);
                    }
                    listMutableLiveData.postValue(listItemUsers);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("XXX", "onFailure: "+t.getMessage());
            }
        });

    }

    public void getDataId(final int id){
        final ArrayList<ListUser> listItemUsers = new ArrayList<>();
        GetEndpointDetailUser getEndpointDetailUser = ApiClient.getClient().create(GetEndpointDetailUser.class);
        Call<ResponseBody> call = getEndpointDetailUser.getDetailUser(id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String result = response.body().string();
                    JSONObject jsonArray = new JSONObject(result);

                    for (int i = 0; i< jsonArray.length(); i++){

                            int user_id = jsonArray.getInt("userId");
                            int id = jsonArray.getInt("id");
                            String title = jsonArray.getString("title");
                            Boolean completed = jsonArray.getBoolean("completed");
                        ListUser listUser = new ListUser(user_id, id, title, completed);
                        listItemUsers.add(listUser);
                    }
                    listMutableLiveData.postValue(listItemUsers);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("XXX", "onFailure: "+t.getMessage());
            }
        });
    }

    public LiveData<ArrayList<ListUser>> getAllData(){
        return listMutableLiveData;
    }


    interface GetEndpointUser{
        @GET("todos")
        Call<ResponseBody> getUsers();
    }

    interface GetEndpointDetailUser{
        @GET("todos/{id}")
        Call<ResponseBody> getDetailUser(@Path("id") int id);
    }

}
