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
import kun.bdbd.coremodel.datamodel.http.entities.PublishData;
import kun.bdbd.coremodel.datamodel.http.repository.GankDataRepository;
import kun.bdbd.coremodel.util.NetUtils;

/**
 * Created by zhangyl1 on 2018/01/23.
 */

public class PublishViewModel extends AndroidViewModel {
    private static final MutableLiveData ABSENT = new MutableLiveData();

    {
        //noinspection unchecked
        ABSENT.setValue(null);
    }

    MutableLiveData<PublishData> applyData = new MutableLiveData<>();
    //生命周期观察的数据
    private LiveData<PublishData> mLiveObservableData;
    //UI使用可观察的数据 ObservableField是一个包装类
    public ObservableField<PublishData> uiObservableData = new ObservableField<>();


    private final CompositeDisposable mDisposable = new CompositeDisposable();

    public PublishViewModel(@NonNull Application application) {
        super(application);
        //这里的trigger为网络检测，也可以换成缓存数据是否存在检测

        mLiveObservableData = Transformations.switchMap(NetUtils.netConnected(application), new Function<Boolean, LiveData<PublishData>>() {
            @Override
            public LiveData<PublishData> apply(Boolean isNetConnected) {
                Log.i("zhangyl", "apply------>");
                if (!isNetConnected) {
                    return ABSENT; //网络未连接返回空
                }

                GankDataRepository.getPublishDataRepository("20", "1")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<PublishData>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                mDisposable.add(d);
                            }

                            @Override
                            public void onNext(PublishData value) {
                                Log.i("zhangyl", "setValue------>");
                                applyData.setValue(value);
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.i("zhangyl", "onError------>");
                                e.printStackTrace();
                            }

                            @Override
                            public void onComplete() {
                                Log.i("zhangyl", "onComplete------>");
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
    public LiveData<PublishData> getLiveObservableData() {
        return mLiveObservableData;
    }

    /**
     * 设置
     *
     * @param product
     */
    public void setUiObservableData(PublishData product) {
        this.uiObservableData.set(product);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mDisposable.clear();
    }
}
