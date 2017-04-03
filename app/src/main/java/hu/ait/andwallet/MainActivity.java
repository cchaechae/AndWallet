package hu.ait.andwallet;

import android.content.Intent;
import android.graphics.Interpolator;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.layoutDefine)
    LinearLayout layoutDefine;

    @BindView(R.id.etName)
    EditText editName;

    @BindView(R.id.etValue)
    EditText editValue;

    @BindView(R.id.btSave)
    Button btSave;

    boolean ifexpense;
    static int balance;
    static int numincome;
    static int numexpense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        ButterKnife.bind(layoutDefine);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);

        numincome = 0;
        numexpense = 0;
        balance = 0;

        final ToggleButton toggle = (ToggleButton) findViewById(R.id.btIncome);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked){
                    //expense
                    ifexpense = true;
                }

                else{
                    ifexpense = false;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void summaryClicked(MenuItem mi) {
        // handle click here
        startActivity(new Intent(MainActivity.this, summaryActivity.class));

    }

    public void deleteClicked(MenuItem mi){

        layoutDefine.removeAllViews();
    }


    @OnClick(R.id.btSave)
    public void pressed(Button btn){
        View viewDefine = getLayoutInflater().inflate(R.layout.layout_define, null, false);

        TextView tvTitle =  ((TextView) viewDefine.findViewById(R.id.tvTitle));
        TextView tvPrice =  ((TextView) viewDefine.findViewById(R.id.tvPrice));
        ImageView ivIcon = ((ImageView) viewDefine.findViewById(R.id.ivIcon));
        TextView tvBalance = ((TextView) viewDefine.findViewById(R.id.tvBalance));

        Drawable brownpig = getResources().getDrawable(R.drawable.brownpig);
        Drawable pinkpig = getResources().getDrawable(R.drawable.pinkpig);

        if (!getValue().equals("")) {

            try {

                if (!getMoney().equals("")) {

                    if (ifexpense) {
                        ivIcon.setImageDrawable(brownpig);
                    } else {
                        ivIcon.setImageDrawable(pinkpig);
                    }
                    int value = Integer.parseInt(getValue());
                    setBalance(value);

                    tvTitle.setText(getMoney());
                    tvPrice.setText(getValue());
                    tvBalance.setText(Integer.toString(balance));

                    layoutDefine.addView(viewDefine, 0); //will always add to the first
                }
            }

            catch (NumberFormatException e) {
                editValue.setError("This must be an integer number!");
            }
        }

        else{
            editName.setError("This field should not be empty!");
        }

    }


    private String getMoney(){

        return editName.getText().toString();
    }

    private String getValue(){

        return editValue.getText().toString();
    }

    private void setBalance(int n){

        if (ifexpense){
            balance -= n;
            numexpense += n;
            System.out.println("expense" + numexpense);
        }
        else{
            balance += n;
            numincome += n;
            System.out.println("income" + numincome);
        }

    }

    public static int getNumincome() {
        return numincome;
    }

    public static int getNumexpense() {
        return numexpense;
    }

    public static int getBalance() {
        return balance;
    }
}
