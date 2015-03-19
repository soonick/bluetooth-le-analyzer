package com.example.app;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;

/**
 * Main activity.
 */
public class Example extends Activity {
  /**
   * Id for enable bluetooth request
   */
  private final static int REQUEST_ENABLE_BT = 1;

  /**
   * Handler to stop scanning
   */
  private Handler mHandler;

  /**
   * Bluetooth adapter
   */
  private BluetoothAdapter mBluetoothAdapter;

  /**
   * Bluetooth scanner
   */
  private BluetoothLeScanner scanner;

  /**
   * List view for the devices
   */
  private ListView listView;

  /**
   * Adapter for the tlist
   */
  private BleAdapter adapter;

  /**
   * Period after which we want to stop scanning (in miliseconds)
   */
  private static final long SCAN_PERIOD = 1000;

  /**
   * Initialize view and make sure we BLE is available
   * @param savedInstanceState - You know ;)
   */
  @Override
  public void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    // Initializes Bluetooth adapter.
    final BluetoothManager bluetoothManager =
        (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
    mBluetoothAdapter = bluetoothManager.getAdapter();

    // Ensures Bluetooth is available on the device and it is enabled. If not,
    // displays a dialog requesting user permission to enable Bluetooth.
    if (mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()) {
        Intent enableBtIntent =
            new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
    }
  }

  /**
   * Hook adapter to view and start scanning
   */
  @Override
  public void onResume() {
    super.onResume();

    scanner = mBluetoothAdapter.getBluetoothLeScanner();
    listView = (ListView)findViewById(R.id.bleDevices);
    adapter = new BleAdapter(this.getLayoutInflater());
    listView.setAdapter(adapter);
    scanLeDevice();
  }

  /**
   * Starts scanning and creates a timeout to stop scanning
   */
  private void scanLeDevice() {
    // Stops scanning after a pre-defined scan period.
    mHandler = new Handler();
    mHandler.postDelayed(new Runnable() {
      @Override
      public void run() {
        scanner.stopScan(bleCallbacks);
      }
    }, SCAN_PERIOD);

    scanner.startScan(bleCallbacks);
  }

  private ScanCallback bleCallbacks  = new ScanCallback() {
    /**
     * Adds devices to the adapter
     */
    @Override
    public void onScanResult(int callbackType, final ScanResult result) {
      runOnUiThread(new Runnable() {
        @Override
        public void run() {
          adapter.addItem(result.getDevice());
          adapter.notifyDataSetChanged();
        }
      });
    }
  };
}
