package devliving.online.mvbarcodereader;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.IntDef;
import androidx.fragment.app.Fragment;

import com.google.android.gms.vision.barcode.Barcode;

/**
 * Created by user on 10/2/16.
 */
public class MVBarcodeScanner {
    public static final String SCANNING_MODE = "scanning_mode";
    public static final String BARCODE_FORMATS = "barcode_formats";
    public static final String PREVIEW_SCALE_TYPE = "preview_scale_type";

    // constants used to pass extra data in the intent
    public static final String BarcodeObject = "Barcode";
    public static final String BarcodeObjects = "Barcodes";

    ScanningMode mMode = null;
    @BarCodeFormat
    int[] mFormats = null;

    private MVBarcodeScanner(ScanningMode mode, @BarCodeFormat int[] formats) {
        mMode = mode;
        mFormats = formats;
    }

    public void launchScanner(Activity activity, int requestCode) {
        activity.startActivityForResult(getScannerIntent(activity), requestCode);
    }

    public void launchScanner(Fragment fragment, int requestCode) {
        fragment.startActivityForResult(getScannerIntent(fragment.getContext()), requestCode);
    }

    public Intent getScannerIntent(Context context) {
        Intent i = new Intent(context, BarcodeCaptureActivity.class);
        if (mMode != null) i.putExtra(SCANNING_MODE, mMode);
        if (mFormats != null) i.putExtra(BARCODE_FORMATS, mFormats);
        return i;
    }

    public static class Builder {
        ScanningMode mMode = null;
        @BarCodeFormat
        int[] mFormats = null;

        public Builder setScanningMode(ScanningMode mode) {
            mMode = mode;
            return this;
        }

        public Builder setFormats(@BarCodeFormat int... formats) {
            mFormats = formats;
            return this;
        }

        public MVBarcodeScanner build() {
            return new MVBarcodeScanner(mMode, mFormats);
        }
    }

    @IntDef({
            Barcode.ALL_FORMATS,
            Barcode.AZTEC,
            Barcode.CALENDAR_EVENT,
            Barcode.CODABAR,
            Barcode.CODE_39,
            Barcode.CODE_93,
            Barcode.CODE_128,

            Barcode.DATA_MATRIX,Barcode.CONTACT_INFO,
            Barcode.DRIVER_LICENSE,
            Barcode.EAN_8,
            Barcode.EAN_13,
            Barcode.EMAIL,
            Barcode.GEO,
            Barcode.ISBN,
            Barcode.ITF,
            Barcode.PDF417,
            Barcode.PHONE,
            Barcode.PRODUCT,
            Barcode.QR_CODE,
            Barcode.SMS,
            Barcode.UPC_A,
            Barcode.TEXT,
            Barcode.UPC_E,
            Barcode.URL,
            Barcode.WIFI
    })
    public @interface BarCodeFormat {

    }


    public enum ScanningMode {
        SINGLE_AUTO,
        SINGLE_MANUAL,
        MULTIPLE
    }
}
