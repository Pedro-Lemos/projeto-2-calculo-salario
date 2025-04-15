package br.com.fecapccp.app2calculosalario;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button btnCalcular;
    private TextView txtResultado;
    private RadioGroup rgCalculoPercentual;
    private RadioButton rb40, rb45, rb50;
    private TextView txtSalario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnCalcular = findViewById(R.id.btnCalcular);
        txtResultado = findViewById(R.id.txtResultado);
        txtSalario = findViewById(R.id.editTxtSalario);
        rgCalculoPercentual = findViewById(R.id.rgCalculoPercentual);
        rb40 = findViewById(R.id.rb40);
        rb45 = findViewById(R.id.rb45);
        rb50 = findViewById(R.id.rb50);

        btnCalcular.setOnClickListener(v -> {
            String txtSalarioValor = txtSalario.getText().toString();
            double percentual = radioGroup();

            if (txtSalarioValor.isEmpty()) {
                txtResultado.setText("Digite um valor válido!");
                return;
            } else if (percentual == -1) {
                txtResultado.setText("Selecione um percentual!");
                return;
            }

            double salario = calcularSalario(txtSalarioValor, percentual);
            txtResultado.setText(String.format("O novo salário com o aumento percentual é: %.2f", salario));

        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private double calcularSalario(String txtSalario, double percentualAumento) {
        double salario = Double.parseDouble(txtSalario);

        return salario * (percentualAumento / 100 + 1);
    }

    private double radioGroup(){
        int checkedId = rgCalculoPercentual.getCheckedRadioButtonId();
        if (checkedId == R.id.rb40) {
            return 40;
        } else if (checkedId == R.id.rb45) {
            return 45;
        } else if (checkedId == R.id.rb50) {
            return 50;
        } else {
            return -1; // Caso o usuário não selecione nenhuma opcão
        }
    }
}