package hieunnph32561.fpoly.demo_api;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity_quenmk extends AppCompatActivity {

    private EditText editTextEmail;
    private Button buttonSendResetEmail;
    private EditText editTextConfirmationCode;
    private Button buttonConfirmCode;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_quenmk);

        mAuth = FirebaseAuth.getInstance();

        editTextEmail = findViewById(R.id.editTextEmail);
        buttonSendResetEmail = findViewById(R.id.buttonSendResetEmail);
//        editTextConfirmationCode = findViewById(R.id.editTextConfirmationCode);
//        buttonConfirmCode = findViewById(R.id.buttonConfirmCode);

        buttonSendResetEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendResetEmail();
            }
        });
//
//        buttonConfirmCode.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                confirmCode();
//            }
//        });
    }

    private void sendResetEmail() {
        String email = editTextEmail.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Enter your email address", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    // Xử lý khi gửi email thành công (ví dụ: hiển thị thông báo)
                    Toast.makeText(MainActivity_quenmk.this, "Reset email sent successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity_quenmk.this, MainActivity_Dangnhap.class);
                    startActivity(intent);
                } else {
                    // Xử lý khi gửi email thất bại (ví dụ: hiển thị thông báo lỗi)
                    Toast.makeText(MainActivity_quenmk.this, "Error sending mail: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
//    private void confirmCode() {
//        String confirmationCode = editTextConfirmationCode.getText().toString().trim();
//        if (TextUtils.isEmpty(confirmationCode)) {
//            Toast.makeText(this, "Enter confirmation code", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        String email = mAuth.getCurrentUser().getEmail();
//
//
//        // Lấy mã xác nhận từ người dùng
//        AuthCredential credential = EmailAuthProvider.getCredential(email, confirmationCode);
//
//        // Thực hiện xác thực
//        mAuth.getCurrentUser().reauthenticate(credential)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()) {
//                            // Mã xác nhận đúng, chuyển đến màn hình đặt lại mật khẩu
//                            Intent intent = new Intent(MainActivity_quenmk.this, MainActivity_Dangnhap.class);
//                            startActivity(intent);
//                        } else {
//                            // Mã xác nhận không đúng, hiển thị thông báo
//                            Toast.makeText(MainActivity_quenmk.this, "Invalid confirmation code", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//    }

}
