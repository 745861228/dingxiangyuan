package com.me.dingxiangyuan.acitvity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.me.dingxiangyuan.R;
import com.me.dingxiangyuan.base.BaseActivity;
import com.me.dingxiangyuan.view.DoubleDatePickerDialog;
import com.me.dingxiangyuan.view.SelfDialog;

import java.io.File;
import java.util.Calendar;

public class DatumActivity extends BaseActivity {
    private ImageView ruturn_img;
    private ImageView user_imgs;
    private RelativeLayout user_img;
    private SelfDialog selfDialog;
    private RelativeLayout date_picker;
    private RelativeLayout profess;
    private TextView tv;
    private static final int PHOTO_REQUEST_CAREMA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果
    private static final String PHOTO_FILE_NAME = "temp_photo.jpg";
    private File tempFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datum);
        initView();
    }

    //初始化控件
    private void initView() {
        ruturn_img = (ImageView) findViewById(R.id.imag_return);
        user_imgs = (ImageView) findViewById(R.id.user_images);
        user_img = (RelativeLayout) findViewById(R.id.user_img);
        date_picker = (RelativeLayout) findViewById(R.id.data_picker);
        profess = (RelativeLayout) findViewById(R.id.profess);

        tv = (TextView) findViewById(R.id.tv);


        //返回
        ruturn_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.leftin, R.anim.leftout);
            }

        });
        //职业选择
        profess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DatumActivity.this,ProfessionalActivity.class);
                startActivity(intent);
            }
        });
        //日期
        date_picker.setOnClickListener(new View.OnClickListener() {
            Calendar c = Calendar.getInstance();

            @Override
            public void onClick(View v) {
                // 最后一个false表示不显示日期，如果要显示日期，最后参数可以是true或者不用输入
                new DoubleDatePickerDialog(DatumActivity.this, 0, new DoubleDatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker startDatePicker, int startYear, int startMonthOfYear) {
                        String textString = String.format(String.valueOf(startYear),
                                startMonthOfYear + 1);
                        tv.setText(textString);
                    }


                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), true).show();
            }
        });
        //拍照获取相册
        user_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selfDialog = new SelfDialog(DatumActivity.this);
                selfDialog.setTitle("提示");
                selfDialog.setYesOnclickListener("相册", new SelfDialog.onYesOnclickListener() {
                    @Override
                    public void onYesClick() {
                        gallery();

                        selfDialog.dismiss();
                    }
                });
                selfDialog.setNoOnclickListener("拍照", new SelfDialog.onNoOnclickListener() {
                    @Override
                    public void onNoClick() {
                        camera();

                        selfDialog.dismiss();
                    }
                });
                selfDialog.show();
            }


        });
    }

    private void gallery() {
        // 激活系统图库，选择一张图片
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_GALLERY
        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
    }

    private void camera() {
        // 激活相机
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        // 判断存储卡是否可以用，可用进行存储
        if (hasSdcard()) {
            tempFile = new File(Environment.getExternalStorageDirectory(),
                    PHOTO_FILE_NAME);
            // 从文件中创建uri
            Uri uri = Uri.fromFile(tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CAREMA
        startActivityForResult(intent, PHOTO_REQUEST_CAREMA);
    }

    /*
  * 剪切图片
  */
    private void crop(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);

        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    /*
87      * 判断sdcard是否被挂载
88      */
    private boolean hasSdcard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PHOTO_REQUEST_GALLERY) {
            // 从相册返回的数据
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                crop(uri);
            }

        } else if (requestCode == PHOTO_REQUEST_CAREMA) {
            // 从相机返回的数据
            if (hasSdcard()) {
                crop(Uri.fromFile(tempFile));
            } else {
                Toast.makeText(DatumActivity.this, "未找到存储卡，无法存储照片！", Toast.LENGTH_LONG).show();
            }

        } else if (requestCode == PHOTO_REQUEST_CUT) {
            // 从剪切图片返回的数据
            if (data != null) {
                Bitmap bitmap = data.getParcelableExtra("data");
                this.user_imgs.setImageBitmap(bitmap);
            }
            try {
                // 将临时文件删除
                tempFile.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}