package cl.telematica.android.certamen3.models.contracts;

/**
 * Created by Patricio on 18-11-2016.
 */

public interface HttpServerConnection {
    String connectToServer(String myUrl, int timeOut);
}
