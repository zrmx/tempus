package com.cappielloantonio.tempo.broadcast.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.view.KeyEvent;

public class EcarxMediaButtonReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null || intent.getExtras() == null) {
            return;
        }

        KeyEvent keyEvent;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            keyEvent = intent.getParcelableExtra(Intent.EXTRA_KEY_EVENT, KeyEvent.class);
        } else {
            keyEvent = intent.getParcelableExtra(Intent.EXTRA_KEY_EVENT);
        }

        if (keyEvent == null) {
            return;
        }

        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        if (audioManager == null) {
            return;
        }

        audioManager.dispatchMediaKeyEvent(keyEvent);
    }
}
