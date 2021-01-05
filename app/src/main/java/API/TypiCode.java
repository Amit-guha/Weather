package API;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.jsonparsing.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TypiCode extends AppCompatActivity {

    private TextView tvResult;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_typi_code);

        tvResult = findViewById(R.id.tvresult);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        getPost();
       // getComment();

    }

    private void getComment() {
        Call<List<Comment>> call = jsonPlaceHolderApi.getComments(3);
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (!response.isSuccessful()) {
                 tvResult.setText("Code : "+response.code());
                }
                List<Comment>comments=response.body();
                for(Comment comment:comments){
                    String content="";
                    content+="postId :"+comment.getPostId()+"\n";
                    content+="id :"+comment.getId()+"\n";
                    content+="name :"+comment.getName()+"\n";
                    content+="email :"+comment.getEmail()+"\n";
                    content+="body :"+comment.getText()+"\n\n";

                    tvResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                tvResult.setText(t.getMessage());
            }
        });
    }

    private void getPost() {
        Map<String,String>map=new HashMap<>();
        map.put("userId","2");
        map.put("_sort","id");
        map.put("_order","desc");

        //new Integer[]{2,3,6},"id","desc"
        Call<List<Post>> call = jsonPlaceHolderApi.getpost(map);

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    tvResult.setText(response.code());
                    return;
                }

                List<Post> posts = response.body();
                for (Post post : posts) {
                    String content = "";
                    content += "userId :" + post.getUserId() + "\n";
                    content += "id :" + post.getId() + "\n";
                    content += "title :" + post.getTitle() + "\n";
                    content += "text :" + post.getText() + "\n\n";

                    tvResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

                tvResult.setText(t.getMessage());
            }
        });
    }
}