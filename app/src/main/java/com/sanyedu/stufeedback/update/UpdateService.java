package com.sanyedu.stufeedback.update;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;

import com.sanyedu.sanylib.log.SanyLogs;
import com.sanyedu.sanylib.utils.SystemUtils;
import com.sanyedu.sanylib.utils.ToastUtil;
import com.sanyedu.stufeedback.R;
import com.sanyedu.stufeedback.model.UpdateInfoData;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;

public class UpdateService {

    private static final int ALREADY_NEW = 1;
    private static final int SHOWUPDATE = 2;
    private static final int INSTALLUPDATE = 3;

    private final String savePath = Environment.getExternalStorageDirectory()
            .getPath() + "/updateload";

    private UpdateInfoData mUpdateInfoData;
    private File mDownloadedFile = null;
    private final String defaultPackageName = "testapp.apk";
    private Context mContext = null;
    private boolean mShowToast = false;

    public UpdateService(Context context, boolean showToast) {
        mContext = context;
        mShowToast = showToast;
        mUpdateInfoData = new UpdateInfoData("0", 0, "0", "", "");
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ALREADY_NEW:
                    if (mDownloadedFile != null) {
                        //删除上次已下载的文件
                        if (mDownloadedFile.exists()) {
                            if (!mDownloadedFile.delete()) {
                                mDownloadedFile.deleteOnExit();
                            }
                        }
                    }
                    if (mShowToast) {
                        ToastUtil.showLongToast("当前已是最新版本.");
                    }
                    break;
                case SHOWUPDATE:
                    showUpdateDialog();
                    break;
                case INSTALLUPDATE:
                    installUpdate();
                    break;
            }
        }
    };

    public void checkUpdate(final boolean reportResult) {
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    boolean isNewVersionAvailable = false;
                    if (getServerVersionInfo()) {
                        String lastestVersionCode = "0";
                        if (mUpdateInfoData != null) {
                            if (mUpdateInfoData.getLastestVersionCode() != null) {
                                lastestVersionCode = mUpdateInfoData.getLastestVersionCode();
                            }
                            if (Integer.parseInt(lastestVersionCode) > SystemUtils.getVersionCode(mContext))
                                isNewVersionAvailable = true;
                        }
                    }

                    if (isNewVersionAvailable) {
                        Message msg = new Message();
                        msg.what = SHOWUPDATE;
                        handler.sendMessage(msg);
                    } else {
                        if (reportResult) {
                            Message msg = new Message();
                            msg.what = ALREADY_NEW;
                            handler.sendMessage(msg);
                        }
                    }
                }
            }).start();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public boolean getServerVersionInfo() {
        //是否有可用版本；
        //从网络获取升级信息

        return true;
    }

    //可以换成retrofit 下载更简单
    public boolean downloadFile(ProgressDialog pd) {
       /* URL url;
        try {
            String urlStr = "";
            if (mUpdateInfoData != null && mUpdateInfoData.getUrl() != null) {
                urlStr = mUpdateInfoData.getUrl();
            }
            url = new URL(urlStr);
            HttpURLConnection conn;

            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(10000);
            pd.setMax(conn.getContentLength());
            InputStream is = conn.getInputStream();
            String packageName = urlStr.substring(urlStr.lastIndexOf("/"));
            if (!packageName.endsWith("apk")) {
                packageName = defaultPackageName;
            }

            mDownloadedFile = new File(savePath, packageName);
            FileOutputStream fos = new FileOutputStream(mDownloadedFile);
            BufferedInputStream bis = new BufferedInputStream(is);
            byte[] buffer = new byte[1024];
            int len;
            int total = 0;
            while ((len = bis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
                total += len;
                //update progress
                pd.setProgress(total);
            }
            fos.close();
            bis.close();
            is.close();
            return true;

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }*/
        return true;
    }

    public void InstallAPK(Context context) {

        if (context == null) {
            return;
        }

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(this.mDownloadedFile), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    //显示更新dialog
    private void showUpdateDialog() {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setIcon(R.mipmap.ic_launcher);
            builder.setTitle("发现新版本");
            builder.setMessage(mUpdateInfoData.getContent().replaceAll("\\\\n", "\n"));
            builder.setCancelable(false);
            builder.setPositiveButton(
                    "立即更新",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            final ProgressDialog pd = new ProgressDialog(mContext);
                            try {
                                pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                                pd.setCancelable(false);
                                pd.setMessage("正在下载...");
                                pd.show();

                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        boolean isSuccess = downloadFile(pd);
                                        if (isSuccess) {
                                            pd.dismiss();
                                            Message msg = new Message();
                                            msg.what = INSTALLUPDATE;
                                            handler.sendMessage(msg);
                                        } else {
                                            if (pd != null) {
                                                pd.dismiss();
                                            }
                                          ToastUtil.showLongToast("下载失败，请检查你的网络");
                                        }
                                    }
                                }).start();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
            );
            //TODO:设置按钮的点击事件
//            builder.setNegativeButton(mUpdateInfoData.getForce() == 1 ? "退出应用" : "暂不更新", ((dialog, which) -> {
//                if (mUpdateInfoData != null) {
//                    if (mUpdateInfoData.getForce() == 1) {
//                        Process.killProcess(Process.myPid());
//                    } else {
//                        dialog.dismiss();
//                    }
//                }
//            }));

            AlertDialog dialog = builder.create();
            dialog.show();
            // dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLUE);
            // dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(Color.BLACK);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void installUpdate() {
        InstallAPK(mContext);
    }

    public void destroyHelper() {
        try {
            handler.removeCallbacksAndMessages(null);
            handler = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
