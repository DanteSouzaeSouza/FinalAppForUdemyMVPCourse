package br.com.theoldpinkeye.finalappforudemymvpcourse.topmovies;

import io.reactivex.Observable;

/**
 * Created by Just Us on 29/11/2017.
 */

public interface TopMoviesActivityMVP { //Conventional name is the main activity name + MVP
    interface View {
        void updateData(ViewModel viewModel); //tells the RecyclerView there is a new object to be added to the object list
        void showSnackbar(String s); //used to display messages to the user
    }

    interface Presenter {
        void loadData();
        void rxDispose(); //Updated to RxJava2 terminology
        void setView(TopMoviesActivityMVP.View view);
    }

    interface Model {
        Observable<ViewModel> result();
    }
}
