package com.fajuary.xiyishop_android.module.activity;

/**
 * created by huanghui at 2016/10/11
 * 订单管理页
 */

//public class OrderManagerActivity extends Activity implements View.OnClickListener {
//    private TabLayout mTabLayout;
//    private ViewPager mViewPager;
//
//    private List<String> mTitleList = new ArrayList<>();//页卡标题集合
//    private ArrayList<BasePager> mViewList = new ArrayList<>();//页卡视图集合
//    private ImageView iv_back;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.ordermanager_main);
//        LogUtil.e("订单管理页面");
//        iv_back = (ImageView) findViewById(R.id.iv_back);
//        mViewPager = (ViewPager) findViewById(R.id.vp_view);
//        mTabLayout = (TabLayout) findViewById(R.id.tabs);
//
//        //添加页卡视图
//        mViewList.add(new CompletePager(this));//未完成订单页
//        mViewList.add(new UnfinsheedPager(this));//完成订单页
//
//        //添加页卡标题
//        mTitleList.add("未完成的订单");
//        mTitleList.add("完成的订单");
//
//
//        mTabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
//        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(0)));//添加tab选项卡
//        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(1)));
//
//
//        MyPagerAdapter mAdapter = new MyPagerAdapter();
//        mViewPager.setAdapter(mAdapter);//给ViewPager设置适配器
//        mTabLayout.setupWithViewPager(mViewPager);//将TabLayout和ViewPager关联起来。
//        mTabLayout.setTabsFromPagerAdapter(mAdapter);//给Tabs设置适配器
//
//        iv_back.setOnClickListener(this);
//
//    }
//
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case  R.id.iv_back://返回
//                    finish();
//                break;
//        }
//    }
//    //ViewPager适配器
//    class MyPagerAdapter extends PagerAdapter {
//        @Override
//        public int getCount() {
//            return mViewList.size();//页卡数
//        }
//
//        @Override
//        public boolean isViewFromObject(View view, Object object) {
//            return view == object;//官方推荐写法
//        }
//
//        @Override
//        public Object instantiateItem(ViewGroup container, int position) {
//            BasePager basePager = mViewList.get(position);
//            View rootView = basePager.rootView;
//            container.addView(rootView);//不能为空   不然报异常
//            basePager.initData();
//            return rootView;
//        }
//
//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
//            container.removeView((View) object);
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return mTitleList.get(position);//页卡标题
//        }
//
//    }
//}
