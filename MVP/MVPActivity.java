public class MVPActivity extends AppCompatActivity implements MvpView,AdapterView.OnItemClickListener{
 
    private ListView mvpListView;
    private MvpPresenter mvpPresenter;
    private ProgressBar pb;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        mvpListView = (ListView)findViewById(R.id.mvp_listview);
        mvpListView.setOnItemClickListener(this);
        pb = (ProgressBar) findViewById(R.id.mvp_loading);
        mvpPresenter = new MvpPresenter(this);
    }
 
    @Override
    protected void onResume() {
        super.onResume();
        mvpPresenter.onResume();
    }
 
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mvpPresenter.onItemClick(position);
    }
 
    @Override
    public void showLoading() {
        pb.setVisibility(View.VISIBLE);
    }
 
    @Override
    public void hideLoading() {
        pb.setVisibility(View.GONE);
    }
 
    @Override
    public void setListItem(List<String> data) {
        ArrayAdapter adapter = new ArrayAdapter(MVPActivity.this,android.R.layout.simple_list_item_1,data);
        mvpListView.setAdapter(adapter);
    }
 
    //退出时销毁持有Activity
    @Override
    protected void onDestroy() {
        mvpPresenter.onDestroy();
        super.onDestroy();
    }
 
    @Override
    public void showMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
