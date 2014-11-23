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
import com.IBM.smartdonor.Utils.Preferences;
import com.IBM.smartdonor.Utils.XMLParser;
import com.ibm.mobile.services.data.IBMDataException;
import com.ibm.mobile.services.data.IBMDataObject;
import com.ibm.mobile.services.data.IBMQuery;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

public class Activity_My_Transaction extends Activity {
	LinearLayout ll_main;
	Button btn_donate;

	String country = "", pin = "";
	DialogView dialogview;

	List<Transaction> itemList;
	BlueListApplication blApplication;
	public static final String CLASS_NAME = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_transaction);

		initViews();

		blApplication = (BlueListApplication) getApplication();
		dialogview = new DialogView();

		itemList = blApplication.getTransactionList();

		dialogview.showSpinProgress(Activity_My_Transaction.this, "Wait...");
		listItems();
		
		btn_donate.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Activity_My_Transaction.this, Activity_Add_Transaction.class);
				startActivity(i);
				finish();
			}
		});

	}

	private void initViews() {
		// TODO Auto-generated method stub

		ll_main = (LinearLayout) findViewById(R.id.ll_main);
		
		btn_donate = (Button) findViewById(R.id.btn_donate);
	}

	public void showAlert(String header, String msg) {
		AlertDialog.Builder builder = new AlertDialog.Builder(
				Activity_My_Transaction.this);

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

			Preferences pref = new Preferences(Activity_My_Transaction.this);
			String phone = pref.getStringPreference(
					Activity_My_Transaction.this, "phone");

			if (phone.equals(obj.getPHONE())) {
				View child;
				LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				child = inflater.inflate(R.layout.listitem_my_transaction, null);
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
