package com.example.administrator.teacherhelper.view.Adapter;

/**
 * Created by Administrator on 2018/3/14 0014.
 */


public class MyAdapter{
//public class MyAdapter extends BaseAdapter {
//    private Context context ;
//    private List<DOWNLOAD> list;
//    public MyAdapter(Context context, List<DOWNLOAD> list){
//        this.context = context;
//        this.list = list;
//    }
//
//    @Override
//    public int getCount() {
//        return list.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return list.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent) {
//        final ViewHolder viewHolder;
//        if (convertView == null){
//            String name;
//            BmobFile image;
//
//            name = list.get(position).getName();
////            System.out.println(image.getFileUrl());
//            LayoutInflater inflater = LayoutInflater.from(context);
//            convertView = inflater.inflate(R.layout.my_download, null);//实例化一个布局文件
//            viewHolder = new ViewHolder();
//            viewHolder.tv_desc = (TextView)convertView.findViewById(R.id.dowload);
//            convertView.setTag(viewHolder);
//
////            //不能直接在主线程中进行从网络端获取图片，而需要单独开一个子线程完成从网络端获取图片
////            new Thread(new Runnable() {
////                @Override
////                public void run() {
////                    //根据表中图片的url地址来得到图片（Bitmap类型）
////                    final Bitmap bitmap=getPicture(list.get(position).getIcon().getFileUrl());
////                    try {
////                        Thread.sleep(2000);
////                    } catch (InterruptedException e) {
////                        e.printStackTrace();
////                    }
////                    viewHolder.iv_img.post(new Runnable() {
////                        @Override
////                        public void run() {
////                            System.out.println("*********************************");
////                            viewHolder.iv_img.setImageBitmap(bitmap);//将图片放到视图中去
////                        }
////                    });
////                }
////            }).start();
//            viewHolder.tv_desc.setText(name);
//            viewHolder.tv_desc.setOnClickListener(download);
//
//        }else {
//            viewHolder = (ViewHolder) convertView.getTag();
//        }
//        return convertView;
//    }
//    class ViewHolder{
//        TextView tv_desc;
//    }
//    View.OnClickListener download = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    //根据表中图片的url地址来得到图片（Bitmap类型）
//                    final Bitmap bitmap=getContent(list.get(position).getIcon().getFileUrl());
//                    try {
//                        Thread.sleep(2000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    viewHolder.iv_img.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            System.out.println("*********************************");
//                            viewHolder.iv_img.setImageBitmap(bitmap);//将图片放到视图中去
//                        }
//                    });
//                }
//            }).start();
//
//        }
//    };
}
