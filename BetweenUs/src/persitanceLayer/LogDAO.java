package persitanceLayer;

import presentationLayer.views.customComponents.Log;

/**
 *
 */
public interface LogDAO {
    void saveLog(Log log, String gameName);
}
