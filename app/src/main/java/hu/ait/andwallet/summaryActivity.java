package hu.ait.andwallet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import hu.ait.andwallet.MainActivity;
import butterknife.BindView;

public class summaryActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        TextView tvIncome =  ((TextView) findViewById(R.id.finalIncome));
        TextView tvExpense = ((TextView) findViewById(R.id.finalExpense));
        TextView tvBalance = ((TextView) findViewById(R.id.finalBalance));

        String incomeS = getResources().getString(R.string.txtIncome, MainActivity.getNumincome());
        String expenseS = getResources().getString(R.string.txtExpense, MainActivity.getNumexpense());
        String balanceS = getResources().getString(R.string.txtBalance, MainActivity.getBalance());

        tvIncome.setText(incomeS);
        tvExpense.setText(expenseS);
        tvBalance.setText(balanceS);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_summary, menu);
        return true;
    }

    public void gobackClicked(MenuItem mi){

        startActivity(new Intent(summaryActivity.this, MainActivity.class));

    }

}
