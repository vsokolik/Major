package ru.testapp.major.di;


import javax.inject.Singleton;

import dagger.Component;
import ru.testapp.major.app.MajorApp;
import ru.testapp.major.di.modules.ClientModule;
import ru.testapp.major.ui.activities.MainActivity;

@Singleton
@Component(modules = {ClientModule.class})
public interface AppComponent {

    void inject(MajorApp majorApp);

    void inject(MainActivity mainActivity);

}
