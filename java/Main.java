package com.example.paramedics.myrxjava.NodeJS;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.paramedics.myrxjava.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.adapter.rxjava2.Result;

public class Main extends AppCompatActivity {

    @BindView(R.id.button)
    Button button;
    @BindView(R.id.button1)
    Button button1;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.button4)
    Button button4;
    @BindView(R.id.button5)
    Button button5;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.editTextpass)
    EditText editTextpass;
    @BindView(R.id.editTextid)
    EditText editTextid;

    private ApiInterface apiInterface;
    private Single<Data> observableGetUsers;
    private Single<Data> observableGetUsersField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // bind the view using butterknife
        ButterKnife.bind(this); // obliger de faire ca au lieu de setOnClickListener

        apiInterface = Utils.getApiInterface();
    }


    @OnClick(R.id.button)
    public void onButtonClick(View view) {
        Log.d("onClick : ", "You have clicked ");
        insertUser();
    }

    @OnClick(R.id.button1)
    public void onButtonClick1(View view) {
        Log.d("onClick : ", "You have clicked ");
        myGetUsers();
    }

    @OnClick(R.id.button2)
    public void onButtonClick2(View view) {
        Log.d("onClick : ", "You have clicked ");
        myGetUserByField();
    }


    @OnClick(R.id.button4)
    public void onButtonClick4(View view) {
        Log.d("onClick : ", "You have clicked ");
        myDeleteUser();
    }

    @OnClick(R.id.button5)
    public void onButtonClick5(View view) {
        Log.d("onClick : ", "You have clicked ");
        myUpdateUser();
    }

    private void insertUser() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Inserting...");
        progressDialog.show();

        String name = editText.getText().toString();
        int age = Integer.parseInt(editTextpass.getText().toString());

        User user = new User(name, age);

        Call<Result> call = apiInterface.insertUser(
                user.getName(),
                user.getAge()
        );

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();

                Log.d("testing", "success");
                Log.d("testing Response ", String.valueOf(response.message())); // on success affiche OK

            }

            @Override
            public void onFailure(Call<Result> call, Throwable throwable) {
                progressDialog.dismiss();
                Log.d("testing", "onFailure");
                Log.d("testing", throwable.getMessage());
            }
        });
    }


    public void myGetUsers() {
        observableGetUsers = apiInterface.getUsers();
        observableGetUsers
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(t -> Log.d("testing getU", String.valueOf(t)),
                        throwable -> throwable.printStackTrace());
    }


    public void myGetUserByField() {
        int tmp = Integer.parseInt(editTextpass.getText().toString());
        observableGetUsersField = apiInterface.getUserByField(tmp);
        observableGetUsersField
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(t -> Log.d("testing getU", String.valueOf(t)),
                        throwable -> throwable.printStackTrace());
    }


    public void myDeleteUser() {
        int tmp = Integer.parseInt(editTextpass.getText().toString());

        Call<Result> call = apiInterface.deleteUser(tmp);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Log.d("testing", "success");
                Log.d("testing Response ", String.valueOf(response.body()));
            }

            @Override
            public void onFailure(Call<Result> call, Throwable throwable) {
                Log.d("testing", "onFailure");
                Log.d("testing", throwable.getMessage());
            }
        });
    }


    public void myUpdateUser() {
        int tmpid = Integer.parseInt(editTextid.getText().toString());
        int tmp = Integer.parseInt(editTextpass.getText().toString());
        String tmp2 = editText.getText().toString();
        Call<Result> call = apiInterface.updateUser(tmpid, tmp2, tmp);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Log.d("testing", "success");
                Log.d("testing Response ", String.valueOf(response.body()));
            }

            @Override
            public void onFailure(Call<Result> call, Throwable throwable) {
                Log.d("testing", "onFailure");
                Log.d("testing", throwable.getMessage());
            }
        });
    }
}
