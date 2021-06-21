package com.knowaboutit.BluetoothToggle;

import android.app.Activity;
import android.content.Context;
import com.google.appinventor.components.annotations.*;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.EventDispatcher;
import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.pm.PackageManager;

@DesignerComponent(
        version = 1,
        description = "",
        category = ComponentCategory.EXTENSION,
        nonVisible = true,
        iconName = "")

@SimpleObject(external = true)
//Permissions
@UsesPermissions(permissionNames = "android.permission.BLUETOOTH, android.permission.BLUETOOTH_ADMIN, android.permission.ACCESS_COARSE_LOCATION")

public class BluetoothToggle extends AndroidNonvisibleComponent {

    //Activity and Context
    private Context context;
    private Activity activity;
    private BluetoothAdapter mBluetoothAdapter;

    public BluetoothToggle(ComponentContainer container){
        super(container.$form());
        this.activity = container.$context();
        this.context = container.$context();
    }

    @SimpleFunction(description = "Disables bluetooth")
    public void DisableBluetooth(){
    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    mBluetoothAdapter.enable();
    }

    @SimpleFunction(description = "Enables Bluetooth")
    public void EnableBluetooth(){
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        mBluetoothAdapter.disable();
    }
    @SimpleFunction(description="Returns whether bluetooth is enabled or not,empty string if bluetooth is not supported")
    public String IsEnabled(){
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(mBluetoothAdapter == null){ // ohhhh :(
                return "";
        }    
        return mBluetoothAdapter.isEnabled();
    }
        
    @SimpleFunction(description = "Check if BLE is supported or not ")
    public boolean IsBLESupported(){
       if ((this.context).getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)){
          return true;
        }else{
          return false;
        }

    }
        
     @SimpleFunction(description = "Enable BLE")
    public void EnableBLE(){
        final BluetoothManager bluetoothManager = (this.activity).getSystemService(BluetoothManager.class);
        BluetoothAdapter bluetoothAdapter = null;
        if (bluetoothManager != null) {
        bluetoothAdapter = bluetoothManager.getAdapter();
        }

    if ((bluetoothAdapter != null) && !bluetoothAdapter.isEnabled()) {
        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        (this.activity).startActivityForResult(enableBtIntent, 0);
     }
    }
}

