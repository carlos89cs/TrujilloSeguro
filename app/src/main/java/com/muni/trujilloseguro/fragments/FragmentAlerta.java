package com.muni.trujilloseguro.fragments;

import android.app.Activity;
import android.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.muni.trujilloseguro.app.R;
import com.muni.trujilloseguro.app.MainActivity;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carlos on 05/06/14.
 */
public class FragmentAlerta extends Fragment {


    Button btnContacto;
    public static final int	PICK_CONTACT	= 1;
    private final static int REQUEST_CONTACTPICKER = 1;
    public static final int RESULT_OK = 1;
    private TextView txtContacts;

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int REQUEST_CODE_PICK_CONTACTS = 1;
    private Uri uriContact;
    private String contactID;     // contacts unique ID

    public FragmentAlerta() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_layout_alerta, container,false);

        btnContacto = (Button)view.findViewById(R.id.buttoncontacto);

        btnContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(Intent.ACTION_PICK);
                //intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
                //startActivityForResult(intent, PICK_CONTACT);
                startActivityForResult(new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI), REQUEST_CODE_PICK_CONTACTS);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_PICK_CONTACTS && resultCode == RESULT_OK) {
            Log.d(TAG, "Response: " + data.toString());
            uriContact = data.getData();

            retrieveContactName();


        }
    }

    private void retrieveContactName() {

        String contactName = null;

        // querying contact data store
        Cursor cursor = getActivity().getContentResolver().query(uriContact, null, null, null, null);

        if (cursor.moveToFirst()) {

            // DISPLAY_NAME = The display name for the contact.
            // HAS_PHONE_NUMBER =   An indicator of whether this contact has at least one phone number.

            contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            txtContacts.setText(contactName);
            System.out.println(contactName);
        }

        cursor.close();

        Log.d(TAG, "NOMBRE DE CONTACTO ===============================================>: " + contactName);

    }


    /*
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CONTACTPICKER)
        {
            if(resultCode == RESULT_OK)
            {
                Uri contentUri = data.getData();
                String contactId = contentUri.getLastPathSegment();
                Cursor cursor = getActivity().getContentResolver().query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                        ContactsContract.CommonDataKinds.Phone._ID + "=?",       // < - Note, not CONTACT_ID!
                        new String[]{contactId}, null);
                getActivity().startManagingCursor(cursor);
                Boolean numbersExist = cursor.moveToFirst();
                int phoneNumberColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String phoneNumber = "";
                while (numbersExist)
                {
                    phoneNumber = cursor.getString(phoneNumberColumnIndex);
                    phoneNumber = phoneNumber.trim();
                    numbersExist = cursor.moveToNext();
                }
                getActivity().stopManagingCursor(cursor);
                if (!phoneNumber.equals(""))
                {
                    txtContacts.setText(phoneNumber);
                    //setPhoneNumber(phoneNumber);
                } // phoneNumber != ""
            } // Result Code = RESULT_OK
        } // Request Code = REQUEST_CONTACTPICER
    }	// end function

*/
    /*
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CONTACTPICKER)
        {
            if(resultCode == RESULT_OK)
            {
                Uri contentUri = data.getData();
                String contactId = contentUri.getLastPathSegment();
                Cursor cursor = getActivity().getContentResolver().query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                        ContactsContract.CommonDataKinds.Phone._ID + "=?",       // < - Note, not CONTACT_ID!
                        new String[]{contactId}, null);
                getActivity().startManagingCursor(cursor);
                Boolean numbersExist = cursor.moveToFirst();
                int phoneNumberColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String phoneNumber = "";
                while (numbersExist)
                {
                    phoneNumber = cursor.getString(phoneNumberColumnIndex);
                    phoneNumber = phoneNumber.trim();
                    numbersExist = cursor.moveToNext();
                }
                getActivity().stopManagingCursor(cursor);
                if (!phoneNumber.equals(""))
                {
                    txtContacts.setText(phoneNumber);
                    //setPhoneNumber(phoneNumber);
                } // phoneNumber != ""
            } // Result Code = RESULT_OK
        } // Request Code = REQUEST_CONTACTPICER
    }	// end function

    */
    /*

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CONTACT_PICKER_RESULT:
                    final EditText phoneInput = (EditText) findViewById(R.id.phoneNumberInput);
                    Cursor cursor = null;
                    String phoneNumber = "";
                    List<String> allNumbers = new ArrayList<String>();
                    int phoneIdx = 0;
                    try {
                        Uri result = data.getData();
                        String id = result.getLastPathSegment();
                        cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?", new String[] { id }, null);
                        phoneIdx = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DATA);
                        if (cursor.moveToFirst()) {
                            while (cursor.isAfterLast() == false) {
                                phoneNumber = cursor.getString(phoneIdx);
                                allNumbers.add(phoneNumber);
                                cursor.moveToNext();
                            }
                        } else {
                            //no results actions
                        }
                    } catch (Exception e) {
                        //error actions
                    } finally {
                        if (cursor != null) {
                            cursor.close();
                        }

                        final CharSequence[] items = allNumbers.toArray(new String[allNumbers.size()]);
                        AlertDialog.Builder builder = new AlertDialog.Builder(your_class.this);
                        builder.setTitle("Choose a number");
                        builder.setItems(items, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                String selectedNumber = items[item].toString();
                                selectedNumber = selectedNumber.replace("-", "");
                                phoneInput.setText(selectedNumber);
                            }
                        });
                        AlertDialog alert = builder.create();
                        if(allNumbers.size() > 1) {
                            alert.show();
                        } else {
                            String selectedNumber = phoneNumber.toString();
                            selectedNumber = selectedNumber.replace("-", "");
                            phoneInput.setText(selectedNumber);
                        }

                        if (phoneNumber.length() == 0) {
                            //no numbers found actions
                        }
                    }
                    break;
            }
        } else {
            //activity result error actions
        }
    }
    */

}
