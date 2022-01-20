package im.zego.livedemo.helper;

import android.content.Context;
import im.zego.live.util.TokenServerAssistant;
import im.zego.live.util.ZegoRTCServerAssistant;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import org.json.JSONException;
import org.json.JSONObject;

public class AuthInfoManager {

    private AuthInfoManager() {
    }

    private static final class Holder {

        private static final AuthInfoManager INSTANCE = new AuthInfoManager();
    }

    public static AuthInfoManager getInstance() {
        return Holder.INSTANCE;
    }


    private String serverSecret;
    private long appID;
    private String appSign;
    private Context context;

    public long getAppID() {
        return appID;
    }

    public String getAppSign() {
        return appSign;
    }

    public void init(Context context) {
        this.context = context;
        String fileJson = readJsonFile(context, "KeyCenter.json");
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(fileJson);
            appID = jsonObject.getLong("appID");
            appSign = jsonObject.getString("appSign");
            serverSecret = jsonObject.getString("serverSecret");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String readJsonFile(Context context, String fileName) {
        String jsonStr = "";
        try {
            InputStream inputStream = context.getAssets().open(fileName);
            Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            inputStream.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String generateCreateRoomToken(String roomID, String userID) {
        ZegoRTCServerAssistant.Privileges privileges = new ZegoRTCServerAssistant.Privileges();
        privileges.canLoginRoom = true;
        privileges.canPublishStream = true;
        long appID = AuthInfoManager.getInstance().getAppID();
        String appSign = AuthInfoManager.getInstance().getAppSign();
        return ZegoRTCServerAssistant.generateToken(appID, roomID, userID, privileges, appSign, 660).data;
    }

    public String generateJoinRoomToken(String userID) {
        try {
            return TokenServerAssistant.generateToken(appID, userID, serverSecret, 60 * 60 * 24).data;
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

    public String generateLoginToken(String userID) {
        try {
            return TokenServerAssistant.generateToken(appID, userID, serverSecret, 60 * 60 * 24).data;
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }
}