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
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import kun.bdbd.coremodel.datamodel.http.entities.GroupData;
import kun.bdbd.coremodel.datamodel.http.repository.GankDataRepository;
import kun.bdbd.coremodel.util.NetUtils;

/**
 * Created by dxx on 2017/11/20.
 * 动态的url
 */

public class GroupViewModel extends AndroidViewModel {
    private static final MutableLiveData ABSENT = new MutableLiveData();
    Application application;

    {
        //noinspection unchecked
        ABSENT.setValue(null);
    }

    //生命周期观察的数据，可使uiObservableData变更
    private LiveData<GroupData> mLiveObservableData;
    //UI使用可观察的数据 ObservableField是一个包装类，可使UI变更
    public ObservableField<GroupData> uiObservableData = new ObservableField<>();
    //后台调度，可关闭
    private final CompositeDisposable mDisposable = new CompositeDisposable();
    //动态数据，请求获取，可使mLiveObservableData变更
    private MutableLiveData<GroupData> applyData = new MutableLiveData<>();
    //动态boolean值，根据接口进度变化
    public ObservableBoolean isRefreshing = new ObservableBoolean();
    private int count = 20;
    private int index = 1;

    public GroupViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        mLiveObservableData = Transformations.switchMap(NetUtils.netConnected(application), new Function<Boolean, LiveData<GroupData>>() {
            @Override
            public LiveData<GroupData> apply(Boolean isNetConnected) {
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
        isRefreshing.set(true);
        GankDataRepository.getGroupDataRepository(count, index)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GroupData>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable.add(d);
                    }

                    @Override
                    public void onNext(GroupData value) {
                        LogUtils.e(value);
                        applyData.setValue(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e(e);
                        isRefreshing.set(false);
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.e("onComplete");
                        index++;
                        isRefreshing.set(false);
                    }
                });
    }

    //http://blog.csdn.net/YoYo_Newbie/article/details/51959154
    public SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            LogUtils.e("onRefreshListener执行");
            LoadData();
        }
    };


    /**
     * LiveData支持了lifecycle生命周期检测
     *
     * @return
     */
    public LiveData<GroupData> getLiveObservableData() {
        return mLiveObservableData;
    }

    /**
     * 设置
     *
     * @param product
     */

    public void setUiObservableData(GroupData product) {
        this.uiObservableData.set(product);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mDisposable.clear();
    }

}
