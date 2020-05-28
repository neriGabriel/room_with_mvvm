package com.example.roomwithmvvm.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import com.example.roomwithmvvm.R;
import com.example.roomwithmvvm.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    //ViewBinding
    private ActivityMainBinding binding;

    //NavController referente ao modelo de navigation graph
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
            Atribuo ao meu binding o inflate da classe ActivityMainBinding passando como parametro o LayoutInflater
        */
        this.binding = ActivityMainBinding.inflate(getLayoutInflater());
        /*
            Atribuo ao ContentView o root gerado acima no binding
        */
        setContentView(this.binding.getRoot());

        /*
            Atribuo ao meu objeto navController o navigation (com a acitivty e o navhost setado no layout dessa activity)
        */
        this.navController = Navigation.findNavController(this, R.id.fragment);
        /*
            'Inicio' a action bar a esse navigation
        */
        NavigationUI.setupActionBarWithNavController(this, this.navController);
    }

    /*
        Método executado quando o navigation graph 'subir'
    */
    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(this.navController, (DrawerLayout) null);
    }

    /*
        Método executado quando o gerenciamento da activity criar a opção no menu
        (processo de setar botões na action bar => criar um layout resource do tipo menu, criar o itens)
    */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

}
