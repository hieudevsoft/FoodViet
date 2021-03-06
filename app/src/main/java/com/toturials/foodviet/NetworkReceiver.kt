import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.toturials.foodviet.NetworkUtil.getConnectivityStatusString

class NetworkReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        var status = getConnectivityStatusString(context)
        if (status!!.isEmpty()) {
            status = "No Internet Connection"
        }
        Toast.makeText(context, status, Toast.LENGTH_LONG).show()
    }
}