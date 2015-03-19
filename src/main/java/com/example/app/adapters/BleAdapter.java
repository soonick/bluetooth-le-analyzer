package com.example.app;

import android.bluetooth.BluetoothDevice;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;

/**
 * Adapter for bluetooth devices
 */
public class BleAdapter extends BaseAdapter {
  /**
   * Layout inflater
   */
  private LayoutInflater inflater;

  /**
   * List of Ble devices
   */
  private ArrayList<BluetoothDevice> devices;

  /**
   * Initialize devices ArrayList
   */
  public BleAdapter(LayoutInflater inflater) {
    devices = new ArrayList<BluetoothDevice>();
    this.inflater = inflater;
  }

  /**
   * Add a new Ble Device to the ArrayList. Checks that it is not already there
   * before adding it.
   */
  public void addItem(BluetoothDevice device) {
    if (!devices.contains(device)) {
      devices.add(device);
    }
  }

  /**
   * {@inheritDoc}
   */
  public View getView(int position, View convertView, ViewGroup parent) {
    if (convertView == null) {
      convertView = inflater.inflate(R.layout.ble_item, null);
    }

    return convertView;
  }

  /**
   * {@inheritDoc}
   */
  public long getItemId(int position) {
    return position;
  }

  /**
   * {@inheritDoc}
   */
  public Object getItem(int position) {
    return devices.get(position);
  }

  /**
   * {@inheritDoc}
   */
  public int getCount() {
    return devices.size();
  }
}
