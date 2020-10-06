package com.test.dojoincodetest.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.test.dojoincodetest.di.base.BaseViewModel
import com.test.dojoincodetest.model.Category
import com.test.dojoincodetest.model.CategoryBase
import com.test.dojoincodetest.model.Listing
import com.test.dojoincodetest.util.NetworkState
import com.test.dojoincodetest.network.AnsarApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Inject

class MainViewModel : BaseViewModel()  {

    @Inject
    lateinit var ansarApi: AnsarApi

    var networkExecutor: Executor? = null
    val subscription = CompositeDisposable()
    val repoResult = MutableLiveData<Listing<Category>>()
    val categories = Transformations.switchMap(repoResult) { it.categoryList }
    val networkStateMain = Transformations.switchMap(repoResult) { it.networkState }

    private var retry: (() -> Any)? = null
    val networkState = MutableLiveData<NetworkState>()
    val categoriesDataList = MutableLiveData<List<Category>>()

    init {
        repoResult.postValue(artistsData())
    }

    fun retryAllFailed() {
        val prevRetry = retry
        retry = null
        prevRetry?.let {
            networkExecutor?.execute{
                it.invoke()
            }
        }
    }

    private fun artistsData(): Listing<Category> {

        networkExecutor = Executors.newFixedThreadPool(5)

        subscription.add(ansarApi.getCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                networkState.postValue(NetworkState.LOADING)
            }
            .doOnTerminate {
                retry = null
                networkState.postValue(NetworkState.LOADED)
            }
            .subscribe(
                {
                        categories -> onRetrieveCategoriesListSuccess(categories)
                },
                {
                    retry = {
                        artistsData()
                    }
                    val errors = NetworkState.error(it.message ?: "unknown error")
                    networkState.postValue(errors)
                }
            ))


        return Listing(
            categoryList = categoriesDataList,
            networkState = networkState,
            retry = {
                retryAllFailed()
            }
        )

    }

    var categoriesList: List<Category>? = null
    private fun onRetrieveCategoriesListSuccess(
        category: CategoryBase
    ) {
        try {
            categoriesList = category.category
            categoriesDataList.postValue(categoriesList)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun retry() {
        val listing = repoResult.value
        listing?.retry?.invoke()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

}