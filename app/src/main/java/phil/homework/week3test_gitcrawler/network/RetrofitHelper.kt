package phil.homework.week3test_gitcrawler.network

import android.content.Context
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import phil.homework.week3test_gitcrawler.model.repo.Repository
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

class RetrofitHelper(val context: Context) {

    private val baseUrl = "https://api.github.com/"

    private val okHttpClient = OkHttpClient.Builder().build()

    private fun createClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private fun createService(): RetrofitService {
        return createClient().create(RetrofitService::class.java)
    }

    fun getUserRepoListObservable(user: String, callback: (List<Repository>) -> Unit){
        createService()
            .getReposByUser(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                callback.invoke(it)
            }
    }

    interface RetrofitService{
        @GET("users/{user}/repos")
        fun getReposByUser(@Path("user") user: String): Observable<List<Repository>>
    }

}