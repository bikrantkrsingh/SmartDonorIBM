package com.IBM.smartdoner.Main;

import java.util.concurrent.TimeUnit;

import com.IBM.smartdoner.DB.DBAdapter;
import com.IBM.smartdonor.Utils.DialogView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Activity_Add_Transaction extends Activity {

	Spinner spn_item, spn_validity;
	EditText et_other, et_quantity, et_area, et_address, et_note, et_name, et_phone;
	Button btn_add;

	String userID = "", donationType = "", quantity = "", validity = "",
			area = "", address = "", note = "", date = "", name = "", phone = "";
	DBAdapter db;
	DialogView dialogview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_transaction);

		initViews();

		loadSpinner();

		db = new DBAdapter(this);
		dialogview = new DialogView();

		spn_item.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				if (position == 4)
					et_other.setVisibility(View.VISIBLE);

				else
					et_other.setVisibility(View.GONE);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});

		btn_add.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (validation()) {
					dialogview.showSpinProgress(Activity_Add_Transaction.this,
							"Adding Donation...");
					createItem(v);
				}

			}
		});
	}

	protected boolean validation() {
		// TODO Auto-generated method stub

		if (spn_item.getSelectedItem().toString().equals("Others"))
			donationType = et_other.getText().toString().trim();
		else
			donationType = spn_item.getSelectedItem().toString();

		validity = spn_validity.getSelectedItem().toString();

		name = et_name.getText().toString().trim();
		phone = et_phone.getText().toString().trim();
		quantity = et_quantity.getText().toString().trim();
		area = et_area.getText().toString().trim();
		address = et_address.getText().toString().trim();
		note = et_note.getText().toString().trim();
		long time = System.currentTimeMillis();
		date = String.valueOf(time);
		if (spn_validity.getSelectedItemPosition() == 0) {
			validity = String.valueOf(time + TimeUnit.MINUTES.toMillis(2));
		}

		else if (spn_validity.getSelectedItemPosition() == 1) {
			validity = String.valueOf(time
					+ TimeUnit.MINUTES.toMillis(7 * 24 * 60));
		}

		else if (spn_validity.getSelectedItemPosition() == 2) {
			validity = String.valueOf(time
					+ TimeUnit.MINUTES.toMillis(15 * 24 * 60));
		} else {
			validity = String.valueOf(time
					+ TimeUnit.MINUTES.toMillis(30 * 24 * 60));
		}

		if (donationType.equals("")) {
			showAlert("Sorry!", "Please specify your belongings");
			return false;
		}

		else if (quantity.equals("")) {
			showAlert("Sorry!", "Please mention quantity");
			return false;
		}

		else if (area.equals("")) {
			showAlert("Sorry!", "Please provide Area");
			return false;
		} 
		
		else if (name.equals("")) {
			showAlert("Sorry!", "Please provide Name");
			return false;
		}
		
		else if (address.equals("")) {
			showAlert("Sorry!", "Please provide Address");
			return false;
		}
		
		else if (phone.equals("")) {
			showAlert("Sorry!", "Please provide Contact Number");
			return false;
		}

		else
			return true;
	}

	public void showAlert(String header, String msg) {
		AlertDialog.Builder builder = new AlertDialog.Builder(
				Activity_Add_Transaction.this);

		builder.setMessage(msg).setTitle(header).setCancelable(false)
				.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();

					}
				});
		AlertDialog alert = builder.create();
		alert.show();
	}

	private void loadSpinner() {
		// TODO Auto-generated method stub
		ArrayAdapter<CharSequence> adp1 = ArrayAdapter.createFromResource(
				getApplicationContext(), R.array.item,
				android.R.layout.simple_spinner_item);
		adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spn_item.setAdapter(adp1);

		ArrayAdapter<CharSequence> adp2 = ArrayAdapter.createFromResource(
				getApplicationContext(), R.array.validity,
				android.R.layout.simple_spinner_item);
		adp2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spn_validity.setAdapter(adp2);
	}

	private void initViews() {
		// TODO Auto-generated method stub
		spn_item = (Spinner) findViewById(R.id.spn_item);
		spn_validity = (Spinner) findViewById(R.id.spn_validity);

		et_other = (EditText) findViewById(R.id.et_other);
		et_area = (EditText) findViewById(R.id.et_area);
		et_quantity = (EditText) findViewById(R.id.et_quantity);
		et_address = (EditText) findViewById(R.id.et_address);
		et_note = (EditText) findViewById(R.id.et_note);
		et_name = (EditText) findViewById(R.id.et_name);
		et_phone = (EditText) findViewById(R.id.et_phone);

		btn_add = (Button) findViewById(R.id.btn_add);
	}

	public void createItem(View v) {
		Transaction item = new Transaction();

		item.setDONATIONTYPE(donationType);
		item.setQUANTITY(quantity);
		item.setVALIDITY(validity);
		item.setAREA(area);
		item.setADDRESS(address);
		item.setNOTE(note);
		item.setDATE(date);
		item.setNAME(name);
		item.setPHONE(phone);

		item.save();
		dialogview.dismissProgress();
		Toast.makeText(getApplicationContext(), "Thank you for your Donation",
				6000).show();
		Intent i = new Intent(Activity_Add_Transaction.this, Activity_My_Transaction.class);
		startActivity(i);
		finish();

	}

}
