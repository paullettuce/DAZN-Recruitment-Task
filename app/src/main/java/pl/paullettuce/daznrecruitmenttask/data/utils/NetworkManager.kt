package pl.paullettuce.daznrecruitmenttask.data.utils

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities.TRANSPORT_CELLULAR
import android.net.NetworkCapabilities.TRANSPORT_ETHERNET
import android.net.NetworkCapabilities.TRANSPORT_WIFI
import android.os.Build
import javax.inject.Inject

class NetworkManager @Inject constructor(
    private val connectivityManager: ConnectivityManager
) {
    fun isNetworkAvailable(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities: Network = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            return actNw.hasTransport(TRANSPORT_WIFI) ||
                    actNw.hasTransport(TRANSPORT_CELLULAR) ||
                    actNw.hasTransport(TRANSPORT_ETHERNET)

        } else {
            return connectivityManager.activeNetworkInfo?.isAvailable ?: false
        }
    }
}