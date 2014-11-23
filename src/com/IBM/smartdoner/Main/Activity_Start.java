package com.IBM.smartdoner.Main;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import bolts.Continuation;
import bolts.Task;
import com.IBM.smartdoner.DB.DBAdapter;
import com.IBM.smartdonor.Utils.DialogView;
import com.IBM.smartdonor.Utils.Preferences;
import com.ibm.mobile.services.data.IBMDataException;
import com.ibm.mobile.services.data.IBMDataObject;
import com.ibm.mobile.services.data.IBMQuery;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity_Start extends Activity {

	EditText et_phone;
	Button btn_search;

	DialogView dialogview;

	String phone = "";
	String respopnce = "", usertype = "";
	DBAdapter db;
	String userType = "";
	String success = "N";

	List<Registration> itemList;
	BlueListApplication blApplication;
	public static final String CLASS_NAME = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);

		initViews();

		db = new DBAdapter(Activity_Start.this);
		blApplication = (BlueListApplication) getApplication();
		dialogview = new DialogView();

		itemList = blApplication.getItemList();

		btn_search.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// if (NetUtils.isNetworkConnected(Activity_Start.this)) {
				//
				//
				//
				// }
				//
				// else {
				// Toast.makeText(getApplicationContext(), "No network",
				// Toast.LENGTH_LONG).show();
				// }

				if (validation()) {
					Preferences pref = new Preferences(Activity_Start.this);
					pref.storeStringPreference(Activity_Start.this, "phone", phone);
					dialogview.showSpinProgress(Activity_Start.this, "Wait...");
					listItems();

				}

			}
		});

	}

	public void listItems() {
		try {
			IBMQuery<Registration> query = IBMQuery
					.queryForClass(Registration.class);
			System.out.println("listItems is called");
			/**
			 * IBMQueryResult is used to receive array of objects from server.
			 * 
			 * onResult is called when it successfully retrieves the objects
			 * associated with the query, and will reorder these items based on
			 * creation time.
			 * 
			 * onError is called when an error occurs during the query.
			 */
			// Query all the Item objects from the server
			query.find().continueWith(
					new Continuation<List<Registration>, Void>() {

						@Override
						public Void then(Task<List<Registration>> task)
								throws Exception {
							// Log error message, if the save task fail.
							if (task.isFaulted()) {
								Log.e(CLASS_NAME, "Exception : "
										+ task.getError().getMessage());
								return null;
							}
							final List<Registration> objects = task.getResult();

							// If the result succeeds, load the list
							if (!isFinishing()) {
								runOnUiThread(new Runnable() {
									public void run() {
										// clear local itemList
										// we'll be reordering and repopulating
										// from DataService
										itemList.clear();
										for (IBMDataObject item : objects) {
											itemList.add((Registration) item);
										}
										sortItems(itemList);
									}
								});
							}
							return null;
						}

					});
		} catch (IBMDataException error) {
			Log.e(CLASS_NAME, "Exception : " + error.getMessage());
		}
	}

	private void sortItems(List<Registration> theList) {
		// sort collection by case insensitive alphabetical order
		checkStatus(theList);
		Collections.sort(theList, new Comparator<Registration>() {
			public int compare(Registration lhs, Registration rhs) {
				// String lhsName = lhs.getName();
				// String rhsName = rhs.getName();

				return 0;
			}
		});
	}

	private void checkStatus(List<Registration> theList) {
		// TODO Auto-generated method stub
		for (int i = 0; i < theList.size(); i++) {
			if (phone.equals(theList.get(i).getPHONE())) {
				success = "Y";
				userType = theList.get(i).getUSERTYPE();
			}

		}

		dialogview.dismissProgress();
		gotoNextActivity();

	}

	private void gotoNextActivity() {
		// TODO Auto-generated method stub
		if (success.equals("N")) {
			Intent i = new Intent(Activity_Start.this, MainActivity.class);
			startActivity(i);
			finish();
		}

		else {
			if(userType.equals("U")) {
				Intent i = new Intent(Activity_Start.this, Activity_My_Transaction.class);
				startActivity(i);
				finish();
			}
			else {
				Intent i = new Intent(Activity_Start.this, Activity_Search_Result.class);
				startActivity(i);
				finish();
			}
			
		}

	}

	protected boolean validation() {
		// TODO Auto-generated method stub

		phone = et_phone.getText().toString().trim();

		if (phone.equals("") || phone.length() != 10) {
			showAlert("Sorry!", "Please enter valid phone");
			return false;
		}

		else
			return true;
	}

	private void initViews() {
		// TODO Auto-generated method stub
		et_phone = (EditText) findViewById(R.id.et_phone);
		btn_search = (Button) findViewById(R.id.btn_search);
	}

	public void showAlert(String header, String msg) {
		AlertDialog.Builder builder = new AlertDialog.Builder(
				Activity_Start.this);

		builder.setMessage(msg).setTitle(header).setCancelable(false)
				.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();

					}
				});
		AlertDialog alert = builder.create();
		alert.show();
	}

}
