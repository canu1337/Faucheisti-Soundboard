package eu.faucheisti.soundboard;

import java.io.IOException;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.webkit.JavascriptInterface;

//All of this because of a chromium bug that don't allow sound to be played from assets
// https://code.google.com/p/chromium/issues/detail?id=387898

public class AudioInterface {
    Context mContext;

    AudioInterface(Context c) {
        mContext = c;
    }

    //Play an audio file from the webpage
    @JavascriptInterface
    public void playAudio(String aud) { //String aud - file name passed
        //from the JavaScript function
        final MediaPlayer mp;

        try {
            AssetFileDescriptor fileDescriptor =
                    mContext.getAssets().openFd(aud);
            mp = new MediaPlayer();
            mp.setDataSource(fileDescriptor.getFileDescriptor(),
                    fileDescriptor.getStartOffset(),
                    fileDescriptor.getLength());
            fileDescriptor.close();
            mp.prepare();
            mp.start();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}