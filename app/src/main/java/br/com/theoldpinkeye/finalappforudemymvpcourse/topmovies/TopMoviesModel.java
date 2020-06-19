package br.com.theoldpinkeye.finalappforudemymvpcourse.topmovies;


import br.com.theoldpinkeye.finalappforudemymvpcourse.http.apimodel.Result;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;

/**
 * Created by Just Us on 01/12/2017.
 */

public class TopMoviesModel implements TopMoviesActivityMVP.Model {
    private Repository repository; //Reminder: the View only communicates with the Presenter. The presenter only communicates with the model, and the Model communicates with the Repository

    public TopMoviesModel(Repository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<ViewModel> result() { //replaced Func2 with BiFunction according to RxJava2 specification
        return Observable.zip(repository.getResultData(), repository.getCountryData(),
            (result, s) -> new ViewModel(result.title, s));
    }
}
