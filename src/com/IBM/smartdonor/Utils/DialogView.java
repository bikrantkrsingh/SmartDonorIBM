package com.IBM.smartdonor.Utils;



import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DialogView {
	static ProgressDialog mAlertDialog;
	
	

	public void showSingleButtonDialog(Context context, String msg) {
		AlertDialog.Builder adb = new AlertDialog.Builder(context);
		adb.setTitle("Alert");
		adb.setMessage(msg);
		adb.setNeutralButton("Ok", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});

		AlertDialog alert = adb.create();
		alert.show();
	}
	
	
	
	public void showSpinProgress(Context context, String msg){
		mAlertDialog = new ProgressDialog(context);
		mAlertDialog.setMessage(msg);
		mAlertDialog.setCancelable(true);
		mAlertDialog.setCanceledOnTouchOutside(false);
		mAlertDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mAlertDialog.show();
	}
	
	public void showHorizontalProgress(Context context, String msg){
		mAlertDialog = new ProgressDialog(context);
		mAlertDialog.setMessage(msg);
		mAlertDialog.setCancelable(true);
		mAlertDialog.setCanceledOnTouchOutside(false);
		mAlertDialog.setIndeterminate(false);
		mAlertDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		mAlertDialog.setProgress(0);
		mAlertDialog.setMax(100);
		mAlertDialog.show();
	}
	
	public void dismissProgress(){
		if(mAlertDialog.isShowing())
			mAlertDialog.dismiss();
	}

	public void showToast(Context context, String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
	}
	
	
	
}
