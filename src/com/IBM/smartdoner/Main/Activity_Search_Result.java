package com.IBM.smartdoner.Main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import bolts.Continuation;
import bolts.Task;

import com.IBM.smartdonor.Utils.DialogView;
import com.IBM.smartdonor.Utils.NetUtils;
import com.IBM.smartdonor.Utils.XMLParser;
import com.ibm.mobile.services.data.IBMDataException;
import com.ibm.mobile.services.data.IBMDataObject;
import com.ibm.mobile.services.data.IBMQuery;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;

public class Activity_Search_Result extends Activity {

	EditText et_area;
	Button btn_search;
	LinearLayout ll_main;

	String country = "", pin = "";
	DialogView dialogview;

	List<Transaction> itemList;
	BlueListApplication blApplication;
	public static final String CLASS_NAME = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_result);

		initViews();

		blApplication = (BlueListApplication) getApplication();
		dialogview = new DialogView();

		itemList = blApplication.getTransactionList();

		dialogview.showSpinProgress(Activity_Search_Result.this, "Wait...");
		listItems();

		btn_search.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				if (NetUtils.isNetworkConnected(Activity_Search_Result.this)) {
//
//					if (validation()) {
//
//					}
//
//				}
//
//				else {
//					Toast.makeText(getApplicationContext(), "No network",
//							Toast.LENGTH_LONG).show();
//				}
			}
		});
	}

	private void initViews() {
		// TODO Auto-generated method stub
		et_area = (EditText) findViewById(R.id.et_area);
		btn_search = (Button) findViewById(R.id.btn_search);
		ll_main = (LinearLayout) findViewById(R.id.ll_main);
	}

	protected boolean validation() {
		// TODO Auto-generated method stub
		country = et_area.getText().toString().trim();

		if (pin.equals("")) {
			showAlert("Sorry!", "Please enter valid Pin");
			return false;
		}

		else
			return true;
	}

	public void showAlert(String header, String msg) {
		AlertDialog.Builder builder = new AlertDialog.Builder(
				Activity_Search_Result.this);

		builder.setMessage(msg).setTitle(header).setCancelable(false)
				.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();

					}
				});
		AlertDialog alert = builder.create();
		alert.show();
	}

	public void listItems() {
		try {
			IBMQuery<Transaction> query = IBMQuery
					.queryForClass(Transaction.class);
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
					new Continuation<List<Transaction>, Void>() {

						@Override
						public Void then(Task<List<Transaction>> task)
								throws Exception {
							// Log error message, if the save task fail.
							if (task.isFaulted()) {
								Log.e(CLASS_NAME, "Exception : "
										+ task.getError().getMessage());
								return null;
							}
							final List<Transaction> objects = task.getResult();

							// If the result succeeds, load the list
							if (!isFinishing()) {
								runOnUiThread(new Runnable() {
									public void run() {
										// clear local itemList
										// we'll be reordering and repopulating
										// from DataService
										itemList.clear();
										for (IBMDataObject item : objects) {
											itemList.add((Transaction) item);
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

	private void sortItems(List<Transaction> theList) {
		// sort collection by case insensitive alphabetical order
		loadList(theList);
		Collections.sort(theList, new Comparator<Transaction>() {
			public int compare(Transaction lhs, Transaction rhs) {
				// String lhsName = lhs.getName();
				// String rhsName = rhs.getName();

				return 0;
			}
		});

	}

	private void loadList(List<Transaction> theList) {
		// TODO Auto-generated method stub
		dialogview.dismissProgress();
		for (int i = 0; i < theList.size(); i++) {

			Transaction obj = theList.get(i);

			if (System.currentTimeMillis() <= Long.valueOf(obj.getVALIDITY())) {
				View child;
				LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				child = inflater.inflate(R.layout.listitem_search_result, null);
				TextView tv_item = (TextView) child.findViewById(R.id.tv_item);
				TextView tv_quantity = (TextView) child
						.findViewById(R.id.tv_quantity);
				TextView tv_name = (TextView) child.findViewById(R.id.tv_name);
				TextView tv_address = (TextView) child
						.findViewById(R.id.tv_address);
				TextView tv_area = (TextView) child.findViewById(R.id.tv_area);
				TextView tv_phone = (TextView) child
						.findViewById(R.id.tv_phone);

				tv_item.setText("Donation : " + obj.getDONATIONTYPE()
						+ "  Quantity : " + obj.getQUANTITY());
				// tv_quantity.setText("Quantity : " + obj.getQUANTITY());
				tv_name.setText("Name : " + obj.getNAME());
				tv_address.setText("Address : " + obj.getADDRESS());
				tv_area.setText("Area/Location : " + obj.getAREA());
				tv_phone.setText("Contact : +91-" + obj.getPHONE());

				ll_main.addView(child);

				LinearLayout ll = new LinearLayout(this);
				ll.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
						8));
				ll_main.addView(ll);
			}

		}
	}
}
