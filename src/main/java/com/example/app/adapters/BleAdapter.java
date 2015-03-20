package com.example.app;

import android.bluetooth.BluetoothDevice;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
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
    ViewHolder vh;
    String name;

    if (convertView == null) {
      convertView = inflater.inflate(R.layout.ble_item, parent, false);
      vh = new ViewHolder();
      vh.deviceName = (TextView)convertView.findViewById(R.id.device_name);
      convertView.setTag(vh);
    } else {
      vh = (ViewHolder)convertView.getTag();
    }

    BluetoothDevice current = (BluetoothDevice)getItem(position);
    name = current.getName();
    if (name == null) {
      name = current.getAddress();
    }
    vh.deviceName.setText(name);

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

  /**
   * This class is used to avoid costly calls to findViewById
   */
  private static class ViewHolder {
    public TextView deviceName;
  }
}
