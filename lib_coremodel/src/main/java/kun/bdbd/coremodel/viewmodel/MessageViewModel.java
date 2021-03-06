package kun.bdbd.coremodel.viewmodel;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.apkfuns.logutils.LogUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import kun.bdbd.coremodel.datamodel.http.entities.MessageData;
import kun.bdbd.coremodel.datamodel.http.entities.MessageData;
import kun.bdbd.coremodel.datamodel.http.repository.GankDataRepository;
import kun.bdbd.coremodel.util.NetUtils;

/**
 * Created by dxx on 2017/11/20.
 * 动态的url
 */

public class MessageViewModel extends AndroidViewModel {
    private static final MutableLiveData ABSENT = new MutableLiveData();
    Application application;

    {
        //noinspection unchecked
        ABSENT.setValue(null);
    }

    //生命周期观察的数据，可使uiObservableData变更
    private LiveData<MessageData> mLiveObservableData;
    //UI使用可观察的数据 ObservableField是一个包装类，可使UI变更
    public ObservableField<MessageData> uiObservableData = new ObservableField<>();
    //后台调度，可关闭
    private final CompositeDisposable mDisposable = new CompositeDisposable();
    //动态数据，请求获取，可使mLiveObservableData变更
    private MutableLiveData<MessageData> applyData = new MutableLiveData<>();
    private int count = 20;
    private int index = 1;

    public MessageViewModel(@NonNull Application app) {
        super(app);
        this.application = app;
        mLiveObservableData = Transformations.switchMap(NetUtils.netConnected(application), new Function<Boolean, LiveData<MessageData>>() {
            @Override
            public LiveData<MessageData> apply(Boolean isNetConnected) {
                if (!isNetConnected) {
                    Toast.makeText(application, "网络异常", Toast.LENGTH_SHORT).show();
                    return ABSENT;
                }
                LoadData();
                return applyData;
            }
        });
    }


    /**
     * 请求数据接口
     */
    private void LoadData() {
        GankDataRepository.getMessageDataRepository(count, index)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MessageData>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable.add(d);
                    }

                    @Override
                    public void onNext(MessageData value) {
                        LogUtils.e(value);
                        applyData.setValue(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e(e);
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.e("onComplete");
                        index++;
                    }
                });
    }



    /**
     * LiveData支持了lifecycle生命周期检测
     *
     * @return
     */
    public LiveData<MessageData> getLiveObservableData() {
        return mLiveObservableData;
    }

    /**
     * 设置
     *
     * @param product
     */

    public void setUiObservableData(MessageData product) {
        this.uiObservableData.set(product);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mDisposable.clear();
    }

}
