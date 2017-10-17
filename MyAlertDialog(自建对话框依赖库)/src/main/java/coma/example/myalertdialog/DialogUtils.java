package coma.example.myalertdialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

/**
 * Created by 范晋炜 on 2017/10/17 0017.
 * coma.example.myalertdialog
 * DialogUtils
 */


public class DialogUtils {

    public static void showDialog(final Context context, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "点击了确认按钮", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "点击了取消按钮", Toast.LENGTH_SHORT).show();
                    }
                });
        builder.create();
        builder.show();
    }

    public static void showIconDialog(final Context context, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "点击了确认按钮", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "点击了取消按钮", Toast.LENGTH_SHORT).show();
                    }
                });
        builder.create();
        builder.show();
    }

}
