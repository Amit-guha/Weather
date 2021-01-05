package API;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface JsonPlaceHolderApi {

    ///posts--------------get data from posts
  /*  @GET("posts")
    Call<List<Post>>getpost();*/

    //Query------at end point of the url look ? mark
    @GET("posts")
    Call<List<Post>>getpost(@Query("userId")Integer [] userId,
                            @Query("_sort")String sort,
                            @Query("_order")String order);


    @GET("posts")
    Call<List<Post>>getpost(@QueryMap Map<String,String>map);



    //@GET("posts/1/comments")
    @GET("posts/{id}/comments")
    Call<List<Comment>>getComments(@Path("id") int postId);
}
