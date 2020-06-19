package br.com.theoldpinkeye.finalappforudemymvpcourse.topmovies;

import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


import javax.inject.Inject;

import br.com.theoldpinkeye.finalappforudemymvpcourse.R;
import br.com.theoldpinkeye.finalappforudemymvpcourse.root.App;
import butterknife.BindView;
import butterknife.ButterKnife;


public class TopMoviesActivity extends AppCompatActivity implements TopMoviesActivityMVP.View {


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.listActivity_rootView)
    ViewGroup rootView;

    @Inject
    TopMoviesActivityMVP.Presenter presenter;

    private final String TAG = TopMoviesActivity.class.getName();
    private ListAdapter listAdapter;
    private List<ViewModel> resultList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_movies);

        ((App) getApplication()).getComponent().inject(this);

        ButterKnife.bind(this);

        listAdapter = new ListAdapter(resultList);
        recyclerView.setAdapter(listAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this));

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.setView(this);
        presenter.loadData();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.rxDispose();
        resultList.clear();
        listAdapter.notifyDataSetChanged();


    }

    @Override
    public void updateData(ViewModel viewModel) {  //Updates RecyclerView
        resultList.add(viewModel);
        int itemToInsert = resultList.isEmpty() ? 0 : resultList.size()-1;
        listAdapter.notifyItemInserted(itemToInsert);
        Log.d(TAG, "updateData: " + resultList.size());

    }

    @Override
    public void showSnackbar(String msg) {
        Snackbar.make(rootView, msg, Snackbar.LENGTH_LONG).show();
    }
}

