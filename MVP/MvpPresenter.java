public class MvpPresenter {
    private MvpView mvpView;
    private RequestBiz requestBiz;
    private Handler mHandler;
    //此构造方法传入了一个MvpView 所以我们只需要在Activity中实现这个MvpView接口就可以传入上下文 并以此调用MvpView 的抽象方法（可能这种说法不对,管他呢）
    public MvpPresenter(MvpView mvpView) {
        this.mvpView = mvpView;
        requestBiz = new RequestBiziml();
        mHandler = new Handler(Looper.getMainLooper());
    }
    public void onResume(){
        mvpView.showLoading();
        requestBiz.requestForData(new OnRequestListener() {
            @Override
            public void onSuccess(final List<String> data) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mvpView.hideLoading();
                        mvpView.setListItem(data);
                    }
                });
            }
            @Override
            public void onFailed() {
                mvpView.showMessage("请求失败");
            }
        });
    }
    public void onDestroy(){
        mvpView = null;
    }
    public void onItemClick(int position){
        mvpView.showMessage("点击了item"+position);
    }
}
