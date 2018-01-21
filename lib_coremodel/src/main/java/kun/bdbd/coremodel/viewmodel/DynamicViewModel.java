package kun.bdbd.coremodel.viewmodel;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import kun.bdbd.coremodel.datamodel.http.entities.DynamicData;
import kun.bdbd.coremodel.datamodel.http.entities.VideoData;
import kun.bdbd.coremodel.datamodel.http.repository.GankDataRepository;
import kun.bdbd.coremodel.util.NetUtils;

/**
 * Created by dxx on 2017/11/20.
 * 动态的url
 */

public class DynamicViewModel extends AndroidViewModel {
    private static final MutableLiveData ABSENT = new MutableLiveData();

    {
        //noinspection unchecked
        ABSENT.setValue(null);
    }

    //生命周期观察的数据
    private LiveData<DynamicData> mLiveObservableData;
    //UI使用可观察的数据 ObservableField是一个包装类
    public ObservableField<DynamicData> uiObservableData = new ObservableField<>();

    private final CompositeDisposable mDisposable = new CompositeDisposable();


    public DynamicViewModel(@NonNull Application application) {
        super(application);
        //这里的trigger为网络检测，也可以换成缓存数据是否存在检测
        mLiveObservableData = Transformations.switchMap(NetUtils.netConnected(application), new Function<Boolean, LiveData<DynamicData>>() {
            @Override
            public LiveData<DynamicData> apply(Boolean isNetConnected) {
                Log.i("danxx", "apply------>");
                if (!isNetConnected) {
                    return ABSENT; //网络未连接返回空
                }
                MutableLiveData<DynamicData> applyData = new MutableLiveData<>();

                GankDataRepository.getDynamicDataRepository("20", "1")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<DynamicData>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                mDisposable.add(d);
                            }

                            @Override
                            public void onNext(DynamicData value) {
                                Log.i("danxx", "setValue------>");
                                applyData.setValue(value);
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.i("danxx", "onError------>");
                                e.printStackTrace();
                            }

                            @Override
                            public void onComplete() {
                                Log.i("danxx", "onComplete------>");
                            }
                        });
                return applyData;
            }
        });
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
