package ru.testapp.major.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.testapp.major.app.Api;
import ru.testapp.major.app.MajorService;

@Module(includes = {ApiModule.class})
public class ClientModule {
	@Provides
	@Singleton
	public MajorService provideClientService(Api api) {
		return new MajorService(api);
	}
}