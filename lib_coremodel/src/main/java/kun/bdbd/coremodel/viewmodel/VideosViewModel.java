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

import kun.bdbd.coremodel.datamodel.http.entities.VideoData;
import kun.bdbd.coremodel.datamodel.http.repository.GankDataRepository;
import kun.bdbd.coremodel.util.NetUtils;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 保存生命周期和UI所使用的数据
 * Created by dxx on 2017/11/10.
 */

public class VideosViewModel extends AndroidViewModel {

    private static final MutableLiveData ABSENT = new MutableLiveData();
    {
        //noinspection unchecked
        ABSENT.setValue(null);
    }

    //生命周期观察的数据
    private LiveData<VideoData> mLiveObservableData;
    //UI使用可观察的数据 ObservableField是一个包装类
    public ObservableField<VideoData> uiObservableData = new ObservableField<>();

    private final CompositeDisposable mDisposable = new CompositeDisposable();

    public VideosViewModel(@NonNull Application application) {
        super(application);
        Log.i("danxx", "VideosViewModel------>");
        //这里的trigger为网络检测，也可以换成缓存数据是否存在检测
        mLiveObservableData = Transformations.switchMap(NetUtils.netConnected(application), new Function<Boolean, LiveData<VideoData>>() {
            @Override
            public LiveData<VideoData> apply(Boolean isNetConnected) {
                Log.i("danxx", "apply------>");
                if (!isNetConnected) {
                    return ABSENT; //网络未连接返回空
                }
                MutableLiveData<VideoData> applyData = new MutableLiveData<>();

                GankDataRepository.getFuliDataRepository("20", "1")
                .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<VideoData>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                    mDisposable.add(d);
                            }

                            @Override
                            public void onNext(VideoData value) {
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
     * @return
     */
    public LiveData<VideoData> getLiveObservableData() {
        return mLiveObservableData;
    }

    /**
     * 设置
     * @param product
     */
    public void setUiObservableData(VideoData product) {
        this.uiObservableData.set(product);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mDisposable.clear();
    }
}
