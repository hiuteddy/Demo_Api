package hieunnph32561.fpoly.demo_api;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity_Dangky extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword,editreTextPassword;

    private TextView txtsgin,txtphone;
    private Button buttonRegister;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        // Ánh xạ các view
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editreTextPassword = findViewById(R.id.editreTextPassword);
        txtsgin = findViewById(R.id.txtsign);
        txtphone = findViewById(R.id.txtdangkysdt);

        buttonRegister = findViewById(R.id.buttonRegister);

        // Xử lý sự kiện khi người dùng nhấn nút Đăng ký
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
        txtsgin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_Dangky.this, MainActivity_Dangnhap.class);
                startActivity(intent);
            }
        });
        txtphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_Dangky.this, MainActivity_Loginphone.class);
                startActivity(intent);
            }
        });

    }

    // Hàm đăng ký tài khoản
    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String repassword = editreTextPassword.getText().toString().trim();

        if (!password.equals(repassword)) { // Kiểm tra xác nhận mật khẩu
            Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Kiểm tra xem email và password có rỗng không
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Đăng ký tài khoản bằng email và password
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(MainActivity_Dangky.this,MainActivity_Dangnhap.class));
                            // Đăng ký thành công, đăng nhập người dùng vào ứng dụng
                            Toast.makeText(MainActivity_Dangky.this, "Registration successful!", Toast.LENGTH_SHORT).show();
                            // Thực hiện các hành động tiếp theo sau khi đăng ký thành công, ví dụ: chuyển hướng đến màn hình chính
                        } else {
                            // Đăng ký thất bại, hiển thị thông báo lỗi
                            Toast.makeText(MainActivity_Dangky.this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
