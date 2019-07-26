import com.example.myapp.Article;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class FireDatabaseHelper {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mreferencesArticles;
    private List<Article> articles= new ArrayList<>();
    public FireDatabaseHelper(){
        mDatabase=FirebaseDatabase.getInstance();
        mreferencesArticles=mDatabase.getReference("Article");
    }
}
