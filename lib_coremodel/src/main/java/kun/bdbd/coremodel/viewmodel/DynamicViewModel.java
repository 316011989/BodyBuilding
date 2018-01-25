package kun.bdbd.coremodel.viewmodel;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import kun.bdbd.coremodel.datamodel.http.entities.DynamicData;
import kun.bdbd.coremodel.datamodel.http.repository.GankDataRepository;
import kun.bdbd.coremodel.util.NetUtils;

/**
 * Created by dxx on 2017/11/20.
 * 动态的url
 */

public class DynamicViewModel extends AndroidViewModel {
    private static final MutableLiveData ABSENT = new MutableLiveData();
    Application application;

    {
        //noinspection unchecked
        ABSENT.setValue(null);
    }

    //生命周期观察的数据
    private LiveData<DynamicData> mLiveObservableData;
    //UI使用可观察的数据 ObservableField是一个包装类
    public ObservableField<DynamicData> uiObservableData = new ObservableField<>();
    public ObservableBoolean refreshing = new ObservableBoolean();

    private final CompositeDisposable mDisposable = new CompositeDisposable();


    public DynamicViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        LoadData("20", "1");
    }


    /**
     *
     */
    private void LoadData(String size, String index) {
        //这里的trigger为网络检测，也可以换成缓存数据是否存在检测
        mLiveObservableData = Transformations.switchMap(NetUtils.netConnected(application), new Function<Boolean, LiveData<DynamicData>>() {
            @Override
            public LiveData<DynamicData> apply(Boolean isNetConnected) {
                Log.i("zhangyl", "apply------>");
                if (!isNetConnected) {
                    return ABSENT; //网络未连接返回空
                }
                refreshing.set(true);
                MutableLiveData<DynamicData> applyData = new MutableLiveData<>();

                GankDataRepository.getDynamicDataRepository(size, index)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<DynamicData>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                mDisposable.add(d);
                            }

                            @Override
                            public void onNext(DynamicData value) {
                                Log.i("zhangyl", "setValue------>");
                                applyData.setValue(value);
                                refreshing.set(false);
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.i("zhangyl", "onError------>");
                                e.printStackTrace();
                                refreshing.set(false);
                            }

                            @Override
                            public void onComplete() {
                                Log.i("danxx", "onComplete------>");
                                refreshing.set(false);
                            }
                        });
                return applyData;
            }
        });
    }

    @BindingAdapter("isrefreshing")
    public static void setRefreshing(SwipeRefreshLayout layout, boolean isRefreshing) {
        if (isRefreshing != layout.isRefreshing()) {
            layout.setRefreshing(isRefreshing);
        }
    }

    @BindingAdapter("onrefresh")
    public static void onRefreshListener(SwipeRefreshLayout layout, SwipeRefreshLayout.OnRefreshListener listener) {
        LogUtils.e("onRefreshListener方法");
        layout.setOnRefreshListener(listener);
    }


    public SwipeRefreshLayout.OnRefreshListener setOnRefreshListener() {
        return new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                LoadData("20", "5");
            }
        };
    }


    /**
     * LiveData支持了lifecycle生命周期检测
     *
     * @return
     */
    public LiveData<DynamicData> getLiveObservableData() {
        return mLiveObservableData;
    }

    /**
     * 设置
     *
     * @param product
     */

    public void setUiObservableData(DynamicData product) {
        this.uiObservableData.set(product);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mDisposable.clear();
    }

}
