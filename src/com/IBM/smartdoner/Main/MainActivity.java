package com.IBM.smartdoner.Main;

import com.IBM.smartdoner.DB.DBAdapter;
import com.IBM.smartdonor.Utils.DialogView;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ToggleButton;

public class MainActivity extends Activity {

	EditText et_name, et_add1, et_add2, et_area, et_pin, et_state, et_country,
			et_email, et_phone, et_pan;

	String name = "", add1 = "", add2 = "", area = "", pin = "", state = "",
			country = "", email = "", phone = "", pan = "", usertype = "N";

	Button btn_register, btn_validation;
	ToggleButton tg_usertype;
	DialogView dialogview;

	DBAdapter db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initViews();

		db = new DBAdapter(MainActivity.this);
		dialogview = new DialogView();

		tg_usertype.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked == true) {
					usertype = "N";
				}

				else {
					usertype = "U";
				}
			}
		});

		btn_register.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (validation()) {
					dialogview.showSpinProgress(MainActivity.this,
							"Registering...");
					createItem(v);
				}

			}
		});
		
		btn_validation.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Validation Not available Now", 6000).show();
			}
		});

	}

	protected boolean validation() {
		// TODO Auto-generated method stub

		name = et_name.getText().toString().trim();
		add1 = et_add1.getText().toString().trim();
		add2 = et_add2.getText().toString().trim();
		area = et_area.getText().toString().trim();
		pin = et_pin.getText().toString().trim();
		state = et_state.getText().toString().trim();
		country = et_country.getText().toString().trim();
		email = et_email.getText().toString().trim();
		phone = et_phone.getText().toString().trim();
		pan = et_pan.getText().toString().trim();

		if (tg_usertype.isChecked())
			usertype = "U";
		else
			usertype = "N";

		if (name.equals("")) {
			showAlert("Sorry!", "Please enter name");
			return false;
		}

		if (add1.equals("")) {
			showAlert("Sorry!", "Please enter Address");
			return false;
		}

		if (area.equals("")) {
			showAlert("Sorry!", "Please enter Area");
			return false;
		}

		if (pin.equals("")) {
			showAlert("Sorry!", "Please enter PIN");
			return false;
		}

		if (state.equals("")) {
			showAlert("Sorry!", "Please enter State");
			return false;
		}

		if (country.equals("")) {
			showAlert("Sorry!", "Please enter Country");
			return false;
		}

		if (email.equals("")) {
			showAlert("Sorry!", "Please enter Email");
			return false;
		}

		if (phone.equals("")) {
			showAlert("Sorry!", "Please enter Phone");
			return false;
		}

		if (pan.equals("")) {
			showAlert("Sorry!", "Please enter PAN number");
			return false;
		}

		else
			return true;
	}

	private void initViews() {
		// TODO Auto-generated method stub
		et_name = (EditText) findViewById(R.id.et_name);
		et_add1 = (EditText) findViewById(R.id.et_add1);
		et_add2 = (EditText) findViewById(R.id.et_add2);
		et_area = (EditText) findViewById(R.id.et_area);
		et_pin = (EditText) findViewById(R.id.et_pin);
		et_state = (EditText) findViewById(R.id.et_state);
		et_country = (EditText) findViewById(R.id.et_country);
		et_email = (EditText) findViewById(R.id.et_email);
		et_phone = (EditText) findViewById(R.id.et_phone);
		et_pan = (EditText) findViewById(R.id.et_pan);

		btn_register = (Button) findViewById(R.id.btn_register);
		btn_validation = (Button) findViewById(R.id.btn_validation);

		tg_usertype = (ToggleButton) findViewById(R.id.tg_usertype);
	}

	public void createItem(View v) {

		System.out.println("create Item is called");

		Registration item = new Registration();
		// if (!memberNameStr.equals("")) {

		item.setNAME(name);
		item.setADDRESS1(add1);
		item.setADDRESS2(add2);
		item.setAREA(area);
		item.setPIN(pin);
		item.setSTATE(state);
		item.setCOUNTRY(country);
		item.setEMAIL(email);
		item.setPHONE(phone);
		item.setPAN(pan);
		item.setUSERTYPE(usertype);

		/**
		 * IBMObjectResult is used to handle the response from the server after
		 * either creating or saving an object.
		 * 
		 * onResult is called if the object was successfully saved onError is
		 * called if an error occurred saving the object
		 */

		item.save();

		dialogview.dismissProgress();
		
		if(usertype.equals("N")) {
			Intent i = new Intent(MainActivity.this, Activity_Search_Result.class);
			startActivity(i);
			finish();
		}
		else {
			Intent i = new Intent(MainActivity.this, Activity_My_Transaction.class);
			startActivity(i);
			finish();
		}

		// item.saveInBackground(new IBMObjectResult<Registration>() {
		// /**
		// * If the result succeeds, onResult gets called with the object that
		// was created.
		// */
		// public void onResult(Registration object) {
		// if (!isFinishing()) {
		// System.out.println("successsssssssssssss");
		// //listItems();
		// }
		// }
		// /**
		// * If the result failed, onError is called with an exception that
		// describes the error.
		// */
		// public void onError(IBMDataException error) {
		// //Log.e(CLASS_NAME, "Exception : " + error.getMessage());
		// System.out.println("Errrrrrrrrrrrrrrrrrrrrr");
		// }
		// });

		

		// Intent intent = new Intent(CreateEmp.this,MainActivity.class);
		// //intent.putExtra("myDataArray", names);
		// startActivity(intent);

		// / Set All the fields blank
		// memberName.setText("");
		// memberAge.setText("");
		// memberAddress.setText("");

		// set text field back to empty after item added
		// itemToAdd.setText("");
		// }
	}

	public void showAlert(String header, String msg) {
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

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
