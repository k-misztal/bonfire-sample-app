package pl.misztal.template.di.component;

import android.content.Context;
import android.view.LayoutInflater;

import dagger.Component;
import pl.misztal.template.ExceptionHandler;
import pl.misztal.template.di.scope.ActivitySingleton;
import pl.misztal.template.location.LocationProvider;
import pl.misztal.template.model.DataManager;

/**
 * Created by kmisztal on 10.06.2017.
 *
 * @author Krzysztof Misztal
 */
@ActivitySingleton
@Component(dependencies = AppComponent.class)
public interface ActivityComponent {
    DataManager dataManager();

    Context context();

    ExceptionHandler exceptionHandler();

    LayoutInflater layoutInflater();

    LocationProvider locationProvider();
}
