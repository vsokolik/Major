package ru.testapp.major.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import ru.testapp.major.utils.Const;

@Module
public class RetrofitModule {

    public RetrofitModule() {}

	@Provides
	@Singleton
	public Retrofit provideRetrofit(Retrofit.Builder builder) {
		HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
		OkHttpClient client = new OkHttpClient.Builder().addInterceptor(logging).build();
		return builder.baseUrl(Const.BASE_API_URL).client(client).build();
	}

	@Provides
	@Singleton
	public Retrofit.Builder provideRetrofitBuilder() {
		return new Retrofit.Builder()
				.addConverterFactory(ScalarsConverterFactory.create());
	}
}
