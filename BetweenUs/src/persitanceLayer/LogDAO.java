package persitanceLayer;

import presentationLayer.views.customComponents.Log;

/**
 * Interfície que conté els mètodes que tracten els logs
 */
public interface LogDAO {
    void saveLog(Log log, String gameName);
}
