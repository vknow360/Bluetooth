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
    BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
    adapter.enable();
    }

    @SimpleFunction(description = "Enables Bluetooth")
    public void EnableBluetooth(){
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        adapter.disable();
    }
    @SimpleFunction(description = "Tell bluetooth is enabled or not Bruh")
    public void CheckState(){
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
        BluetoothNotSupported();
      } else if (!mBluetoothAdapter.isEnabled()) {
        BluetoothDisabled();
      } else {
        BluetoothEnabled();
      }
}
    @SimpleEvent()
    public void BluetoothNotSupported() {
        EventDispatcher.dispatchEvent(this, "BluetoothNotSupported");
    }
    @SimpleEvent()
    public void BluetoothDisabled() {
        EventDispatcher.dispatchEvent(this, "BluetoothDisabled");
    }
    @SimpleEvent()
    public void BluetoothEnabled() {
        EventDispatcher.dispatchEvent(this, "BluetoothEnabled");
    }

    }

