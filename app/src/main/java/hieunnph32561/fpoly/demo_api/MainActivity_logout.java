package hieunnph32561.fpoly.demo_api;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity_logout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_logout);

        TextView textViewLogout = findViewById(R.id.textView);
        textViewLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(MainActivity_logout.this, MainActivity_Dangnhap.class);
                startActivity(intent);
            }
        });
    }

    private void logoutUser() {
        FirebaseAuth.getInstance().signOut();
        // Chuyển đến màn hình đăng nhập hoặc màn hình chính tùy thuộc vào yêu cầu của bạn
        Intent intent = new Intent(MainActivity_logout.this, MainActivity_Dangnhap.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Xóa mọi hoạt động trước đó và đặt màn hình mới là nhiệm vụ chính
        startActivity(intent);
        finish(); // Kết thúc Activity hiện tại
    }
}
